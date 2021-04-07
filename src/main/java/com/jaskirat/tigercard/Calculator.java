package com.jaskirat.tigercard;


import java.time.DayOfWeek;

public class Calculator {
    private Journey journey;
    private Capping capping;
    private Fare fare;
    private DayOfWeek previousDayOfWeek;
    private int fareTillNow;

    public Calculator(Journey journey, DayOfWeek previousDayOfWeek, int fareTillNow) {
        this.journey = journey;
        this.previousDayOfWeek = previousDayOfWeek;
        this.fareTillNow = fareTillNow;
        this.capping = new Capping();
        this.fare = new Fare();
    }

    public void calculateFare() {
        DayOfWeek currentDayOfWeek = this.journey.getDayOfWeek();
        Zone fromZone = this.journey.getFromZone();
        Zone toZone = this.journey.getToZone();
        TimeOfTravel timeOfTravel = new TimeOfTravel(journey);
        int currentDailyCap = this.capping.getDailyCap(fromZone, toZone);
        boolean dayChanged = isDayChanged(previousDayOfWeek, currentDayOfWeek);


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
