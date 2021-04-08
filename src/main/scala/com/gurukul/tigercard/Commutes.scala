package com.gurukul.tigercard

import org.apache.spark.sql.types.{StringType, IntegerType, StructField, StructType}
import org.apache.spark.sql.functions.{to_date, date_format, hour, minute, weekofyear, col}
import org.apache.spark.sql.{DataFrame, SparkSession}


class Commutes(commutesPath: String, implicit val spark: SparkSession) {
  private var commutesSchema: StructType = StructType(Array(
    StructField("maid", StringType, nullable = true),
    StructField("datetime", StringType, nullable = true),
    StructField("from_zone", IntegerType, nullable = true),
    StructField("to_zone", IntegerType, nullable = true)
  ))

  def this(commutesPath: String, spark: SparkSession, commutesSchema: StructType) {
    this(commutesPath, spark)
    this.commutesSchema = commutesSchema
  }

  def getDatetimeFeatures(rawCommutes: DataFrame): DataFrame = {
    rawCommutes
        .withColumn(colName="date", to_date(col(colName="datetime")))
        .withColumn(colName="day_of_week", date_format(col(colName="datetime"), format="EEEE"))
        .withColumn(colName="hour_of_day", hour(col(colName="datetime")))
        .withColumn(colName="minute_of_day", minute(col(colName="datetime")))
        .withColumn(colName="week_of_year", weekofyear(col(colName="datetime")))
        .withColumn(colName="is_weekend", date_format(col(colName="datetime"), format="EEE").isin(list = "Sat", "Sun").cast(to = "int"))
  }

  def readCommutes(): DataFrame = {
    spark.read
      .option("header", "true")
      .schema(commutesSchema)
      .csv(commutesPath)
  }

  def getCommutes: DataFrame = {
    val rawCommutes: DataFrame = readCommutes()
    getDatetimeFeatures(rawCommutes)
  }
}
