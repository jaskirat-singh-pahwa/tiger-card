package com.jaskirat.tigercard;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import static org.junit.Assert.*;


public class ZonePoliciesTest {
    @Test
    public void itShouldReturnTrueWhenFromZoneIsNotOneAndToZoneIsOne() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("18:15"), Zone.Zone2, Zone.Zone1
        );
        ZonePolicies zonePolicies = new ZonePolicies(journey);

        assertTrue(zonePolicies.isPolicyOneTrue());
    }

    @Test
    public void itShouldReturnFalseWhenFromZoneIsOneAndToZoneIsOne() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("18:15"), Zone.Zone1, Zone.Zone1
        );
        ZonePolicies zonePolicies = new ZonePolicies(journey);

        assertFalse(zonePolicies.isPolicyOneTrue());
    }

    @Test
    public void itShouldReturnFalseWhenFromZoneIsTwoAndToZoneIsTwo() {
        Journey journey = new Journey(
                DayOfWeek.MONDAY, LocalTime.parse("18:15"), Zone.Zone2, Zone.Zone2
        );
        ZonePolicies zonePolicies = new ZonePolicies(journey);

        assertFalse(zonePolicies.isPolicyOneTrue());
    }

}
