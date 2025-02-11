package model;

/* 
 * Represents a generic ventilator scalar with amplitude, horizontal stretch, 
 * phase shift, and vertical stretch
 */
public abstract class ScalarTime {
    private float amplitude; 
    private float horStretch; 
    private float phaseShift;
    private float vertShift; 

    protected int tidalVolume;
    protected int respRate;
    protected int compliance;
    protected float resistance;

    /*
     * EFFECTS: constructs a new ScalarTime with supplied tidal volume (ml), 
     * (resp)iratory rate (breaths/min), compliance (ml/cmH2O), resistance (cmH20/L/s)
     * XXX how do you write effects clause for constructor of an abstract class?
     */
    protected ScalarTime(int tidalVolume, int respRate, int compliance, float resistance) {
        // TODO
    }

    protected float getAmplitude() {
        return this.amplitude;
    }

    protected void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    /*
     * EFFECTS: calculates the amplitude of the waveform modelling the breathing cycle
     */
    protected abstract void calculateAmplitude();

    protected float getHorStretch() {
        return this.horStretch;
    }

    protected void setHorStretch(float horStretch) {
        this.horStretch = horStretch;
    }

    /*
     * EFFECTS: calculates the horizontal stretch of the waveform modelling the breathing cycle
     */
    private void calculateHorStretch() {
        // TODO
    }

    protected float getPhaseShift() {
        return this.phaseShift;
    }

    protected void setPhaseShift(float phaseShift) {
        this.phaseShift = phaseShift;
    }

    /*
     * EFFECTS: calculates the phase shift of the waveform modelling the breathing cycle
     */
    protected abstract void calculatePhaseShift();


    protected float getVertShift() {
        return this.vertShift;
    }

    protected void setVertShift(float vertShift) {
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
     * EFFECTS: calculates and returns the maximum value of the scalar-time function
     */
    protected int calculateMaximumScalarValue() {
        // TODO
        return 0;
    }
    
    /*
     * EFFECTS: calculates and returns the minimum value of the scalar-time function
     */
    protected int calculateMinimumScalarValue() {
        // TODO
        return 0;
    }

    /*
     * EFFECTS: calculates and returns the period for the scalar-time function
     */
    protected float calculateBreathCycleTime() {
        // TODO
        return 0.0f;
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
