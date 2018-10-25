package myskyscanner.flight;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

public class MyFlightDatabaseTest {

    MyFlightDatabase test = new MyFlightDatabase();

    @Test
    public void addFlight_uniqueFlightToTheDatabase_true() throws InvalidAttributesException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departure = LocalDateTime.parse("2018-10-25 12:00", formatter);
        LocalDateTime arrival = LocalDateTime.parse("2018-10-25 14:30", formatter);
        assertTrue(test.addFlight("r", "e", "s", departure, arrival, 200, 100, 0, "me"));
    }

    @Test(expected = InvalidAttributesException.class)
    public void addFlight_alreadyExistingFlightToTheDatabase_exception()
            throws InvalidAttributesException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departure = LocalDateTime.parse("2018-10-25 12:00", formatter);
        LocalDateTime arrival = LocalDateTime.parse("2018-10-25 14:30", formatter);
        test.addFlight("r", "e", "s", departure, arrival, 200, 100, 0, "me");
        test.addFlight("r", "e", "s", departure, arrival, 200, 100, 0, "me");
    }

    public void removeFlight_existingFlightFromTheDatabase_true()
            throws InvalidAttributesException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departure = LocalDateTime.parse("2018-10-25 12:00", formatter);
        LocalDateTime arrival = LocalDateTime.parse("2018-10-25 14:30", formatter);
        test.addFlight("r", "e", "s", departure, arrival, 200, 100, 0, "me");
        assertTrue(test.removeFlight("re2518014s"));
    }

    @Test(expected = InvalidAttributesException.class)
    public void removeFlight_notExistingFlightFromTheDatabase_exception()
            throws InvalidAttributesException {
        test.removeFlight("f");
    }

}
