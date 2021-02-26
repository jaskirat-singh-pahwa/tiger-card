package com.gurukul.tigercard;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalTime;


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

        journeys.add(new Journey(DayOfWeek.TUESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        Driver driver = new Driver(journeys);

        driver.runDriver();

    }
}
