package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ScalarTimeTest {
    protected ScalarTime st1;
    protected ScalarTime st2;

    static float TOL = 0.1f;

    // EFFECTS: helper method for subclasses to create new instances
    protected abstract ScalarTime createScalarTimeInstance(
            int tidalVolume, int respRate, int compliance, float resistance);
    /*
     * XXX I don't want to be able to instantiate ScalarTime because it doesn't make
     * sense to...
     * yet, I do want there to be some common objects for each subclass to test with
     */

    @BeforeEach
    void beforeEach() {
        st1 = createScalarTimeInstance(300, 16, 100, 1.0f);
        st2 = createScalarTimeInstance(450, 20, 75, 2.0f);
    }

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

    /*
     * XXX is this a reasonable thing to do? can't think of edge cases or anything,
     * but do want to enforce that
     * subclasses will test these general behaviours
     */
    
    abstract void testAmplitude();
    
    @Test
    void testConversionFactor() {
        assertEquals(Math.PI* 2 / st1.calculateBreathCycleTime(), st1.calculateConversionFactor(), TOL);
        assertEquals(Math.PI* 2 / st2.calculateBreathCycleTime(), st2.calculateConversionFactor(), TOL);
    }
    
    abstract void testPhaseShift();

    abstract void testVertShift();

    abstract void testCalculateScalarValueAtTimeInSeconds();

    abstract void testCalculateMaximumScalarValue();

    abstract void testCalculateMinimumScalarValue();

}
