package com.gurukul.tigercard;

import java.time.DayOfWeek;


//public class FareCapping {
//    private Capping capping;
//    private FareCalculator fareCalculator;
//    private Zone fromZone;
//    private Zone toZone;
////    private int dailyCap;
////    private int weeklyCap;
////    private DayOfWeek dayOfWeek;
////    private int standardFare;
//
//
//    public FareCapping(Journey journey) {
//        this.capping = new Capping();
//        this.fareCalculator = new FareCalculator(journey);
//        this.fromZone = journey.getFromZone();
//        this.toZone = journey.getToZone();
////        this.dailyCap = dailyCap;
////        this.weeklyCap = weeklyCap;
////        this.dayOfWeek = dayOfWeek;
////        this.standardFare = standardFare;
//    }
//
//    public int getCappedFare() {
//        int dailyCap = this.capping.getDailyCap(this.fromZone, this.toZone);
//        int standardFare = fareCalculator.getStandardFare();
//
//        if (dailyCap - standardFare > 0) {
//            return standardFare;
//        }
//        else {
//            return dailyCap;
//        }
//    }
//}
