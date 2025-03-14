package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents a collection of lung profiles and functionality for managing them
 * 
 * CLASS INVARIANT(S):
 * Labels in lpList must be unique
 */
public class LungProfileManager extends Observable implements Writable {

    private List<LungProfile> lpList;
    private String name;

    /*
     * Constructs new LungProfileManager with empty list
     */
    public LungProfileManager(String name) {
        lpList = new ArrayList<>();
        this.name = name;
    }

    public List<LungProfile> getLungProfiles() {
        return this.lpList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*
     * EFFECTS: returns the number of lung profiles in lpList
     */
    public int numLungProfiles() {
        return lpList.size();
    }

    /*
     * REQUIRES: size of list must be > 0
     * EFFECTS: returns an Optional<LungProfile> which contains a lung profile if one with corresponding label 
     * is found or is empty if it cannot be found
     */
    public Optional<LungProfile> findLungProfile(String label) {
        for (LungProfile lp : this.lpList) {
            if (lp.getLabel().equals(label)) {
                return Optional.of(lp);
            }
        }
        return Optional.empty();
    }

    /*
     * REQUIRES: lung profile must have unique label
     * MODIFIES: this
     * EFFECTS: adds the lung profile to a collection of lung profiles
     */
    public void addLungProfile(LungProfile lp) {
        this.lpList.add(lp);
    }

    /*
     * REQUIRES: lung profile list mustn't be empty and lung profile must exist in the list
     * MODIFIES: this
     * EFFECTS: deletes the supplied lung profile
     */
    public boolean deleteLungProfile(LungProfile lp) {
        return lpList.remove(lp);
    }

    /*
     * Code Reference(s): CPSC 210 JsonSerializationDemo 
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lpList", lungProfilesToJson());
        return json;
    }

    /*
     * Code Reference(s): CPSC 210 JsonSerializationDemo 
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     */
    // EFFECTS: returns lung profiels in this as a JSON array
    private JSONArray lungProfilesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LungProfile lp : lpList) {
            jsonArray.put(lp.toJson());
        }

        return jsonArray;
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObservers'");
    }
}