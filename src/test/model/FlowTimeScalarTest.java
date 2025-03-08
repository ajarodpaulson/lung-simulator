package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlowTimeScalarTest extends ScalarTimeTest {

    @Test
    @Override
    void testGetUnits() {
        assertEquals("L/s", st1.getUnits());
    }

    @Test
    @Override
    void testGetScalarName() {
        assertEquals("flow", st1.getScalarName());
    }

    @Test
    @Override
    void testAmplitude() {
        assertEquals(-1.0f * 80 * Math.PI / 1000, st1.calculateAmplitude(), TOL);
        assertEquals((-1.0f * 450 * Math.PI  / st2.calculateBreathCycleTime()) / 1000, st2.calculateAmplitude(), TOL);
    }

    @Test
    @Override
    void testPhaseShift() {
        assertEquals(3.75f / 4.0f, st1.calculatePhaseShift());
        assertEquals(3.0f / 4.0f, st2.calculatePhaseShift());
    }

    @Test
    @Override
    void testVertShift() {
        assertEquals(0, st1.calculateVertShift());
        assertEquals(0, st2.calculateVertShift());
    }

    @Test
    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0.0f), TOL);
        assertEquals(-1.0f * 0.3f * Math.PI / 3.75f, st1.calculateScalarValueAtTimeInSeconds(3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(2.0f * 3.75f / 4), TOL);
        assertEquals(0.3f * Math.PI / 3.75f, st1.calculateScalarValueAtTimeInSeconds(3.0f * 3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(3.75f), TOL);
    }

    @Test
    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(80 * Math.PI / 1000, st1.calculateMaximumScalarValue(), TOL);
        assertEquals(450 * Math.PI / st2.calculateBreathCycleTime() / 1000, st2.calculateMaximumScalarValue(), TOL);
    }

    @Test
    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(-1 * 80 * Math.PI / 1000, st1.calculateMinimumScalarValue(), TOL);
        assertEquals(- 1 * 0.45f * Math.PI / st2.calculateBreathCycleTime(), st2.calculateMinimumScalarValue(), TOL);   
    }

    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new FlowTimeScalar(tidalVolume, respRate, compliance, resistance);
    }
    
}
