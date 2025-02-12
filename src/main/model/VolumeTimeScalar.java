package model;

public class VolumeTimeScalar extends ScalarTime {

    public VolumeTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
        super(tidalVolume, respRate, compliance, resistance);
    }

    // EFFECTS: returns the tidal volume of this
    @Override
    protected float calculateAmplitude() {
        return this.tidalVolume / 2;
    }

    // EFFECTS: returns 0
    @Override
    protected float calculatePhaseShift() {
       return 0.0f;
    }

    // EFFECTS: returns 0
    @Override
    protected float calculateVertShift() {
       return calculateAmplitude();
    }

}
