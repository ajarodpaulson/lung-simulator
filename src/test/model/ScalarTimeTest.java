package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ScalarTimeTest {
    protected ScalarTime st1;
    protected ScalarTime st2;

    static float TOL = 0.1f;

    @BeforeEach
    void beforeEach() {
        st1 = createScalarTimeInstance(300, 16, 100, 1.0f);
        st2 = createScalarTimeInstance(450, 20, 75, 2.0f);
    }

    abstract void testGetUnits();

    abstract void testGetScalarName();

    abstract void testAmplitude();

    abstract void testPhaseShift();

    abstract void testVertShift();

    abstract void testCalculateScalarValueAtTimeInSeconds();

    abstract void testCalculateMaximumScalarValue();

    abstract void testCalculateMinimumScalarValue();

    @Test
    void testConstructor() {
        assertEquals(300, st1.getTidalVolume());
        assertEquals(16, st1.getRespRate());
        assertEquals(100, st1.getCompliance());
        assertEquals(1.0f, st1.getResistance(), TOL);
    }

    @Test
    void testSetters() {
        st1.setTidalVolume(275);
        assertEquals(275, st1.getTidalVolume());
        st1.setRespRate(35);
        assertEquals(35, st1.getRespRate());
        st1.setCompliance(180);
        assertEquals(180, st1.getCompliance());
        st1.setResistance(0.8f);
        assertEquals(0.8f, st1.getResistance(), TOL);
    }

    @Test
    void testConversionFactor() {
        assertEquals(Math.PI * 2 / st1.calculateBreathCycleTime(), st1.calculateConversionFactor(), TOL);
        assertEquals(Math.PI * 2 / st2.calculateBreathCycleTime(), st2.calculateConversionFactor(), TOL);
    }

    /*
     * MODIFIES: this
     * EFFECTS: helper method for subclasses to create new instances
     */
    protected abstract ScalarTime createScalarTimeInstance(
            int tidalVolume, int respRate, int compliance, float resistance);
}
