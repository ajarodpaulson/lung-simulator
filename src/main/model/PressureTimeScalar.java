package model;

/**
 * Represents the metrics for the pressure versus time ventilator scalar which is 
 * derivable from ...
 * All method specifications are in ScalarTime
 */
public class PressureTimeScalar extends ScalarTime {

    protected PressureTimeScalar(int tidalVolume, int respRate, float compliance, float resistance) {
            super(tidalVolume, respRate, compliance, resistance);
        }
    
        @Override
    public String getUnits() {
        return "cmH2O";
    }

    @Override
    public String getScalarName() {
        return "pressure";
    }

    @Override
    public float calculateAmplitude() {
       return Math.abs(tidalVolume / compliance) / 2.0f;
    }

    @Override
    protected float calculatePhaseShift() {
        return (calculateBreathCycleTime() / 4.0f);
    }

    @Override
    protected float calculateVertShift() {
        return calculateAmplitude();
    }
    
}
