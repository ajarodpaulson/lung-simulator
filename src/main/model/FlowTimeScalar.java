package model;

/*
 * Represents the metrics for the flow versus time ventilator scalar which is 
 * derivable from the tidal volume, respiratory rate, respiratory system compliance, and lung resistance.
 * 
 * All method specifications are in ScalarTime
 */
public class FlowTimeScalar extends ScalarTime {

    protected FlowTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
        super(tidalVolume, respRate, compliance, resistance);
    }

    @Override
    public String getUnits() {
        return "L/s";
    }

    @Override
    public String getScalarName() {
        return "flow";
    }

    // XXX design decision...
    @Override 
    protected float calculateScalarValueAtTimeInSeconds(float time) {
        return (float) (calculateAmplitude()
                * Math.cos(calculateConversionFactor() * (time - calculatePhaseShift())) + calculateVertShift());
    }

    @Override
    public float calculateAmplitude() {
        return (float) (-1.0f * this.tidalVolume / 1000 * Math.PI / calculateBreathCycleTime());
    }

    @Override
    protected float calculatePhaseShift() {
        return calculateBreathCycleTime() / 4.0f;
    }

    @Override
    protected float calculateVertShift() {
        return 0.0f;
    }

}
