package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlowTimeScalarTest extends ScalarTimeTest {

    @Override
    void testGetUnits() {
        assertEquals("L/s", st1.getUnits());
    }

    @Override
    void testGetScalarName() {
        assertEquals("flow", st1.getScalarName());
    }

    @Override
    void testAmplitude() {
        assertEquals(80 * Math.PI, st1.calculateAmplitude());
        assertEquals(450 * Math.PI / st2.getTidalVolume(), st2.calculateAmplitude());
    }

    @Override
    void testPhaseShift() {
        assertEquals(0, st1.calculatePhaseShift());
        assertEquals(0, st2.calculatePhaseShift());
    }

    @Override
    void testVertShift() {
        assertEquals(0, st1.calculateVertShift());
        assertEquals(0, st2.calculateVertShift());
    }

    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0.0f), TOL);
        assertEquals(80 * Math.PI / 2, st1.calculateScalarValueAtTimeInSeconds(3.75f / 4), TOL);
        assertEquals(80 * Math.PI, st1.calculateScalarValueAtTimeInSeconds(2.0f * 3.75f / 4), TOL);
        assertEquals(80 * Math.PI / 2, st1.calculateScalarValueAtTimeInSeconds(3.0f * 3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(3.75f), TOL);
    }

    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(80 * Math.PI, st1.calculateMaximumScalarValue());
        assertEquals(450 * Math.PI / st2.getTidalVolume(), st2.calculateMaximumScalarValue());
    }

    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(-1 * 80 * Math.PI, st1.calculateMinimumScalarValue());
        assertEquals(- 1 * 450 * Math.PI / st2.getTidalVolume(), st2.calculateMinimumScalarValue());   
    }

    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new FlowTimeScalar(tidalVolume, respRate, compliance, resistance);
    }
    
}
