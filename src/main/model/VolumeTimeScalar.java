package model;

/*
 * Represents the metrics for the volume versus time ventilator scalar which is 
 * derivable from the tidal volume, respiratory rate, respiratory system compliance, and lung resistance.
 * All method specifications are in ScalarTime
 */
public class VolumeTimeScalar extends ScalarTime {

    public VolumeTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
        super(tidalVolume, respRate, compliance, resistance);
    }

    @Override
    public float calculateAmplitude() {
        return this.tidalVolume / 2.0f;
    }

    @Override
    protected float calculatePhaseShift() {
        return calculateBreathCycleTime() / 4.0f;
    }

    @Override
    protected float calculateVertShift() {
        return calculateAmplitude();
    }

    @Override
    public String getUnits() {
        return "mls";
    }

    @Override
    public String getScalarName() {
        return "volume";
    }
}
