package tigercard;

import com.jaskirat.tigercard.Fare;
import com.jaskirat.tigercard.Zone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FareTest {
    @Test
    public void itShouldReturn30WhenFromZoneIsOneToZoneIsOneAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone1, Zone.Zone1), 30);

    }

    @Test
    public void itShouldReturn25WhenFromZoneIsOneToZoneIsOneAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone1, Zone.Zone1), 25);

    }

    @Test
    public void itShouldReturn25WhenFromZoneIsTwoToZoneIsTwoAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone2, Zone.Zone2), 25);

    }

    @Test
    public void itShouldReturn20WhenFromZoneIsTwoToZoneIsTwoAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone2, Zone.Zone2), 20);

    }

    @Test
    public void itShouldReturn35WhenFromZoneIsOneToZoneIsTwoAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone1, Zone.Zone2), 35);
    }

    @Test
    public void itShouldReturn30WhenFromZoneIsOneToZoneIsTwoAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone1, Zone.Zone2), 30);
    }

    @Test
    public void itShouldReturn35WhenFromZoneIsTwoToZoneIsOneAndPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(true, Zone.Zone2, Zone.Zone1), 35);
    }

    @Test
    public void itShouldReturn30WhenFromZoneIsTwoToZoneIsOneAndOffPeakHour() {
        Fare fare = new Fare();

        assertEquals(fare.getFare(false, Zone.Zone2, Zone.Zone1), 30);
    }
}


/*
    Red: Write a failing test for one atomic requirement.
    Green: Write the minimum code to get the failing test(s) to pass.
    Refactor: Refactor your code.

*/

