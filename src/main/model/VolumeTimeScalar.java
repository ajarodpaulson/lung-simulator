package model;

public class VolumeTimeScalar extends ScalarTime {

    public VolumeTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
        super(tidalVolume, respRate, compliance, resistance);
        // TODO Auto-generated constructor stub
    }

    // EFFECTS: returns the tidal volume of this
    @Override
    protected void calculateAmplitude() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateAmplitude'");
    }

    // EFFECTS: returns 0
    @Override
    protected void calculatePhaseShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculatePhaseShift'");
    }

    // EFFECTS: returns 0
    @Override
    protected void calculateVertShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateVertStretch'");
    }

    // EFFECTS: returns 2pi / total breath cycle time
    @Override
    protected void calculateConversionFactor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateHorStretch'");
    }

}
