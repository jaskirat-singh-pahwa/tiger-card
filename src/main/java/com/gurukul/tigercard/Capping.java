package com.gurukul.tigercard;

import java.util.*;


public class Capping {
    private List<CappingFare> cappingFareList = new ArrayList<>();

    public Capping() {
        cappingFareList.add(new CappingFare(Zone.Zone1, Zone.Zone1, 100, 500));
        cappingFareList.add(new CappingFare(Zone.Zone2, Zone.Zone2, 80, 400));
        cappingFareList.add(new CappingFare(Zone.Zone1, Zone.Zone2, 120, 600));
        cappingFareList.add(new CappingFare(Zone.Zone2, Zone.Zone1, 120, 600));
    }

    public int getDailyCap(Zone fromZone, Zone toZone) {
        return this.cappingFareList.stream()
                .filter(c -> c.zoneMatch(fromZone, toZone))
                .map(CappingFare :: getDailyCap)
                .findFirst()
                .orElse(0);
    }

    public int getWeeklyCap(Zone fromZone, Zone toZone) {
        return this.cappingFareList.stream()
                .filter(c -> c.zoneMatch(fromZone, toZone))
                .map(CappingFare :: getWeeklyCap)
                .findFirst()
                .orElse(0);
    }

    public int getMaximumDailyCap(List<Journey> journeys) {
        List<Integer> dailyCaps = new ArrayList<>();

        for (Journey journey: journeys) {
            dailyCaps.add(getDailyCap(journey.getFromZone(), journey.getToZone()));
        }

        return Collections.max(dailyCaps);
    }

    public int getMaximumWeeklyCap(List<Journey> journeys) {
        List<Integer> weeklyCaps = new ArrayList<>();

        for (Journey journey: journeys) {
            weeklyCaps.add(getWeeklyCap(journey.getFromZone(), journey.getToZone()));
        }

        return Collections.max(weeklyCaps);
    }


    static class CappingFare {
        private Zone fromZone;
        private Zone toZone;
        private int dailyCap;
        private int weeklyCap;

        public CappingFare(Zone fromZone, Zone toZone, int dailyCap, int weeklyCap) {
            this.fromZone = fromZone;
            this.toZone = toZone;
            this.dailyCap = dailyCap;
            this.weeklyCap = weeklyCap;
        }

        public boolean zoneMatch(Zone fromZone, Zone toZone) {
            return this.fromZone == fromZone &&
                    this.toZone == toZone;
        }

        private int getDailyCap() {
            return this.dailyCap;
        }

        private int getWeeklyCap() {
            return this.weeklyCap;
        }
    }
}
