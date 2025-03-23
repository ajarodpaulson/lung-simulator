package model.observer;

import java.util.List;

import model.LungProfile;

/**
 * Represents an observer in the Observer Design Pattern
 * 
 * Code reference(s): CPSC 210 AlarmSystem
 */
public interface Observer {
    /**
     * EFFECTS: the method that will run when an Observable calls notifyObservers()
     * @param lungProfiles is the object that will be passed to this from the Observable 
     */
    void update(List<LungProfile> lungProfiles);
}
