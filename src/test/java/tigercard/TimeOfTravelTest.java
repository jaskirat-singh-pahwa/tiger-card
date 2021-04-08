package tigercard;

import com.jaskirat.tigercard.Journey;
import com.jaskirat.tigercard.TimeOfTravel;
import com.jaskirat.tigercard.Zone;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.Assert.*;

// Weekday MORNING time window: 7.00 to 10.30
// Weekday EVENING time window: 17.00 to 20.00
// Weekend MORNING time window: 9.00 to 11.00
// Weekend EVENING time window: 18.00 to 22.00

// Policy 1 is only applicable to evening time windows
// which is when fromZone is not zone1 and toZone is zone1

// Not following code standard while defining function names just for the sake of readability.


public class TimeOfTravelTest {
    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekday_MorningTimeWindow_LowerNominal() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("07:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekday_MorningTimeWindow_UpperNominal() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.TUESDAY, LocalTime.parse("10:30"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_MorningTimeWindow_BelowMinimum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("06:59"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekday_MorningTimeWindow_AboveMinimum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("07:01"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekday_MorningTimeWindow_BelowMaximum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("10:29"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_MorningTimeWindow_AboveMaximum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("10:31"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekend_MorningTimeWindow_LowerNominal() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("09:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekend_MorningTimeWindow_UpperNominal() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("11:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_MorningTimeWindow_BelowMinimum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("08:59"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekend_MorningTimeWindow_AboveMinimum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("09:01"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_PeakHour_WhenIts_Weekend_MorningTimeWindow_BelowMaximum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("10:59"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_MorningTimeWindow_AboveMaximum() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("11:01"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_LowerNominal_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("17:00"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_UpperNominal_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:00"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_BelowMinimum_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:59"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_LowerNominal_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("18:00"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_UpperNominal_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("22:00"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_BelowMinimum_PolicyOneTrue() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("17:59"), Zone.Zone2, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_LowerNominal_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("17:00"), Zone.Zone2, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_UpperNominal_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("20:00"), Zone.Zone2, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_EveningTimeWindow_BelowMinimum_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.MONDAY, LocalTime.parse("16:59"), Zone.Zone1, Zone.Zone1)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_LowerNominal_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("18:00"), Zone.Zone1, Zone.Zone1)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_UpperNominal_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SUNDAY, LocalTime.parse("22:00"), Zone.Zone1, Zone.Zone2)
        );

        assertTrue(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_EveningTimeWindow_BelowMinimum_PolicyOneFalse() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("17:59"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekend_NoTimeWindow() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.SATURDAY, LocalTime.parse("15:39"), Zone.Zone1, Zone.Zone2)
        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

    @Test
    public void itShouldReturn_OffPeakHour_WhenIts_Weekday_NoTimeWindow() {
        TimeOfTravel timeOfTravel = new TimeOfTravel(
                new Journey(DayOfWeek.FRIDAY, LocalTime.parse("01:11"), Zone.Zone1, Zone.Zone2)

        );

        assertFalse(timeOfTravel.isPeakHourTravel());
    }

}


/*
    Red: Write a failing test for one atomic requirement.
    Green: Write the minimum code to get the failing test(s) to pass.
    Refactor: Refactor your code.

*/
