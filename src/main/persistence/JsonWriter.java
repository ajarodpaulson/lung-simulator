package persistence;

import org.json.JSONObject;

import model.LungProfileManager;

import java.io.*;

/*
 * Represents a writer that writes JSON representation of lung profile manager to file
 * 
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of lung profile manager to file
    public void write(LungProfileManager lpm) {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // TODO
    }
}
