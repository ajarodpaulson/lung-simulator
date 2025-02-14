package model;

/*
 * Represents the metrics for the pressure versus time ventilator scalar which is 
 * derivable from the tidal volume, respiratory rate, respiratory system compliance, and lung resistance.
 */
public class PressureTimeScalar extends ScalarTime {

    protected PressureTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
            super(tidalVolume, respRate, compliance, resistance);
            //TODO Auto-generated constructor stub
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