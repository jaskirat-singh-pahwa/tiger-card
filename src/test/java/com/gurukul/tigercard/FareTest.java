package com.gurukul.tigercard;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FareTest {
    @Test
    public void itShouldReturn30_WhenFromZoneIsOneToZoneIsOneAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone1, Zone.Zone1), 30);

    }

    @Test
    public void itShouldReturn25_WhenFromZoneIsOneToZoneIsOneAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone1, Zone.Zone1), 25);

    }

    @Test
    public void itShouldReturn25_WhenFromZoneIsTwoToZoneIsTwoAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone2, Zone.Zone2), 25);

    }

    @Test
    public void itShouldReturn20_WhenFromZoneIsTwoToZoneIsTwoAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone2, Zone.Zone2), 20);

    }

    @Test
    public void itShouldReturn35_WhenFromZoneIsOneToZoneIsTwoAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone1, Zone.Zone2), 35);
    }

    @Test
    public void itShouldReturn30_WhenFromZoneIsOneToZoneIsTwoAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone1, Zone.Zone2), 30);
    }

    @Test
    public void itShouldReturn35_WhenFromZoneIsTwoToZoneIsOneAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone2, Zone.Zone1), 35);
    }

    @Test
    public void itShouldReturn30_WhenFromZoneIsTwoToZoneIsOneAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone2, Zone.Zone1), 30);
    }
}


/*
    Red: Write a failing test for one atomic requirement.
    Green: Write the minimum code to get the failing test(s) to pass.
    Refactor: Refactor your code.

*/

