package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public abstract class ScalarTest extends TestLungProfiles {
   ScalarTime st1;
   ScalarTime st2;
   ScalarTime st3;

//    @BeforeEach 
//    void beforeEach() {
//     st1 = new ScalarTime(lp1.tidalVolume
//    }

    @Test
    abstract void testConstructor();


}
