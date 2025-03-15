package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.LungProfile;
import model.LungProfile.Sex;
import model.LungProfileManager;

/*
 * Represents a reader that reads JSON data stored in file
 * 
 * Code Reference(s): CPSC 210 JsonSerializationDemo 
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    private String source; 

    /*
     * EFFECTS: constructs reader to read from source file
     */ 
    public JsonReader(String source) {
        this.source = source;
    }

    /* 
     * EFFECTS: reads lpm from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public LungProfileManager read() throws IOException  {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseJsonObject(jsonObject);
    }

    /*
     * EFFECTS: reads source file as string and returns it
     */ 
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses LungProfileManager from JSON object and returns it
    private LungProfileManager parseJsonObject(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LungProfileManager lpm = LungProfileManager.getInstance();
        // XXX good idea?
        lpm.getLungProfiles().clear();
        // to here
        addLungProfiles(lpm, jsonObject);
        return lpm;
    }

    // MODIFIES: lpm
    // EFFECTS: parses lung profiles from JSON object and adds them to lpm
    private void addLungProfiles(LungProfileManager lpm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lpList");
        for (Object json : jsonArray) {
            JSONObject nextLungProfile = (JSONObject) json;
            addLungProfile(lpm, nextLungProfile);
        }
    }

    // MODIFIES: lpm
    // EFFECTS: parses lung profile from JSON object and adds it to lpm
    private void addLungProfile(LungProfileManager lpm, JSONObject jsonObject) {
        String label = jsonObject.getString("label");
        float height = jsonObject.getFloat("height");
        Sex sex = Sex.valueOf(jsonObject.getString("sex"));
        int tv = jsonObject.getInt("tv");
        int rr = jsonObject.getInt("rr");
        int compliance = jsonObject.getInt("compliance");
        float resistance = jsonObject.getFloat("resistance");
        LungProfile lp = new LungProfile(label, height, sex, tv, rr, compliance, resistance);
        lpm.addLungProfile(lp);
    }
}
