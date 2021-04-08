package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("07:01"), Zone.Zone2, Zone.Zone1));

        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.FRIDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SATURDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.SUNDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));


        FareCalculator fareCalculator = new FareCalculator(journeys);
        System.out.println(String.format("Amount to be paid for this week is: %d", fareCalculator.getCalculatedFare()));
    }
}
