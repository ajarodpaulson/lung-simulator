package model;

public class VolumeTimeScalar extends ScalarTime {

    public VolumeTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
        super(tidalVolume, respRate, compliance, resistance);
    }

    // EFFECTS: returns the tidal volume of this
    @Override
    public float calculateAmplitude() {
        return this.tidalVolume / 2.0f;
    }

    // EFFECTS: returns 0
    @Override
    protected float calculatePhaseShift() {
        return 0.0f;
    }

    // EFFECTS: returns vertical shift of this
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
