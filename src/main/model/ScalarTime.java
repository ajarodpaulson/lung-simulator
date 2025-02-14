package model;

/* 
 * Represents the metrics for a generic ventilator scalar versus time which is 
 * derivable from the tidal volume, respiratory rate, respiratory system compliance, and lung resistance.
 */
public abstract class ScalarTime {
    protected int tidalVolume;
    protected int respRate;
    protected int compliance;
    protected float resistance;

    /*
     * EFFECTS: constructs a new ScalarTime instance using the supplied tidal volume
     * (ml),
     * (resp)iratory rate (breaths/min), compliance (ml/cmH2O), and resistance
     * (cmH20/L/s)
     */
    protected ScalarTime(int tidalVolume, int respRate, int compliance, float resistance) {
        this.tidalVolume = tidalVolume;
        this.respRate = respRate;
        this.compliance = compliance;
        this.resistance = resistance;
    }

    /*
     * EFFECTS: returns the units as a String for the extending class
     */
    public abstract String getUnits();

    public abstract String getScalarName();

    /*
     * EFFECTS: calculates the amplitude of the scalar versus time waveform
     */
    public abstract float calculateAmplitude();

    /*
     * EFFECTS: calculates the phase shift of the scalar versus time waveform
     */
    protected abstract float calculatePhaseShift();

    /*
     * EFFECTS: calculates the vertical shift of the scalar versus time waveform
     */
    protected abstract float calculateVertShift();

    /*
     * EFFECTS: returns 2π / breath cycle time, which is the conversion factor in
     * the sinusoidal
     * scalar versus time function
     */
    protected float calculateConversionFactor() {
        return (float) (2 * Math.PI / calculateBreathCycleTime());
    }

    /*
     * REQUIRES: time > 0 seconds
     * EFFECTS: calculates and returns the scalar value at the supplied time in
     * seconds
     * Output units are determined by subclasses
     */
    protected float calculateScalarValueAtTimeInSeconds(float time) {
        return (float) (calculateAmplitude()
                * Math.sin(calculateConversionFactor() * (time - calculatePhaseShift())) + calculateVertShift());
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
     */
    public float calculateBreathCycleTime() {
        return 60.0f / this.getRespRate();
    }

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
