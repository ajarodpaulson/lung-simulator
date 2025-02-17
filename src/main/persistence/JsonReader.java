package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Represents a reader that reads JSON data stored in file
 * 
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // TODO
    }

    // EFFECTS: reads writable object from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Writable read() throws IOException {
        // TODO
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        // TODO
        return "";
    }

    // EFFECTS: parses writable object from JSON object and returns it
    private Writable parseWorkRoom(JSONObject jsonObject) {
        // TODO
        return null;
    }

    // MODIFIES: writeable
    // EFFECTS: parses writable objects from JSON object and adds them to collection
    // of writable objects
    private void addWriteables(Writable writables, JSONObject jsonObject) {
        // TODO
    }

    // MODIFIES: writeable
    // EFFECTS: parses writable object from JSON object and adds it to collection of
    // writable objects
    private void addWriteable(Writable writable, JSONObject jsonObject) {
        // TODO
    }
}
