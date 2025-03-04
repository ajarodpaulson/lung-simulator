package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PressureTimeScalarTest extends ScalarTimeTest {

    @Test
    @Override
    void testGetUnits() {
        assertEquals("cmH2O", st1.getUnits());
    }

    @Test
    @Override
    void testGetScalarName() {
        assertEquals("pressure", st1.getScalarName());
    }

    @Test
    @Override
    void testAmplitude() {
        assertEquals(1.5f, st1.calculateAmplitude(), TOL);
    }

    @Test
    @Override
    void testPhaseShift() {
        assertEquals((3.75f / 4), st1.calculatePhaseShift());
    }

    @Test
    @Override
    void testVertShift() {
        assertEquals((Math.abs(st1.tidalVolume / st1.compliance) / 2), st1.calculateVertShift(), TOL);
        assertEquals((Math.abs(st2.tidalVolume / st2.compliance) / 2), st2.calculateVertShift(), TOL);
    }

    @Test
    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0.0f), TOL);
        assertEquals(1.5f, st1.calculateScalarValueAtTimeInSeconds(3.75f / 4), TOL);
        assertEquals(3.0f, st1.calculateScalarValueAtTimeInSeconds(2.0f * 3.75f / 4), TOL);
        assertEquals(1.5f, st1.calculateScalarValueAtTimeInSeconds(3.0f * 3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(3.75f), TOL);
    }

    @Test
    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(st1.calculateAmplitude() * 2.0f, st1.calculateMaximumScalarValue(), TOL);
    }

    @Test
    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(0, st1.calculateMinimumScalarValue(), TOL);
    }

    @Test
    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new PressureTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

}
