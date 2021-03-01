package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.util.List;


public class FareCalculator {
    private  DayOfWeek previousDayOfWeek = DayOfWeek.MONDAY;
    private int maxDailyCap = 0;
    private int fareTillNow = 0;

    private List<Journey> journeys;
    private Capping capping;
    private Fare fare;


    public FareCalculator(List<Journey> journeys) {
       this.journeys = journeys;
       this.capping = new Capping();
       this.fare = new Fare();
   }

   public void getCalculatedFare() {
       for (Journey journey : this.journeys) {
           DayOfWeek currentDayOfWeek = journey.getDayOfWeek();
           Zone fromZone = journey.getFromZone();
           Zone toZone = journey.getToZone();
           TimeOfTravel timeOfTravel = new TimeOfTravel(journey);

           int currentDailyCap = this.capping.getDailyCap(fromZone, toZone);
           boolean dayChanged = isDayChanged(previousDayOfWeek, currentDayOfWeek);

           if (dayChanged) {
               maxDailyCap = 0;
               fareTillNow = 0;
           }

           maxDailyCap = Math.max(maxDailyCap, currentDailyCap);

           int standardFare = getStandardFare(timeOfTravel.isPeakHourTravel(), fromZone, toZone);
           fareTillNow += standardFare;
           int cappedFare = getCappedFare(fareTillNow, maxDailyCap);

           printDailyCap(currentDailyCap, maxDailyCap, standardFare, cappedFare);

           previousDayOfWeek = journey.getDayOfWeek();

       }
   }

    public static void printDailyCap(int currentDailyCap, int maxDailyCap, int standardFare, int cappedFare) {
        System.out.println(String.format("Current daily cap: %s", currentDailyCap));
        System.out.println(String.format("Max daily cap: %s", maxDailyCap));
        System.out.println(String.format("Standard Fare: %s", standardFare));
        System.out.println(String.format("Capped Fare: %s", cappedFare));
        System.out.println("\n");
    }

   public int getStandardFare(boolean isPeakHourTravelled, Zone fromZone, Zone toZone) {
       return this.fare.getFare(isPeakHourTravelled, fromZone, toZone);
   }

    public int getCappedFare(int fareTillNow, int maxDailyCap) {
        return Math.min(fareTillNow, maxDailyCap);
    }

    public boolean isDayChanged(DayOfWeek previousDayOfWeek, DayOfWeek currentDayOfWeek) {
        return previousDayOfWeek != currentDayOfWeek;
    }

}
