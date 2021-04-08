package com.gurukul.tigercard

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, when}

class Fare(implicit val spark: SparkSession) {

  def getCommutesWithSumDailyFare(commutesWithCappingLimit: DataFrame): DataFrame = {
    val commutesWithSUmDailyFare: DataFrame = Helpers.getGroupedResult(
      spark = spark,
      dataFrame = commutesWithCappingLimit,
      groupedCols = List("maid", "date"),
      aggFunction = "sum",
      aggColumn = "regular_fare",
      resultColumnName = "sum_daily_fare"
    )

    Helpers.joinTwoDataFrame(
      dataFrameOne = commutesWithCappingLimit,
      dataFrameTwo = commutesWithSUmDailyFare,
      joinType = "left_outer",
      joinColumnOne = "maid",
      joinColumnTwo = "date"
    )
  }

  def getMaidsWithCappedDailyFare(commutesWithSumDailyFare: DataFrame): DataFrame = {
    commutesWithSumDailyFare.groupBy(
      "maid", "date", "week_of_year", "sum_daily_fare", "max_daily_cap", "max_weekly_cap")
      .agg(when(col(colName = "sum_daily_fare") >= col(colName = "max_daily_cap"), col(colName = "max_daily_cap"))
        .otherwise(col(colName = "sum_daily_fare")).alias(alias = "capped_daily_fare"))
  }

  def getMaidsWithSumWeeklyFare(maidsWithCappedDailyFare: DataFrame): DataFrame  = {
    Helpers.getGroupedResult(
      spark = spark,
      dataFrame = maidsWithCappedDailyFare,
      groupedCols = List("maid", "week_of_year", "max_weekly_cap"),
      aggFunction = "sum",
      aggColumn = "capped_daily_fare",
      resultColumnName = "sum_weekly_fare"
    )
  }

  def getMaidsWithCappedWeeklyFare(commutesWithSumDailyFare: DataFrame): DataFrame = {
    commutesWithSumDailyFare.groupBy(
      "maid", "week_of_year", "max_weekly_cap", "sum_weekly_fare")
      .agg(when(col(colName = "sum_weekly_fare") >= col(colName = "max_weekly_cap"), col(colName = "max_weekly_cap"))
        .otherwise(col(colName = "sum_weekly_fare")).alias(alias = "capped_weekly_fare"))
      .select("maid", "week_of_year", "capped_weekly_fare")
  }


  def getFare(commutesWithCappingLimit: DataFrame): DataFrame = {
    getMaidsWithCappedWeeklyFare(getMaidsWithSumWeeklyFare(getMaidsWithCappedDailyFare(
      getCommutesWithSumDailyFare(
        commutesWithCappingLimit
      ))))
  }
}
