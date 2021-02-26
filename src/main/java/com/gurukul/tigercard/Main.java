package com.gurukul.tigercard;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalTime;


public class Main {
//    public static DayOfWeek previousDayOfWeek = DayOfWeek.MONDAY;
//    public static DayOfWeek currentDayOfWeek;
//    public static int previousDailyCap = 0;
//    public static int currentDailyCap;
//    public static int maxDailyCap;


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

//        int result = getCardAmount(journeys);
//        System.out.println(result);
    }

//    public static int getCardAmount(List<Journey> journeys) {
////        previousDayOfWeek = DayOfWeek.MONDAY;
////        previousDailyCap = 0;
//
//        for (Journey journey : journeys) {
//            currentDayOfWeek = journey.getDayOfWeek();
//            Capping capping = new Capping();
//            currentDailyCap = capping.getDailyCap(journey.getFromZone(), journey.getToZone());
//
//            boolean dayChanged = isDayChanged(currentDayOfWeek, previousDayOfWeek);
//
//            if (dayChanged) {
//                previousDailyCap = 0;
//            }
//
//            maxDailyCap = Math.max(previousDailyCap, currentDailyCap);
//
//            printDailyCap(currentDailyCap, previousDailyCap, maxDailyCap);
//
//
//            previousDayOfWeek = journey.getDayOfWeek();
//            previousDailyCap = capping.getDailyCap(journey.getFromZone(), journey.getToZone());
//        }
//        return 0;
//    }
//
//    public static boolean isDayChanged(DayOfWeek currentDayOfWeek, DayOfWeek previousDayOfWeek) {
//        return currentDayOfWeek != previousDayOfWeek;
//    }
//
//    public static void printDailyCap(int currentDailyCap, int previousDailyCap, int maxDailyCap) {
//        System.out.println(String.format("Current daily cap: %s", currentDailyCap));
//        System.out.println(String.format("Previous daily cap: %s", previousDailyCap));
//        System.out.println(String.format("Max daily cap: %s", maxDailyCap));
//        System.out.println("\n");
//
//
//    }
}
