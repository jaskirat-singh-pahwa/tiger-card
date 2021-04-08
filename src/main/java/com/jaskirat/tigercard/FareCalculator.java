package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.util.List;


public class FareCalculator {
    private DayOfWeek previousDayOfWeek;
    private int currentDailyCap;
    private int currentWeeklyCap;
    private int maxDailyCap;
    private int maxWeeklyCap;
    private int dailyFareTillNow;
    private int weeklyFareTillNow;
    private int dailyCappedFare;
    private int weeklyCappedFare;
    private int x;

    private List<Journey> journeys;
    private Capping capping;
    private Fare fare;


    public FareCalculator(List<Journey> journeys) {
        this.journeys = journeys;
        this.capping = new Capping();
        this.fare = new Fare();

        this.previousDayOfWeek = DayOfWeek.MONDAY;
        this.currentDailyCap = 0;
        this.currentWeeklyCap = 0;
        this.maxDailyCap = 0;
        this.maxWeeklyCap = 0;
        this.dailyFareTillNow = 0;
        this.weeklyFareTillNow = 0;
        this.dailyCappedFare = 0;
        this.weeklyCappedFare = 0;
        this.x = 0;
    }

    public int getCalculatedFare() {
        for (Journey journey : this.journeys) {

            DayOfWeek currentDayOfWeek = journey.getDayOfWeek();
            Zone fromZone = journey.getFromZone();
            Zone toZone = journey.getToZone();
            TimeOfTravel timeOfTravel = new TimeOfTravel(journey);

            this.currentDailyCap = this.capping.getDailyCap(fromZone, toZone);
            this.currentWeeklyCap = this.capping.getWeeklyCap(fromZone, toZone);

            boolean dayChanged = isDayChanged(this.previousDayOfWeek, currentDayOfWeek);
            if (dayChanged) {
                this.maxDailyCap = 0;
                this.dailyFareTillNow = 0;
                this.x = Math.max(this.dailyCappedFare, this.weeklyFareTillNow);
            }

            this.maxDailyCap = Math.max(this.maxDailyCap, this.currentDailyCap);
            this.maxWeeklyCap = Math.max(this.maxWeeklyCap, this.currentWeeklyCap);

            int standardFare = getStandardFare(timeOfTravel.isPeakHourTravel(), fromZone, toZone);
            this.dailyFareTillNow += standardFare;
            this.dailyCappedFare = getCappedFare(this.dailyFareTillNow, this.maxDailyCap);

            this.weeklyFareTillNow = this.x + this.dailyCappedFare;
            this.weeklyCappedFare = getCappedFare(this.weeklyFareTillNow, this.maxWeeklyCap);
            printWeeklyCap(
                    this.currentDailyCap,
                    this.maxDailyCap,
                    this.currentWeeklyCap,
                    this.maxWeeklyCap,
                    standardFare,
                    this.dailyCappedFare,
                    this.weeklyFareTillNow,
                    this.weeklyCappedFare
            );

            previousDayOfWeek = journey.getDayOfWeek();

        }

        return this.weeklyCappedFare;
    }

    public static void printWeeklyCap(
            int currentDailyCap,
            int maxDailyCap,
            int currentWeeklyCap,
            int maxWeeklyCap,
            int standardFare,
            int cappedDailyFare,
            int weeklyFareSoFar,
            int cappedWeeklyFare
    ) {

        System.out.println(String.format("Current Daily Cap: %d", currentDailyCap));
        System.out.println(String.format("Max Daily Cap: %d", maxDailyCap));
        System.out.println(String.format("Current Weekly Cap: %d", currentWeeklyCap));
        System.out.println(String.format("Max Weekly Cap: %d", maxWeeklyCap));
        System.out.println(String.format("Standard Fare: %d", standardFare));
        System.out.println(String.format("Capped Daily Fare: %d", cappedDailyFare));
        System.out.println(String.format("Weekly Fare so far: %d", weeklyFareSoFar));
        System.out.println(String.format("Capped Weekly Fare: %d", cappedWeeklyFare));
        System.out.println("\n");
    }

    public int getStandardFare(boolean isPeakHourTravelled, Zone fromZone, Zone toZone) {
        return this.fare.getFare(isPeakHourTravelled, fromZone, toZone);
    }

    public int getCappedFare(int fareTillNow, int maxCap) {
        return Math.min(fareTillNow, maxCap);
    }

    public boolean isDayChanged(DayOfWeek previousDayOfWeek, DayOfWeek currentDayOfWeek) {
        return previousDayOfWeek != currentDayOfWeek;
    }

}
