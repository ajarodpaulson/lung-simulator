package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.LungProfile;
import model.TestLungProfiles;
import model.LungProfile.Sex;

/*
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonTest extends TestLungProfiles {
    protected void checkLungProfile(String label, float height, Sex sex, int tidalVolume, int respRate, int compliance, float resistance, LungProfile lp) {
        assertEquals(label, lp.getLabel());
        assertEquals(height, lp.getHeight());
        assertEquals(sex, lp.getSex());
        assertEquals(tidalVolume, lp.getTidalVolume());
        assertEquals(respRate, lp.getRespRate());
        assertEquals(compliance, lp.getCompliance());
        assertEquals(resistance, lp.getResistance());
    }
}
