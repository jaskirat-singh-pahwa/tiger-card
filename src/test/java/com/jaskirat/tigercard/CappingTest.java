package com.jaskirat.tigercard;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class CappingTest {
    @Test
    public void itShouldReturnDailyCap100WhenFromZoneOneAndToZoneOne() {
        Capping capping = new Capping();

        assertEquals(capping.getDailyCap(Zone.Zone1, Zone.Zone1), 100);

    }

    @Test
    public void itShouldReturnDailyCap80WhenFromZoneTwoAndToZoneTwo() {
        Capping capping = new Capping();

        assertEquals(capping.getDailyCap(Zone.Zone2, Zone.Zone2), 80);

    }

    @Test
    public void itShouldReturnDailyCap120WhenFromZoneOneAndToZoneTwo() {
        Capping capping = new Capping();

        assertEquals(capping.getDailyCap(Zone.Zone1, Zone.Zone2), 120);

    }

    @Test
    public void itShouldReturnDailyCap120WhenFromZoneTwoAndToZoneOne() {
        Capping capping = new Capping();

        assertEquals(capping.getDailyCap(Zone.Zone2, Zone.Zone1), 120);

    }

    @Test
    public void itShouldReturnWeeklyCap100WhenFromZoneOneAndToZoneOne() {
        Capping capping = new Capping();

        assertEquals(capping.getWeeklyCap(Zone.Zone1, Zone.Zone1), 500);

    }

    @Test
    public void itShouldReturnWeeklyCap80WhenFromZoneTwoAndToZoneTwo() {
        Capping capping = new Capping();

        assertEquals(capping.getWeeklyCap(Zone.Zone2, Zone.Zone2), 400);

    }

    @Test
    public void itShouldReturnWeeklyCap120WhenFromZoneOneAndToZoneTwo() {
        Capping capping = new Capping();

        assertEquals(capping.getWeeklyCap(Zone.Zone1, Zone.Zone2), 600);

    }

    @Test
    public void itShouldReturnWeeklyCap120WhenFromZoneTwoAndToZoneOne() {
        Capping capping = new Capping();

        assertEquals(capping.getWeeklyCap(Zone.Zone2, Zone.Zone1), 600);

    }

    @Test
    public void itShouldReturn120AsMaxDailyCap() {
        Capping capping = new Capping();
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        int maxDailyCap = capping.getMaximumDailyCap(journeys);

        assertEquals(maxDailyCap, 120);
    }

    @Test
    public void itShouldReturn100AsMaxDailyCap() {
        Capping capping = new Capping();
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("10:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("14:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("15:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:22"), Zone.Zone2, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        int maxDailyCap = capping.getMaximumDailyCap(journeys);

        assertEquals(maxDailyCap, 100);
    }
    @Test
    public void itShouldReturn600AsMaxWeeklyCap() {
        Capping capping = new Capping();
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone2));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        int maxWeeklyCap = capping.getMaximumWeeklyCap(journeys);

        assertEquals(maxWeeklyCap, 600);
    }

    @Test
    public void itShouldReturn500AsMaxWeeklyCap() {
        Capping capping = new Capping();
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("15:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:22"), Zone.Zone1, Zone.Zone1));
        journeys.add(new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:22"), Zone.Zone2, Zone.Zone2));

        int maxWeeklyCap = capping.getMaximumWeeklyCap(journeys);

        assertEquals(maxWeeklyCap, 500);
    }

}


/*
    Red: Write a failing test for one atomic requirement.
    Green: Write the minimum code to get the failing test(s) to pass.
    Refactor: Refactor your code.

*/

