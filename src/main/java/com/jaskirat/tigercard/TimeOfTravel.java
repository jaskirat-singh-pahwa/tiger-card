package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.time.LocalTime;


public class TimeOfTravel {
    private final DayOfWeek dayOfWeek;
    private final LocalTime time;
    private ZonePolicies zonePolicies;

    public TimeOfTravel(Journey journey) {
        this.dayOfWeek = journey.getDayOfWeek();
        this.time = journey.getTime();
        this.zonePolicies = new ZonePolicies(journey);
    }

    public static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) ||
                dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    public static boolean isWeekdayMorningTimeFrame(LocalTime time) {
        return time.isAfter(LocalTime.of(7,0))
                && time.isBefore(LocalTime.of(10,30));
    }

    public static boolean isWeekdayEveningTimeFrame(LocalTime time) {
        return time.isAfter(LocalTime.of(17,0))
                && time.isBefore(LocalTime.of(20,0));
    }

    public static boolean isWeekendMorningTimeFrame(LocalTime time) {
        return time.isAfter(LocalTime.of(9,0))
                && time.isBefore(LocalTime.of(11,0));
    }

    public static boolean isWeekendEveningTimeFrame(LocalTime time) {
        return time.isAfter(LocalTime.of(18,0))
                && time.isBefore(LocalTime.of(22,0));
    }

    public boolean isPeakHourTravel() {
        if (isWeekend(this.dayOfWeek)) {
            if (isWeekendMorningTimeFrame(this.time)) {
                return true;
            }
            else {
                if (isWeekendEveningTimeFrame(this.time)) {
                    return !this.zonePolicies.isPolicyOneTrue();
                }
                else {
                    return false;
                }
            }
        }

        else {
            if (isWeekdayMorningTimeFrame(this.time)) {
                return true;
            }
            else {
                if (isWeekdayEveningTimeFrame(this.time)) {
                    return !this.zonePolicies.isPolicyOneTrue();
                }
                else {
                    return false;
                }
            }
        }
    }
}
