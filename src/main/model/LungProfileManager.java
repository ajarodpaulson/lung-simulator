package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Represents a collection of lung profiles 
 */
public class LungProfileManager {

    private List<LungProfile> lpList;

    /*
     * Constructs new LungProfileList with empty list
     */
    public LungProfileManager() {
        lpList = new ArrayList<>();
    }

    // getters

    public List<LungProfile> getLungProfiles() {
        return this.lpList;
    }

    /*
     * REQUIRES: size of list must be > 0
     * EFFECTS: returns lung profile with corresponding label or null if label can't
     * be found
     * FIXME: use of optional here? trying to have callers handle the possibility of
     * null
     */
    public Optional<LungProfile> findLungProfile(String label) {
        for (LungProfile lp : this.lpList) {
            if (lp.getLabel().equals(label)) {
                return Optional.of(lp);
            }
        }
        return Optional.empty();
    }

    // setters & mutators

    /*
     * REQUIRES: lung profile must have unique label
     * MODIFIES: this
     * EFFECTS: adds the lung profile to a collection of lung profiles XXX
     * collection?
     */
    public void addLungProfile(LungProfile lp) {
        this.lpList.add(lp);
    }

    /*
     * REQUIRES: lung profile list mustn't be empty
     * MODIFIES: this
     * EFFECTS: deletes the supplied lung profile
     * XXX: Would it be better to have this method return a boolean indicating the
     * success of the operation,
     * and handle all of the requirements within the method (as opposed to
     * implementing all of this in
     * a ui-based caller)
     * XXX while loop versus for loop
     */
    public boolean deleteLungProfile(LungProfile lp) {
        return lpList.remove(lp);
    }

    // XXX do I need a modifyLungProfile()? what about storing by date added and
    // alphabetically
    // XXX do I need different methods to add and save?

    // helpers
}