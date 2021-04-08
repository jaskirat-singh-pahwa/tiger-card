package com.gurukul.tigercard

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{collect_list, sum, max}

object Helpers {
  def getGroupedResult(spark: SparkSession,
                       dataFrame: DataFrame,
                       groupedCols: List[String],
                       aggFunction: String,
                       aggColumn: String,
                       resultColumnName: String): DataFrame = {
    var groupedDataFrame: DataFrame = spark.emptyDataFrame

    if (aggFunction == "sum") {
      groupedDataFrame = dataFrame
        .groupBy(groupedCols.head, groupedCols.tail:_*)
        .agg(sum(aggColumn).alias(alias = resultColumnName))
    }
    else if (aggFunction == "collectList") {
      groupedDataFrame = dataFrame
        .groupBy(groupedCols.head, groupedCols.tail:_*)
        .agg(collect_list(aggColumn).alias(alias = resultColumnName))
    }
    else if (aggFunction == "max") {
      groupedDataFrame = dataFrame
        .groupBy(groupedCols.head, groupedCols.tail:_*)
        .agg(max(aggColumn).alias(alias = resultColumnName))
    }
    groupedDataFrame
  }

  def joinTwoDataFrame(dataFrameOne: DataFrame,
                       dataFrameTwo: DataFrame,
                       joinType: String,
                       joinColumnOne: String,
                       joinColumnTwo: String): DataFrame = {
    dataFrameOne.join(dataFrameTwo,
      dataFrameOne.col(joinColumnOne) === dataFrameTwo.col(joinColumnOne) &&
        dataFrameOne.col(joinColumnTwo) === dataFrameTwo.col(joinColumnTwo),
      joinType = joinType)
      .drop(dataFrameOne.col(joinColumnOne))
      .drop(dataFrameOne.col(joinColumnTwo))
  }
}
