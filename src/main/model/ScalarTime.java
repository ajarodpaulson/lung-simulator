package model;

/* 
 * Represents a generic ventilator scalar with amplitude, horizontal stretch, 
 * phase shift, and vertical stretch
 */
public abstract class ScalarTime {
    protected int amplitude; 
    protected int horStretch; 
    protected int phaseShift;
    protected int vertShift; 

    /*
     * EFFECTS: constructs a new ScalarTime with supplied a (amplitude), hs (horizontal stretch),
     * ps (phase shift), and vs (vertical stretch)
     * Parameter units defined by subclassing types
     * XXX how do you write effects clause for constructor of an abstract class?
     */
    protected ScalarTime(int a, int hs, int ps, int vs) {
        // TODO
    }

    protected int getAmplitude() {
        return amplitude;
    }

    protected void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    protected int getHorStretch() {
        return horStretch;
    }

    protected void setHorStretch(int horStretch) {
        this.horStretch = horStretch;
    }

    protected int getPhaseShift() {
        return phaseShift;
    }

    protected void setPhaseShift(int phaseShift) {
        this.phaseShift = phaseShift;
    }

    protected int getVertShift() {
        return vertShift;
    }

    protected void setVertShift(int vertShift) {
        this.vertShift = vertShift;
    }

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
