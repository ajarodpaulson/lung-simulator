package model;

/**
 * Represents the metrics for the pressure versus time ventilator scalar which is 
 * derivable from ...
 * All method specifications are in ScalarTime
 */
public class PressureTimeScalar extends ScalarTime {

    protected PressureTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
            super(tidalVolume, respRate, compliance, resistance);
        }
    
        @Override
    public String getUnits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnits'");
    }

    @Override
    public String getScalarName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScalarName'");
    }

    @Override
    public float calculateAmplitude() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateAmplitude'");
    }

    @Override
    protected float calculatePhaseShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculatePhaseShift'");
    }

    @Override
    protected float calculateVertShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateVertShift'");
    }
    
}
