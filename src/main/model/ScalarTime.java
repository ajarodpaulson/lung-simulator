package model;

/* 
 * Represents a generic ventilator scalar with amplitude, horizontal stretch, 
 * phase shift, and vertical stretch
 */
public abstract class ScalarTime {
    protected int amplitude; 
    protected int horStretch; 
    protected final int phaseShift = 0;
    protected int vertShift; 

    private int tidalVolume;
    private int respRate;
    private int compliance;
    private int resistance;

    /*
     * EFFECTS: constructs a new ScalarTime with supplied tidal volume (ml), 
     * (resp)iratory rate (breaths/min), compliance (ml/cmH2O), resistance (cmH20/L/s)
     * XXX how do you write effects clause for constructor of an abstract class?
     */
    protected ScalarTime(int tidalVolume, int respRate, int compliance, float resistance) {
        // TODO
    }

    protected int getAmplitude() {
        return amplitude;
    }

    protected void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    /*
     * EFFECTS: calculates the amplitude of the waveform modelling the breathing cycle
     */
    protected abstract void calculateAmplitude();

    protected int getHorStretch() {
        return horStretch;
    }

    protected void setHorStretch(int horStretch) {
        this.horStretch = horStretch;
    }

    /*
     * EFFECTS: calculates the horizontal stretch of the waveform modelling the breathing cycle
     */
    protected abstract void calculateHorStretch();

    protected int getVertShift() {
        return vertShift;
    }

    protected void setVertShift(int vertShift) {
        this.vertShift = vertShift;
    }

    /*
     * EFFECTS: calculates the horizontal stretch of the waveform modelling the breathing cycle
     */
    protected abstract void calculateVertStretch();

    /*
     * REQUIRES: time > 0 seconds
     * EFFECTS: calculates and returns the scalar value at the supplied time in seconds (decimals ok)
     */
    protected float calculateScalarValueAtTimeInSeconds (float time) {
        // TODO
        return 0.0f;
    }

    /*
     * EFFECTS: returns the maximum value of the scalar-time function
     */
    protected int getMaximumScalarValue() {
        // TODO
        return 0;
    }
    
    /*
     * EFFECTS: returns the minimum value of the scalar-time function
     */
    protected int getMinimumScalarValue() {
        // TODO
        return 0;
    }

    /*
     * EFFECTS: returns the period for the scalar-time function
     */
    protected float getBreathCycleTime() {
        // TODO
        return 0.0f;
    }
}
