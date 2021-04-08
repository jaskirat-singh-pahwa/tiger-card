package com.gurukul.tigercard

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types.DataTypes

object TimeOfTravel {

  case class TimeOfTravel(fromZone: Int,
                          toZone: Int,
                          isWeekend: Int,
                          hourOfDay: Int,
                          minuteOfDay: Int
                         )

  var udfGetTimeOfTravel: UserDefinedFunction = udf((fromZone: Int,
                                                  toZone: Int,
                                                  isWeekend: Int,
                                                  hourOfDay: Int,
                                                  minuteOfDay: Int) =>{
    val timeOfTravel: TimeOfTravel = TimeOfTravel(fromZone, toZone, isWeekend, hourOfDay, minuteOfDay)

    timeOfTravel match {
      case TimeOfTravel(f,t,w,h,m) if w == 1 && (h >= 9 && h <= 10) => "peakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 1 && f != 1 && t == 1 => "offPeakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 1 && (h >= 18 && h <= 21) => "peakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 1 => "offPeakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 0 && (h >= 7 && (h <= 10 && m <= 30)) => "peakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 0 && f != 1 && t == 1 => "offPeakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 0 && (h >= 17 && h <= 19) => "peakHour"
      case TimeOfTravel(f,t,w,h,m) if w == 0  => "offPeakHour"
      case _ => "error"
    }
  }, DataTypes.StringType)

  def getTimeOfTravel(processedCommutes: DataFrame): DataFrame = {
    processedCommutes.withColumn(colName="time_of_travel",
      udfGetTimeOfTravel
        .apply(processedCommutes.col("from_zone"),
          processedCommutes.col("to_zone"),
          processedCommutes.col("is_weekend"),
          processedCommutes.col("hour_of_day"),
          processedCommutes.col("minute_of_day"))
    )
  }
}
