package model;

public class FlowTimeScalar extends ScalarTime {

    protected FlowTimeScalar(int tidalVolume, int respRate, int compliance, float resistance) {
            super(tidalVolume, respRate, compliance, resistance);
            //TODO Auto-generated constructor stub
        }
    
        @Override
    public String getUnits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnits'");
    }

    @Override
    public String getScalarName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScalarName'");
    }

    @Override
    public float calculateAmplitude() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateAmplitude'");
    }

    @Override
    protected float calculatePhaseShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculatePhaseShift'");
    }

    @Override
    protected float calculateVertShift() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateVertShift'");
    }

}
