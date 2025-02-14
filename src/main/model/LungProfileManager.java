package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Represents a collection of lung profiles and functionality for managing them
 * CLASS INVARIANT(S):
 * Labels in lpList must be unique
 */
public class LungProfileManager {

    private List<LungProfile> lpList;

    /*
     * Constructs new LungProfileManager with empty list
     */
    public LungProfileManager() {
        lpList = new ArrayList<>();
    }

    public List<LungProfile> getLungProfiles() {
        return this.lpList;
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
}