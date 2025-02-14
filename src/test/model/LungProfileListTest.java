package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LungProfileListTest extends TestLungProfiles {
    LungProfileManager lungProfileList0Long;
    LungProfileManager lungProfileList1Long;
    LungProfileManager lungProfileList2Long;
    LungProfileManager lungProfileList3Long;

    @BeforeEach
    void runBefore() {

        lungProfileList0Long = new LungProfileManager();

        lungProfileList1Long = new LungProfileManager();
        lungProfileList1Long.addLungProfile(lp1);

        lungProfileList2Long = new LungProfileManager();
        lungProfileList2Long.addLungProfile(lp1);
        lungProfileList2Long.addLungProfile(lp2);

        lungProfileList3Long = new LungProfileManager();
        lungProfileList3Long.addLungProfile(lp2);
        lungProfileList3Long.addLungProfile(lp1);
        lungProfileList3Long.addLungProfile(lp3);
    }

    @Test
    void testConstructor() {
        assertTrue(lungProfileList0Long.getLungProfiles().isEmpty());
    }

    @Test
    void testFindLungProfile1LongFound() {
        assertEquals(lp1, lungProfileList1Long.findLungProfile("COPD").get());
    }

    @Test
    void testFindLungProfile1LongNotFound() {
        assertFalse(lungProfileList1Long.findLungProfile("Fibrosis").isPresent());
    }

    @Test
    void testFindLungProfile2LongFoundAtStartIndex() {
        assertEquals(lp1, lungProfileList2Long.findLungProfile("COPD").get());
    }

    @Test
    void testFindLungProfile2LongFoundAtEndIndex() {
        assertEquals(lp2, lungProfileList2Long.findLungProfile("Asthma").get());
    }

    @Test
    void testFindLungProfile2LongNotFound() {
        assertFalse(lungProfileList2Long.findLungProfile("Daenerys Targaryen").isPresent());
    }

    @Test
    void testAddLungProfileTo0Long() {
        lungProfileList0Long.addLungProfile(lp1);
        assertTrue(lungProfileList0Long.getLungProfiles().contains(lp1));
    }

    @Test
    void testAddLungProfileTo1Long() {
        lungProfileList1Long.addLungProfile(lp2);
        assertTrue(lungProfileList1Long.getLungProfiles().containsAll(lungProfileList2Long.getLungProfiles()));
    }

    @Test
    void testAddLungProfileTo2Long() {
        lungProfileList2Long.addLungProfile(lp3);
        assertTrue(lungProfileList2Long.getLungProfiles().containsAll(lungProfileList3Long.getLungProfiles()));
    }

    @Test
    void deleteLungProfile1LongInList() {
        assertTrue(lungProfileList1Long.deleteLungProfile(lp1));
        assertTrue(lungProfileList1Long.getLungProfiles().isEmpty());
    }

    @Test
    void deleteLungProfile1LongNotInList() {
        assertFalse(lungProfileList1Long.deleteLungProfile(lp2));
        assertEquals(1, lungProfileList1Long.getLungProfiles().size());
    }

    @Test
    void deleteLungProfile2LongAtStartIndex() {
        assertTrue(lungProfileList2Long.deleteLungProfile(lp1));
        assertEquals(1, lungProfileList2Long.getLungProfiles().size());
        assertFalse(lungProfileList2Long.getLungProfiles().contains(lp1));
    }

    @Test
    void deleteLungProfile2LongAtEndIndex() {
        assertTrue(lungProfileList2Long.deleteLungProfile(lp2));
        assertEquals(1, lungProfileList2Long.getLungProfiles().size());
        assertFalse(lungProfileList2Long.getLungProfiles().contains(lp2));
    }

    @Test
    void deleteLungProfile2LongNotInList() {
        assertFalse(lungProfileList2Long.deleteLungProfile(lp3));
        assertEquals(2, lungProfileList2Long.getLungProfiles().size());
    }
}
