package com.jaskirat.tigercard;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.*;


public class TimeOfTravelTest {
    @Test
    public void itShouldGiveMePeakHourWhenItsMondayAndTimeBetweenSevenToTen() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("09:22"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldGiveMePeakHourWhenItsTuesdayAndTimeBetweenSevenToTen() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.TUESDAY, LocalTime.parse("09:22"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldGiveMePeakHourWhenItsTuesdayAndTimeBetweenSeventeenToTwenty() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.TUESDAY, LocalTime.parse("17:01"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldNotGiveMePeakHourWhenItsTuesdayAndTimeIsAfterTenThirty() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.TUESDAY, LocalTime.parse("11:00"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldGiveMePeakHourWhenItsSundayAndTimeIsBetweenNineAndEleven() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("10:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldGiveMePeakHourWhenItsSundayAndTimeBetweenEighteenToTwentyTwo() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.TUESDAY, LocalTime.parse("19:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldNotGiveMePeakHourWhenItsSundayAndTimeIsAfterEleven() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("12:00"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldNotGiveMePeakHourWhenItsSundayAndTimeIsAfterTwentyTwo() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("23:00"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldNotGiveMePeakHourWhenItsSundayAndZonePolicyOneTimeBetweenEighteenToTwentyTwo() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("19:00"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldNotGiveMePeakHourWhenItsWednesdayAndZonePolicyOneTimeBetweenSeventeenToTwenty() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.WEDNESDAY, LocalTime.parse("17:01"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }
}


/*
    Red: Write a failing test for one atomic requirement.
    Green: Write the minimum code to get the failing test(s) to pass.
    Refactor: Refactor your code.

*/
