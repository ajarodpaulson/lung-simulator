package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LungProfileManagerTest extends TestLungProfiles {
    LungProfileManager lpm;

    @BeforeEach
    void runBefore() {
        lpm = LungProfileManager.getInstance();
        lpm.resetInstance();
    }

    @Test
    void testConstructor() {
        assertTrue(lpm.getLungProfiles().isEmpty());
        assertEquals("My lung profile manager", lpm.getName());
        assertNull(lpm.getActiveLungProfile());
    }

    @Test
    void testSetActiveLungProfile() {
        lpm.setActiveLungProfile(lp1);
        assertEquals(lp1, lpm.getActiveLungProfile());
    }

    @Test 
    void testSetNameGivenDifferentNameShouldUpdateNameCorrectly() {
        lpm.setName("Kitamura");
        assertEquals("Kitamura", lpm.getName());
    }

    @Test
    void testFindLungProfile1LongFound() {
        lpm.addLungProfile(lp1);
        assertEquals(lp1, lpm.findLungProfile("COPD").get());
    }

    @Test
    void testFindLungProfile1LongNotFound() {
        lpm.addLungProfile(lp1);
        assertFalse(lpm.findLungProfile("Fibrosis").isPresent());
    }

    @Test
    void testFindLungProfile2LongFoundAtStartIndex() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertEquals(lp1, lpm.findLungProfile("COPD").get());
    }

    @Test
    void testFindLungProfile2LongFoundAtEndIndex() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertEquals(lp2, lpm.findLungProfile("Asthma").get());
    }

    @Test
    void testFindLungProfile2LongNotFound() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertFalse(lpm.findLungProfile("Daenerys Targaryen").isPresent());
    }

    @Test
    void testAddLungProfileTo0Long() {
        lpm.addLungProfile(lp1);
        assertTrue(lpm.getLungProfiles().contains(lp1));
    }

    @Test
    void testAddLungProfileTo1Long() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertTrue(lpm.getLungProfiles().containsAll(lpm.getLungProfiles()));
    }

    @Test
    void testAddLungProfileTo2Long() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        lpm.addLungProfile(lp3);
        assertTrue(lpm.getLungProfiles().containsAll(lpm.getLungProfiles()));
    }

    @Test
    void deleteLungProfile1LongInList() {
        lpm.addLungProfile(lp1);
        assertTrue(lpm.deleteLungProfile(lp1));
        assertTrue(lpm.getLungProfiles().isEmpty());
    }

    @Test
    void deleteLungProfile1LongNotInList() {
        lpm.addLungProfile(lp1);
        assertFalse(lpm.deleteLungProfile(lp2));
        assertEquals(1, lpm.numLungProfiles());
    }

    @Test
    void deleteLungProfile2LongAtStartIndex() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertTrue(lpm.deleteLungProfile(lp1));
        assertEquals(1, lpm.numLungProfiles());
        assertFalse(lpm.getLungProfiles().contains(lp1));
    }

    @Test
    void deleteLungProfile2LongAtEndIndex() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertTrue(lpm.deleteLungProfile(lp2));
        assertEquals(1, lpm.numLungProfiles());
        assertFalse(lpm.getLungProfiles().contains(lp2));
    }

    @Test
    void deleteLungProfile2LongNotInList() {
        lpm.addLungProfile(lp1);
        lpm.addLungProfile(lp2);
        assertFalse(lpm.deleteLungProfile(lp3));
        assertEquals(2, lpm.numLungProfiles());
    }

    @Test
    void deleteLungProfileWhichIsAlsoActiveLungProfile() {
        lpm.addLungProfile(lp1);
        lpm.setActiveLungProfile(lp1);
        lpm.deleteLungProfile(lp1);
        assertTrue(lpm.getLungProfiles().isEmpty());
        assertNull(lpm.getActiveLungProfile());
    }
}

