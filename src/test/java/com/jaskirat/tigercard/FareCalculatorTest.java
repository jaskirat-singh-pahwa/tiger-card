package com.jaskirat.tigercard;

import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;


public class FareCalculatorTest {
    @Test
    public void itShouldReturn_35_when_weekday_time_10_20_zones_2_1() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.of(10,20), Zone.Zone2, Zone.Zone1
        );
        FareCalculator fareCalculator = new FareCalculator(journey);

        assertEquals(fareCalculator.getStandardFare(), 35);
    }

    @Test
    public void itShouldReturn_25_when_weekday_time_10_45_zones_1_1() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("10:45"), Zone.Zone1, Zone.Zone1
        );
        FareCalculator fareCalculator = new FareCalculator(journey);

        assertEquals(fareCalculator.getStandardFare(), 25);
    }

    @Test
    public void itShouldReturn_25_when_weekday_time_16_15_zones_1_1() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("16:15"), Zone.Zone1, Zone.Zone1
        );
        FareCalculator fareCalculator = new FareCalculator(journey);

        assertEquals(fareCalculator.getStandardFare(), 25);
    }

    @Test
    public void itShouldReturn_30_when_weekday_time_18_15_zones_1_1() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("18:15"), Zone.Zone1, Zone.Zone1
        );
        FareCalculator fareCalculator = new FareCalculator(journey);

        assertEquals(fareCalculator.getStandardFare(), 30);
    }

    @Test
    public void itShouldReturn_20_when_weekday_time_14_15_zones_2_2() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("14:15"), Zone.Zone2, Zone.Zone2
        );
        FareCalculator fareCalculator = new FareCalculator(journey);

        assertEquals(fareCalculator.getStandardFare(), 20);
    }




//    @Test
//    public void test1() {
//        Journey journey = new Journey(
//                DayOfWeek.MONDAY, LocalTime.parse("14:15"), Zone.Zone2, Zone.Zone2
//        );
//        FareCalculator fareCalculator = new FareCalculator(journey);
//
//        assertEquals(fareCalculator.getCappedFare(50), )
//    }
}


/*

Rules:
1.) Time of Travel
2.) Fares based on Time of Travel
3.) Fare Capping (Daily and Weekly)

 */
