package com.gurukul.tigercard

import org.apache.spark.sql.{DataFrame, SparkSession}
import scopt.OParser

object Main {

  def main(args: Array[String]): Unit = {
    val appName: String = "TigerCard"
    val startTime: Long = System.currentTimeMillis / 1000

    implicit val spark: SparkSession = SparkSession.builder
      .appName(appName)
      .master(master="local")
      .getOrCreate()

    OParser.parse(CliArgs.parser(appName), args, CliArgs()) match {
      case Some(config) => runSpark(
        config.commutesPath,
        config.outputPath)
      case _ => System.exit(1)
    }

    val endTime: Long = System.currentTimeMillis / 1000
    println(s"Total time taken to find fare: ${endTime - startTime}")

    spark.stop()
  }

  def runSpark(commutesPath: String, outputPath: String)(implicit spark: SparkSession): Unit= {
    val commutes: Commutes = new Commutes(commutesPath = commutesPath, spark = spark)
    val processedCommutes: DataFrame = commutes.getCommutes

    val commutesWithTimeOfTravel: DataFrame = TimeOfTravel.getTimeOfTravel(processedCommutes)

    val cap: Capping = new Capping(processedCommutes = commutesWithTimeOfTravel, spark = spark)
    val commutesWithCapping: DataFrame = cap.getCommutesWithMaxCapping

    val fare: Fare = new Fare()
    val calculatedFare: DataFrame = fare.getFare(commutesWithCapping)
    println(calculatedFare.printSchema())
    println(calculatedFare.show(40, truncate = false))
  }
}
