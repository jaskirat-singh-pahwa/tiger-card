package com.jaskirat.tigercard;

public class ZonePolicies {
    private Zone fromZone;
    private Zone toZone;

    public ZonePolicies(Journey journey) {
        this.fromZone = journey.getFromZone();
        this.toZone = journey.getToZone();
    }

    public boolean isPolicyOneTrue() {
        return this.fromZone != Zone.Zone1 &&
                this.toZone == Zone.Zone1;
    }
}
