package persistence;

import org.junit.jupiter.api.Test;

import model.LungProfile;
import model.LungProfile.Sex;
import model.LungProfileManager;
import model.exception.InvalidArgumentException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterGivenInvalidFileShouldThrowIOException() {
        try {
            LungProfileManager lpm = new LungProfileManager("My lung profile manager");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterGivenEmptyLungProfileManagerShouldSucceed() {
        try {
            LungProfileManager lpm = new LungProfileManager("My lung profile manager");
            JsonWriter writer = new JsonWriter("src/main/data/testWriterEmptyLungProfileManager.json");
            writer.open();
            writer.write(lpm);
            writer.close();

            JsonReader reader = new JsonReader("src/main/data/testWriterEmptyLungProfileManager.json");
            lpm = reader.read();
            assertEquals("My lung profile manager", lpm.getName());
            assertEquals(0, lpm.numLungProfiles());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGivenGeneralLungProfileManagerShouldSucceed() {
        try {
            LungProfileManager lpm = new LungProfileManager("Jarod's Lung Profiles");
            lpm.addLungProfile(lp1);
            lpm.addLungProfile(lp2);
            lpm.addLungProfile(lp3);
            JsonWriter writer = new JsonWriter("src/main/data/testWriterGeneralLungProfileManager.json");
            writer.open();
            writer.write(lpm);
            writer.close();

            JsonReader reader = new JsonReader("src/main/data/testWriterGeneralLungProfileManager.json");

            lpm = reader.read();
            assertEquals("Jarod's Lung Profiles", lpm.getName());
            List<LungProfile> lpList = lpm.getLungProfiles();
            assertEquals(3, lpList.size());
            checkLungProfile("COPD", 158.0f, Sex.FEMALE, 350, 16, 100, 1.0f, lpList.get(0));
            checkLungProfile("Asthma", 180.0f, Sex.MALE, 450, 10, 120, 2.0f, lpList.get(1));
            checkLungProfile("Tuberculosis", 152.3f, Sex.MALE, 300, 18, 85, 1.8f, lpList.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}