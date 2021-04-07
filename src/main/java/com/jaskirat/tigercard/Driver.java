package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.util.List;


public class Driver {
    private  DayOfWeek previousDayOfWeek = DayOfWeek.MONDAY;
    private int maxDailyCap = 0;
    private int fareTillNow = 0;
    private List<Journey> journeys;
    private Capping capping;
    private FareCalculator fareCalculator;

    public Driver(List<Journey> journeys) {
        this.journeys = journeys;
        this.capping = new Capping();
    }

    public void runDriver() {

        for(Journey journey : this.journeys) {
            int currentDailyCap = this.capping.getDailyCap(journey.getFromZone(), journey.getToZone());
            boolean dayChanged = isDayChanged(previousDayOfWeek, journey.getDayOfWeek());

            if (dayChanged) {
                maxDailyCap = 0;
                fareTillNow = 0;
            }

            maxDailyCap = Math.max(maxDailyCap, currentDailyCap);
            this.fareCalculator = new FareCalculator(this.journeys);
//            int standardFare = this.fareCalculator.getStandardFare();
//            fareTillNow += standardFare;
//
//            int cappedFare = this.fareCalculator.getCappedFare(fareTillNow, maxDailyCap);
//
//            printDailyCap(currentDailyCap, maxDailyCap, standardFare, cappedFare);
//
//            previousDayOfWeek = journey.getDayOfWeek();
        }
    }

    public static void printDailyCap(int currentDailyCap, int maxDailyCap, int standardFare, int cappedFare) {
        System.out.println(String.format("Current daily cap: %s", currentDailyCap));
        System.out.println(String.format("Max daily cap: %s", maxDailyCap));
        System.out.println(String.format("Standard Fare: %s", standardFare));
        System.out.println(String.format("Capped Fare: %s", cappedFare));
        System.out.println("\n");
    }

    public boolean isDayChanged(DayOfWeek previousDayOfWeek, DayOfWeek currentDayOfWeek) {
        return previousDayOfWeek != currentDayOfWeek;
    }
}
