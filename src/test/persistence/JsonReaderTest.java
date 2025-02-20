package persistence;

import model.*;
import model.LungProfile.Sex;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderGivenNonExistentFileShouldThrowIOException() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LungProfileManager lpm = reader.read(LungProfileManager.class);
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("src/main/data/testReaderEmptyLungProfileManager.json");
        try {
            LungProfileManager lpm = reader.read(LungProfileManager.class);
            assertEquals("My lung profile manager", lpm.getName());
            assertEquals(0, lpm.numLungProfiles());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("src/main/data/testReaderGeneralLungProfileManager.json");
        try {
            LungProfileManager lpm = reader.read(LungProfileManager.class);
            assertEquals("My lung profile manager", lpm.getName());
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