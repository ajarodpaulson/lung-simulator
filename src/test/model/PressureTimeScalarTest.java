package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PressureTimeScalarTest extends ScalarTimeTest {

    @Override
    void testGetUnits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testGetUnits'");
    }

    @Override
    void testGetScalarName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testGetScalarName'");
    }

    @Override
    void testAmplitude() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testAmplitude'");
    }

    @Override
    void testPhaseShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPhaseShift'");
    }

    @Override
    void testVertShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testVertShift'");
    }

    @Override
    void testCalculateScalarValueAtTimeInSeconds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCalculateScalarValueAtTimeInSeconds'");
    }

    @Override
    void testCalculateMaximumScalarValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCalculateMaximumScalarValue'");
    }

    // @Override
    // void testCalculateMinimumScalarValue() {
    //     assertEquals((float) 300 / 100  + <flow> * st1.getResistance(),
    //     st1.calculateMinimumScalarValue())
    // }

    @Override
    protected ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance) {
        return new PressureTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

    @Override
    void testCalculateMinimumScalarValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCalculateMinimumScalarValue'");
    }
    
}
