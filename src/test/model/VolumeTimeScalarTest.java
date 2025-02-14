package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VolumeTimeScalarTest extends ScalarTimeTest {

    @Override
    protected VolumeTimeScalar createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new VolumeTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

    @Test
    @Override
    void testAmplitude() {
        assertEquals(150, st1.calculateAmplitude());
        assertEquals(450 / 2, st2.calculateAmplitude());
    }

    @Test
    @Override
    void testPhaseShift() {
        assertEquals(0, st1.calculatePhaseShift());
        assertEquals(0, st2.calculatePhaseShift());
    }

    @Test
    @Override
    void testVertShift() {
        assertEquals(150, st1.calculateVertShift());
        assertEquals(450 / 2, st2.calculateVertShift());
    }

    @Test
    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0.0f), TOL);
        assertEquals(150, st1.calculateScalarValueAtTimeInSeconds(3.75f / 4), TOL);
        assertEquals(300, st1.calculateScalarValueAtTimeInSeconds(2.0f * 3.75f / 4), TOL);
        assertEquals(150, st1.calculateScalarValueAtTimeInSeconds(3.0f * 3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(3.75f), TOL);
    }

    @Test
    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(300, st1.calculateMaximumScalarValue(), TOL);
    }

    @Test
    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(0, st1.calculateMinimumScalarValue(), TOL);
    }

    @Test
    @Override
    void testGetUnits() {
        assertEquals("mls", st1.getUnits());
    }

    @Test
    @Override
    void testGetScalarName() {
       assertEquals("volume", st1.getScalarName());
    }

}
