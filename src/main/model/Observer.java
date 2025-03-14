package model;

import java.util.List;

/**
 * Represents an observer in the Observer Design Pattern
 * Code reference(s): CPSC 210 AlarmSystem
 */
public interface Observer {
    void update(List<LungProfile> lungProfiles);
}
