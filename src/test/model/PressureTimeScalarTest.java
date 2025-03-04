package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PressureTimeScalarTest extends ScalarTimeTest {
    

    @Test
    @Override
    void testGetUnits() {
        assertEquals("cmH2O", st1.getUnits());
    }

    @Override
    void testGetScalarName() {
        assertEquals("pressure", st1.getScalarName());
    }

    @Override
    void testAmplitude() {
        assertEquals(Math.abs(st1.compliance * st1.tidalVolume), st1.calculateAmplitude(), TOL);
    }

    @Override
    void testPhaseShift() {
        assertEquals(0, st1.calculatePhaseShift());
    }

    @Override
    void testVertShift() {
       assertEquals((Math.abs(st1.compliance * st1.tidalVolume) / 2), st1.calculateVertShift(), TOL);
       assertEquals((Math.abs(st2.compliance * st2.tidalVolume) / 2), st2.calculateVertShift(), TOL);
    }

    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0), TOL);
        assertEquals(st1.calculateAmplitude() / 2, st1.calculateScalarValueAtTimeInSeconds(st1.calculateBreathCycleTime() * 1 / 4), TOL);
        assertEquals(st1.calculateAmplitude(), st1.calculateScalarValueAtTimeInSeconds(st1.calculateBreathCycleTime() * 2 / 4), TOL);
        assertEquals(st1.calculateAmplitude() / 2, st1.calculateScalarValueAtTimeInSeconds(st1.calculateBreathCycleTime() * 3 / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(st1.calculateBreathCycleTime() * 4 / 4), TOL);

    }

    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(st1.calculateAmplitude(), st1.calculateMaximumScalarValue());
    }

    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(0, st1.calculateMinimumScalarValue());
    }

    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new PressureTimeScalar(tidalVolume, respRate, compliance, resistance);
    }
    
}
