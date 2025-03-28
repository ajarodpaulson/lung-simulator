package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 * 
 * Code reference(s): CPSC 210 AlarmSystem
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("New lung profile added."); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("New lung profile added.", event.getDescription());
        assertEquals(date.getTime(), event.getDate().getTime(), 1);
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "New lung profile added.", event.toString());
    }

    @Test
    public void testEquals() {
        Event event2 = new Event("Zebras are forever.");
        assertFalse(event.equals(event2));
        assertFalse(event.equals(null));
        assertFalse(event.equals(date));
        assertTrue(event.equals(event));
        assertFalse(event.hashCode() == event2.hashCode());
        Event event3 = new Event("New lung profile added.");
        assertFalse(event3.equals(event));
    }
}
