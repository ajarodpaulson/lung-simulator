package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LungProfileTest extends TestLungProfiles {

    static float TOL = 0.1f;

    @BeforeEach
    void runBefore() {

    }

    @Test
    void testConstructorWithLabel() {
        assertEquals("COPD", lp1.getLabel());
        assertEquals(158, lp1.getHeight());
        assertEquals(LungProfile.Sex.FEMALE, lp1.getSex());
        assertEquals(100, lp1.getCompliance());
        assertEquals(350, lp1.getTidalVolume());
        assertEquals(16, lp1.getRespRate());
        assertEquals(1.0f, lp1.getResistance());
        assertEquals((45.5 + 0.91 * (158.0f - 152.4f)), lp1.getIBW(), TOL);
    }

    @Test
    void testConstructorWithNoLabel() {
        assertNull(lp4.getLabel());
        assertEquals(152.4f, lp4.getHeight(), TOL);
        assertEquals(LungProfile.Sex.MALE, lp4.getSex());
        assertEquals(120, lp4.getCompliance());
        assertEquals(300, lp4.getTidalVolume());
        assertEquals(30, lp4.getRespRate());
        assertEquals(2.0f, lp4.getResistance(), TOL);
        assertEquals((50 + 0.91 * (152.4f - 152.4f)), lp4.getIBW(), TOL);
    }

    @Test
    void testSetters() {
        lp1.setLabel("My Lung Profile");
        assertEquals("My Lung Profile", lp1.getLabel());
        lp1.setHeight(192.0f);
        assertEquals(192.0f, lp1.getHeight());
        lp1.setSex(LungProfile.Sex.MALE);
        assertEquals(LungProfile.Sex.MALE, lp1.getSex());
        lp1.setTidalVolume(390);
        assertEquals(390, lp1.getTidalVolume());
        lp1.setRespRate(20);
        assertEquals(20, lp1.getRespRate());
        lp1.setCompliance(50);
        assertEquals(50, lp1.getCompliance());
        lp1.setResistance(1.5f);
        assertEquals(1.5f, lp1.getResistance());
        assertEquals((50 + 0.91 * (192.0f - 152.4f)), lp1.getIBW(), TOL);
    }

    // XXX can implement an equals method later for the LungProfile class
    @Test
    void testGetVolumeTimeScalar() {
        VolumeTimeScalar vtScalar1 = new VolumeTimeScalar(
                lp1.getTidalVolume(), lp1.getRespRate(), lp1.getCompliance(), lp1.getResistance());
        VolumeTimeScalar vtScalar2 = lp1.getVolumeTimeScalar();
        assertEquals(vtScalar1.calculateAmplitude(), vtScalar2.calculateAmplitude());
        assertEquals(vtScalar1.calculateBreathCycleTime(), vtScalar2.calculateBreathCycleTime());
        assertEquals(vtScalar1.calculateMaximumScalarValue(), vtScalar2.calculateMaximumScalarValue());
        assertEquals(vtScalar1.calculateMinimumScalarValue(), vtScalar2.calculateMinimumScalarValue());
    }

    @Test
    void testCalculateIdealBWFemale() {
        assertEquals((45.5 + 0.91 * (158.0f - 152.4f)), lp1.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWMale() {
        assertEquals((50 + 0.91 * (180.0f - 152.4f)), lp2.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWMaleBelowBoundary() {
        assertEquals((50 + 0.91 * (152.4f - 152.4f)), lp3.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWMaleOnBoundary() {
        assertEquals((50 + 0.91 * (152.4f - 152.4f)), lp4.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWMaleAboveBoundary() {
        assertEquals((50 + 0.91 * (152.5f - 152.4f)), lp5.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWFemaleBelowBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp6.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWFemaleOnBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp7.getIBW(), TOL);
    }

    @Test
    void testCalculateIdealBWFemaleAboveBoundary() {
        assertEquals((45.5 + 0.91 * (152.4f - 152.4f)), lp8.getIBW(), TOL);
    }
}
