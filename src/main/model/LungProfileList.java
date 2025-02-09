package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a collection of lung profiles 
 */
public class LungProfileList {

    /*
     * Constructs new LungProfileList with empty list
     */
    public LungProfileList() {

    }

    // getters

    public List<LungProfile> getLungProfiles() {
        // TODO 
        return new ArrayList<>();
    }

    /*
     * EFFECTS: returns lung profile with corresponding label or null if label can't be found
     */
    public LungProfile selectLungProfile(String label) {
        // TODO
        return new LungProfile(0, null, 0, 0);
    }
    
    // setters & mutators

    /*
     * MODIFIES: this
     * EFFECTS: saves the lung profile to a collection of lung profiles XXX collection?
     */
    public void saveLungProfile(LungProfile lungProfile) {
        // TODO
    }

    /*
     * MODIFIES: this
     * EFFECTS: deletes the lung profile with correspoding label or null if label can't be found
     */
    public void deleteLungProfile(String label) {
        // TODO
    }

    /*
     * REQUIRES: label must be unique
     * MODIFIES: this
     * EFFECTS: adds the lung profile to the list
     */
    public void addLungProfile(LungProfile lp) {
        // TODO
    }

    // XXX do I need a modifyLungProfile()? what about storing by date added and alphabetically

    // helpers
}