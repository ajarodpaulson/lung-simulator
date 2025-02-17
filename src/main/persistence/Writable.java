package persistence;

import org.json.JSONObject;

/*
 * Represents the ability to convert a JAVA class into a JSON object
 * 
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
