package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LungProfileTest {
    
    LungProfile lp1;
    LungProfile lp2;
    LungProfile lp3;
    LungProfile lp4;
    LungProfile lp5;
    LungProfile lp6;
    LungProfile lp7;
    LungProfile lp8;
    
    @BeforeEach
    void runBefore() {
        lp1 = new LungProfile("COPD", 158.0f, LungProfile.Sex.FEMALE, 100, 1.0f);
        lp2 = new LungProfile(180.0f, LungProfile.Sex.MALE, 120, 2.0f);

        lp3 = new LungProfile("Asthma", 152.3f, LungProfile.Sex.MALE, 100, 1.0f);
        lp4 = new LungProfile(152.4f, LungProfile.Sex.MALE, 120, 2.0f);
        lp5 = new LungProfile("COPD", 152.5f, LungProfile.Sex.MALE, 100, 1.0f);
        
        lp6 = new LungProfile(152.3f, LungProfile.Sex.FEMALE, 120, 2.0f);
        lp7 = new LungProfile("COPD", 152.4f, LungProfile.Sex.FEMALE, 100, 1.0f);
        lp8 = new LungProfile(152.5f, LungProfile.Sex.FEMALE, 120, 2.0f);
    }

    @Test
    void testConstructorWithLabel() {
        assertEquals("COPD", lp1.getLabel());
        assertEquals(158, lp1.getHeight());
        assertEquals(LungProfile.Sex.FEMALE, lp1.getSex());
        assertEquals(100, lp1.getCompliance());
        assertEquals(1.0f, lp1.getResistance());
    }

    @Test
    void testConstructorWithNoLabel() {
        assertNull(lp2.getLabel());
        assertEquals(180, lp1.getHeight());
        assertEquals(LungProfile.Sex.FEMALE, lp1.getSex());
        assertEquals(100, lp1.getCompliance());
        assertEquals(2.0f, lp1.getResistance());
    }

    @Test
    void testSetters() {
        lp1.setLabel("My Lung Profile");
        lp1.setHeight(122);
        lp1.setSex(LungProfile.Sex.MALE);
        lp1.setCompliance(50);
        lp1.setResistance(1.5f);
    }

    @Test
    void testCalculateIBWFemale() {
        assertEquals((45.5 + 0.91 * (158.0f - 152.4f)), lp1.getIBW());
    }

    @Test
    void testCalculateIBWMale() {
        assertEquals((50 + 0.91 * (180.0f - 152.4f)), lp2.getIBW());
    }

    @Test
    void testCalculateIBWMaleBelowBoundary() {
        assertEquals((50 + 0.91 * (152.4f - 152.4f)), lp3.getIBW());
    }

    @Test
    void testCalculateIBWMaleOnBoundary() {
        assertEquals((50 + 0.91 * (152.4f - 152.4f)), lp4.getIBW());
    }

    @Test
    void testCalculateIBWMaleAboveBoundary() {
        assertEquals((50 + 0.91 * (152.5f - 152.4f)), lp5.getIBW());
    }

    @Test
    void testCalculateIBWFemaleBelowBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp6.getIBW());
    }

    @Test
    void testCalculateIBWFemaleOnBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp7.getIBW());
    }

    @Test
    void testCalculateIBWFemaleAboveBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp8.getIBW());
    }
}
