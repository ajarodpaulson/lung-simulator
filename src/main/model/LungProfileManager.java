package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;

import model.observer.Observable;
import model.observer.Observer;
import persistence.Writable;

/*
 * Represents a collection of lung profiles and functionality for managing them
 * 
 * CLASS INVARIANT(S):
 * Labels in lpList must be unique
 * 
 */
public class LungProfileManager extends Observable implements Writable {

    private List<LungProfile> lpList;
    private String name;
    private static LungProfileManager lungProfileManager;
    private LungProfile activeLungProfile;
    private EventLog eventLog = EventLog.getInstance();

    /*
     * Constructs new LungProfileManager with empty list
     */
    private LungProfileManager() {
        init();
    }

    /**
     * Gets instance of LungProfileManager - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     * 
     * @return instance of EventLog
     * 
     * Code reference: CPSC 210 AlarmSystem
     */
    public static LungProfileManager getInstance() {
        if (lungProfileManager == null) {
            lungProfileManager = new LungProfileManager();
        }
        return lungProfileManager;
    }


    /**
     * MODIFIES: this
     * EFFECTS: reverts this to its initial state
     */
    public void resetInstance() {
        init();
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets initial state of this 
     */
    private void init() {
        lpList = new ArrayList<>();
        this.name = "My lung profile manager";
    }

    public LungProfile getActiveLungProfile() {
        return activeLungProfile;
    }

    public void setActiveLungProfile(LungProfile activeLungProfile) {
        if (this.activeLungProfile == activeLungProfile) {
            return;
        }

        this.activeLungProfile = activeLungProfile;
        String message = (this.activeLungProfile == null) ? "A lung profile has not been selected to display." : "Now displaying the lung profile labelled: " + activeLungProfile.getLabel() + ".";
        eventLog.logEvent(new Event(message));
        notifyObservers();
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
     * EFFECTS: returns an Optional<LungProfile> which contains a lung profile if
     * one with corresponding label
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
        eventLog.logEvent(new Event("The lung profile labelled " + lp.getLabel() + " was added to the list."));
        notifyObservers();
    }

    /*
     * REQUIRES: lung profile list mustn't be empty and lung profile must exist in
     * the list
     * MODIFIES: this
     * EFFECTS: deletes the supplied lung profile
     */
    public boolean deleteLungProfile(LungProfile lp) {
        boolean wasRemoved = lpList.remove(lp);
        if (wasRemoved) {
            if (lp.equals(activeLungProfile)) {
                setActiveLungProfile(null);
            }
            eventLog.logEvent(new Event("The lung profile labelled: " + lp.getLabel() + " was deleted from the list."));
            notifyObservers();
        }
        return wasRemoved;
    }

    /*
     * Code Reference(s): CPSC 210 JsonSerializationDemo
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     * 
     * EFFECTS: converts this.LpList into a JSONObject
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
     * 
     * EFFECTS: returns lung profiles in this as a JSON array
     */
    private JSONArray lungProfilesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LungProfile lp : lpList) {
            jsonArray.put(lp.toJson());
        }

        return jsonArray;
    }


    /**
     * EFFECTS: notifies observers of this and passed them this.lpList
     */
    @Override
    public void notifyObservers() {
        for (Observer next : observers) {
            next.update(lpList);
        }
    }
}
