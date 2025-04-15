package model;

public class Scalars {
    private LungProfile lp;
    private ScalarTime principalScalar;
    private int time;

    public Scalars(int time, ScalarTime principalScalar) {
        this.time = time;
        this.principalScalar = principalScalar;
        /* 
         * the "way" that a given person breathes belongs in lung profile... but then I have semantic coupling 
         * becuase depending on which scalar is my principal, i need a different equation. 
         * 
         * ok, so i added principal scalar to the constructor so you at least need to think about which one you're going to use
         * and perhaps i can instantiate scalar...
         * 
         * i need the equationa and the principal scalar to be in sync
         */
        /*
         * 
         */

         /*
          * spontaneously breathing
          -different functions... cheyne stokes, functions that introduce some random behaviour
          mechanically ventilated
          pressure based: would then want pressure time scalar to be the principal scalar and derive the other 2 from it
          volume based: would then want volume/flow time scalar to be the principal scalar and derive the other 2 from it
          */
    }

    public float getFlow() {

    }

    public float getVolume() {

    }
    
    public float getPressure() {

    }
}
