package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VolumeTimeScalarTest extends ScalarTimeTest {

    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new VolumeTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

    @Override
    void testAmplitude() {
        st1.setAmplitude(st1.calculateAmplitude());
        assertEquals(300, st1.getAmplitude());
        st2.setAmplitude(st2.calculateAmplitude());
        assertEquals(450, st2.getAmplitude());
    }

    @Override
    void testPhaseShift() {
        st1.setPhaseShift(st1.calculatePhaseShift());
        assertEquals(0, st1.getPhaseShift());
        st2.setPhaseShift(st2.calculatePhaseShift());
        assertEquals(0, st2.getPhaseShift());
    }

    @Override
    void testVertShift() {
        st1.setVertShift(st1.calculateVertShift());
        assertEquals(0, st1.getVertShift());
        st2.setVertShift(st2.calculateVertShift());
        assertEquals(0, st2.getVertShift());
    }

    @Test
    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(0.0f), TOL);
        assertEquals(300, st1.calculateScalarValueAtTimeInSeconds(3.75f / 4), TOL);
        assertEquals(0, st1.calculateScalarValueAtTimeInSeconds(2.0f * 3.75f / 4), TOL);
        assertEquals(300, st1.calculateScalarValueAtTimeInSeconds(3.0f * 3.75f / 4), TOL);
    }

    @Test
    @Override
    void testCalculateMaximumScalarValue() {
        assertEquals(300, st1.calculateMaximumScalarValue(), TOL);
    }

    @Test
    @Override
    void testCalculateMinimumScalarValue() {
        assertEquals(0, st1.calculateMaximumScalarValue(), TOL);
    }

}
