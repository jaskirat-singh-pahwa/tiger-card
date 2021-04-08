package com.gurukul.tigercard

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

class Capping(processedCommutes: DataFrame, implicit val spark: SparkSession) {
  private var cappingSchema: StructType = StructType(Array(
    StructField("from_zone", IntegerType, nullable=true),
    StructField("to_zone", IntegerType, nullable=true),
    StructField("time_of_travel", StringType, nullable=true),
    StructField("regular_fare", IntegerType, nullable=true),
    StructField("daily_cap", IntegerType, nullable=true),
    StructField("weekly_cap", IntegerType, nullable=true)
  ))

  private var cappingData: Seq[Row] = Seq(
//    fromZone, toZone, timeOfTravel, regularFare, dailyCap, weeklyCap
    Row(1,1,"peakHour",30,100,500),
    Row(1,1,"offPeakHour",25,100,500),

    Row(2,2,"peakHour",25,80,400),
    Row(2,2,"offPeakHour",20,80,400),

    Row(1,2,"peakHour",35,120,600),
    Row(1,2,"offPeakHour",30,120,600),

    Row(2,1,"peakHour",35,120,600),
    Row(2,1,"offPeakHour",30,120,600)
  )

  def this(processedCommutes: DataFrame, spark: SparkSession, cappingSchema: StructType, cappingData: Seq[Row]) {
    this(processedCommutes, spark)
    this.cappingSchema = cappingSchema
    this.cappingData = cappingData
  }

  def getCappingDataFrame: DataFrame = {
    spark.createDataFrame(spark.sparkContext.parallelize(cappingData), cappingSchema)
  }

  def getCommutesWithCapping: DataFrame = {
    val cappingDataFrame: DataFrame = getCappingDataFrame

    processedCommutes.join(cappingDataFrame,
      processedCommutes("from_zone") === cappingDataFrame("from_zone") &&
        processedCommutes("to_zone") === cappingDataFrame("to_zone") &&
        processedCommutes("time_of_travel") === cappingDataFrame("time_of_travel")
      ,
      joinType="left_outer")
      .select(processedCommutes.col(colName="*"),
        cappingDataFrame.col(colName="regular_fare"),
        cappingDataFrame.col(colName="daily_cap"),
        cappingDataFrame.col(colName="weekly_cap"))
  }
  def getDailyMaxCapping(commutesWithCapping: DataFrame): DataFrame = {
    Helpers.getGroupedResult(
      spark = spark,
      dataFrame = commutesWithCapping,
      groupedCols = List("maid", "date"),
      aggFunction = "max",
      aggColumn = "daily_cap",
      resultColumnName = "max_daily_cap"
    )
  }

  def getWeeklyMaxCapping(commutesWithCapping: DataFrame): DataFrame = {
    Helpers.getGroupedResult(
      spark = spark,
      dataFrame = commutesWithCapping,
      groupedCols = List("maid", "week_of_year"),
      aggFunction = "max",
      aggColumn = "weekly_cap",
      resultColumnName = "max_weekly_cap"
    )
  }

  def getCommutesWithMaxCapping: DataFrame = {
    val commutesWithCapping: DataFrame = getCommutesWithCapping
    val dailyMaxCapping: DataFrame = getDailyMaxCapping(commutesWithCapping)
    val weeklyMaxCapping: DataFrame = getWeeklyMaxCapping(commutesWithCapping)

    val commutesWithDailyMaxCapping: DataFrame = Helpers.joinTwoDataFrame(
      dataFrameOne = commutesWithCapping,
      dataFrameTwo = dailyMaxCapping,
      joinType = "left_outer",
      joinColumnOne = "maid",
      joinColumnTwo = "date"
    )
    Helpers.joinTwoDataFrame(
      dataFrameOne = commutesWithDailyMaxCapping,
      dataFrameTwo = weeklyMaxCapping,
      joinType = "left_outer",
      joinColumnOne = "maid",
      joinColumnTwo = "week_of_year"
    )
  }
}
