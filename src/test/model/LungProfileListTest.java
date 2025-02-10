package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LungProfileListTest extends TestLungProfiles {
    LungProfileList lungProfileList0Long;
    LungProfileList lungProfileList1Long;
    LungProfileList lungProfileList2Long;
    LungProfileList lungProfileList3Long;

    @BeforeEach 
    void runBefore() {

        lungProfileList0Long = new LungProfileList();
        
        lungProfileList1Long = new LungProfileList();
        lungProfileList1Long.addLungProfile(lp1);

        lungProfileList2Long = new LungProfileList();
        lungProfileList2Long.addLungProfile(lp1);
        lungProfileList2Long.addLungProfile(lp2);

        lungProfileList3Long = new LungProfileList();
        lungProfileList3Long.addLungProfile(lp2);
        lungProfileList3Long.addLungProfile(lp1);
        lungProfileList3Long.addLungProfile(lp3);
    }

    @Test
    void testConstructor() {
        assertTrue(lungProfileList0Long.getLungProfiles().isEmpty());
    }

    /*
     * label     |     list of lung profiles
     * in list           0,1,2
     * not in list       position of supplied label (beginning and end)
     */
    @Test
    void testSelectLungProfile1LongFound() {
        assertEquals(lp1, lungProfileList1Long.selectLungProfile("COPD").get());
    }

    @Test
    void testSelectLungProfile1LongNotFound() {
        assertFalse(lungProfileList1Long.selectLungProfile("Fibrosis").isPresent());
    }

    @Test
    void testSelectLungProfile2LongFoundAtStartIndex() {
        assertEquals(lp1, lungProfileList2Long.selectLungProfile("COPD").get());
    }

    @Test 
    void testSelectLungProfile2LongFoundAtEndIndex() {
        assertEquals(lp2, lungProfileList2Long.selectLungProfile("Asthma").get());
    }

    @Test 
    void testSelectLungProfile2LongNotFound() {
        assertFalse(lungProfileList2Long.selectLungProfile("Daenerys Targaryen").isPresent());
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
    void deleteLungProfile1Long() {
        lungProfileList1Long.deleteLungProfile(lp1.getLabel());
        assertTrue(lungProfileList1Long.getLungProfiles().isEmpty());
    }

    @Test
    void deleteLungProfile2LongAtStartIndex() {
        lungProfileList2Long.deleteLungProfile(lp1.getLabel());
        assertEquals(1, lungProfileList2Long.getLungProfiles().size());
        assertFalse(lungProfileList2Long.getLungProfiles().contains(lp1));
    }

    @Test
    void deleteLungProfile2LongAtEndIndex() {
        lungProfileList2Long.deleteLungProfile(lp2.getLabel());
        assertEquals(1, lungProfileList2Long.getLungProfiles().size());
        assertFalse(lungProfileList2Long.getLungProfiles().contains(lp2));
    }



}
