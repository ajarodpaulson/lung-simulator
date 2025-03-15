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
 * 
 * Code reference(s):
 */
public class LungProfileManager extends Observable implements Writable {

    private List<LungProfile> lpList;
    private String name;
    private static LungProfileManager lungProfileManager;
    // XXX Design decision? how else could I have done this? I don't really want the manager to know about this
    // but where else would I have stored which lung profile the user had currently selected?
    private LungProfile activeLungProfile;

    public LungProfile getActiveLungProfile() {
        return activeLungProfile;
    }

    public void setActiveLungProfile(LungProfile activeLungProfile) {
        this.activeLungProfile = activeLungProfile;
        notifyObservers();
    }

    /*
     * Constructs new LungProfileManager with empty list
     */
    private LungProfileManager() {
        lpList = new ArrayList<>();
        this.name = "My lung profile manager";
    }

    /**
	 * Gets instance of LungProfileManager - creates it
	 * if it doesn't already exist.
	 * (Singleton Design Pattern)
	 * @return  instance of EventLog
	 */
	public static LungProfileManager getInstance() {
		if (lungProfileManager == null)
			lungProfileManager = new LungProfileManager();
		
		return lungProfileManager;
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
                activeLungProfile = null;
            }
            notifyObservers();
        }
        return wasRemoved;
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
        for (Observer next : observers) {
            next.update(lpList);
        }
    }
}

/*
 * If you want to have multiple users, you'd have "another singleton that holds a map of users
 * and their respective managers"
 */