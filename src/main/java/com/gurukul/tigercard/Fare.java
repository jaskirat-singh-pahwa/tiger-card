package com.gurukul.tigercard;


import java.util.ArrayList;
import java.util.List;

public class Fare {
    private List<ZoneFare> zoneFareList = new ArrayList<>();

    public Fare() {
        zoneFareList.add(new ZoneFare(true, Zone.Zone1, Zone.Zone1, 30));
        zoneFareList.add(new ZoneFare(true, Zone.Zone2, Zone.Zone2, 25));
        zoneFareList.add(new ZoneFare(true, Zone.Zone1, Zone.Zone2, 35));
        zoneFareList.add(new ZoneFare(true, Zone.Zone2, Zone.Zone1, 35));

        zoneFareList.add(new ZoneFare(false, Zone.Zone1, Zone.Zone1, 25));
        zoneFareList.add(new ZoneFare(false, Zone.Zone2, Zone.Zone2, 20));
        zoneFareList.add(new ZoneFare(false, Zone.Zone1, Zone.Zone2, 30));
        zoneFareList.add(new ZoneFare(false, Zone.Zone2, Zone.Zone1, 30));
    }

    public int getFare(boolean isPeakHour, Zone fromZone, Zone toZone) {
        return this.zoneFareList.stream()
                .filter(f -> f.isPeakHourMatch(isPeakHour))
                .filter(f -> f.zoneMatch(fromZone, toZone))
                .map(ZoneFare :: getFare)
                .findFirst()
                .orElse(0);

    }


    static class ZoneFare {
        private boolean isPeakHour;
        private Zone fromZone;
        private Zone toZone;
        private int fare;

        public ZoneFare(boolean isPeakHour, Zone fromZone, Zone toZone, int fare) {
            this.isPeakHour = isPeakHour;
            this.fromZone = fromZone;
            this.toZone = toZone;
            this.fare = fare;
        }

        public boolean zoneMatch(Zone fromZone, Zone toZone) {
            return this.fromZone == fromZone &&
                    this.toZone == toZone;
        }

        public boolean isPeakHourMatch(boolean isPeakHour) {
            return this.isPeakHour == isPeakHour;
        }

        private int getFare() {
            return this.fare;
        }
    }
}
