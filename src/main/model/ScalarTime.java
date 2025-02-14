package model;

/* 
 * Represents a generic ventilator scalar with amplitude, horizontal stretch, 
 * phase shift, and vertical stretch
 */
public abstract class ScalarTime {
    protected int tidalVolume;
    protected int respRate;
    protected int compliance;
    protected float resistance;

    

    /*
     * EFFECTS: constructs a new ScalarTime with supplied tidal volume (ml),
     * (resp)iratory rate (breaths/min), compliance (ml/cmH2O), resistance
     * (cmH20/L/s)
     */
    // FIXME: change constructor so it can just take a lung profile? to simplify?
    protected ScalarTime(int tidalVolume, int respRate, int compliance, float resistance) {
        this.tidalVolume = tidalVolume;
        this.respRate = respRate;
        this.compliance = compliance;
        this.resistance = resistance;
    }

    /*
     * EFFECTS: calculates the amplitude of the waveform modelling the breathing
     * cycle
     */
    public abstract float calculateAmplitude();

    /*
     * EFFECTS: returns 2pi / total breath cycle time
     */
    protected float calculateConversionFactor() {
        return (float) (2 * Math.PI / calculateBreathCycleTime());
    }

    /*
     * EFFECTS: calculates the phase shift of the waveform modelling the breathing
     * cycle
     */
    protected abstract float calculatePhaseShift();

    /*
     * EFFECTS: calculates the vertical shift of the waveform modelling the
     * breathing cycle
     */
    protected abstract float calculateVertShift();

    /*
     * REQUIRES: time > 0 seconds
     * EFFECTS: calculates and returns the scalar value at the supplied time in
     * seconds
     * Output units determined by subclasses
     */
    protected float calculateScalarValueAtTimeInSeconds(float time) {
        return (float) (-1 * calculateAmplitude()
                * Math.cos(calculateConversionFactor() * (time - calculatePhaseShift())) + calculateVertShift());
    }

    /*
     * EFFECTS: calculates and returns the maximum value of the scalar-time function
     */
    public float calculateMaximumScalarValue() {
        return Math.abs(calculateAmplitude()) + calculateVertShift();
    }

    /*
     * EFFECTS: calculates and returns the minimum value of the scalar-time function
     */
    public float calculateMinimumScalarValue() {
        return calculateVertShift() - Math.abs(calculateAmplitude());
    }

    /*
     * EFFECTS: calculates and returns the period for the scalar-time function
     * jobs
     * take resp rate, which is in breaths per minute
     * breaths/min = breaths / 60s
     */
    public float calculateBreathCycleTime() {
        return 60.0f / this.getRespRate();
    }

    public abstract String getUnits();

    public abstract String getScalarName();

    // XXX I don't think I need any of these... this comes from the lung profile
    protected int getTidalVolume() {
        return tidalVolume;
    }

    protected void setTidalVolume(int tidalVolume) {
        this.tidalVolume = tidalVolume;
    }

    protected int getRespRate() {
        return respRate;
    }

    protected void setRespRate(int respRate) {
        this.respRate = respRate;
    }

    protected int getCompliance() {
        return compliance;
    }

    protected void setCompliance(int compliance) {
        this.compliance = compliance;
    }

    protected float getResistance() {
        return resistance;
    }

    protected void setResistance(float resistance) {
        this.resistance = resistance;
    }
}
