package com.gurukul.tigercard;


public class FareCalculator {
    private Zone fromZone;
    private Zone toZone;
    private TimeOfTravel timeOfTravel;
    private Fare fare;

    public FareCalculator(Journey journey) {
        this.fromZone = journey.getFromZone();
        this.toZone = journey.getToZone();
        this.timeOfTravel = new TimeOfTravel(journey);
        this.fare = new Fare();
    }

    public int getStandardFare() {
        return this.fare.getFare(this.timeOfTravel.isPeakHourTravel(),
                this.fromZone, this.toZone);

    }

    public int getCappedFare(int fareTillNow, int maxDailyCap) {
        return Math.min(fareTillNow, maxDailyCap);
    }
}
