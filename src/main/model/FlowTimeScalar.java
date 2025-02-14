package model;

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

    @Override
    public float calculateAmplitude() {
        return (float) (this.tidalVolume * Math.PI / calculateBreathCycleTime());
    }

    @Override
    protected float calculatePhaseShift() {
        return 0.0f;
    }

    @Override
    protected float calculateVertShift() {
        return 0.0f;
    }

}
