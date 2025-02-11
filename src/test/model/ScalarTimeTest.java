package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public abstract class ScalarTimeTest {
   protected ScalarTime st1;
   protected ScalarTime st2;
   
   float TOL = 0.1f;

   protected abstract ScalarTime createScalarTimeInstance(int tidalVolume, int respRate, int compliance, float resistance);
   /*
    * XXX I don't want to be able to instantiate ScalarTime because it doesn't make sense to...
    * yet, I do want there to be some common objects for each subclass to test with
    */
    @BeforeEach
    void beforeEach() {
        st1 = createScalarTimeInstance(300, 16, 10, 1.0f);
        st2 = createScalarTimeInstance(450, 20, 15, 2.0f);
    }

    @Test
    abstract void testConstructor();

    @Test 
    void testSetters(){
        st1.setTidalVolume(275);
        assertEquals(275, st1.getCompliance());
        st1.setRespRate(35);
        assertEquals(35, st1.getRespRate());
        st1.setCompliance(180);
        assertEquals(180, st1.getCompliance());
        st1.setResistance(0.8f);
        assertEquals(0.8f, st1.getResistance(), TOL);
    }

    /*
     * XXX is this a reasonable thing to do? can't think of edge cases or anything, but do want to enforce that
     * subclasses will test these general behaviours
     */
    @Test
    abstract void testAmplitude();

    @Test
    abstract void testHorStretch();

    @Test
    abstract void testPhaseShift();

    @Test
    abstract void testVertShift();

    @Test
    abstract void testCalculateScalarValueAtTimeInSeconds();

    @Test
    abstract void testCalculateMaximumScalarValue();

    @Test
    abstract void testCalculateMinimumScalarValue();

}
