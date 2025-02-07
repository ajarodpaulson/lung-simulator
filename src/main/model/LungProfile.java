package model;

// Represents a simplified adult lung profile with...
public class LungProfile {

    enum Sex {
        MALE, FEMALE
    }

    /*
     * EFFECTS: constructs a new lung profile with a label, height in cm, biological sex,
     * compliance in ml/cmH2O, and resistance in cmH20/L/s
     */
    public LungProfile(String label, float height, Sex sex, int compliance, float resistance) {

    }

    /*
     * EFFECTS: constructs a new lung profile without a label
     */
    public LungProfile(float height, Sex sex, int compliance, float resistance) {

    }

    // getters

    public String getLabel() {
        // TODO
        return "";
    }

    public float getHeight() {
        // TODO
        return 0;
    }

    public boolean getSex() {
        // TODO
        return false;
    }

    public int getCompliance() {
        // TODO
        return 0;
    }

    public float getResistance() {
        //  TODO
        return 0;
    }

    public float getIBW() {
        // TODO
        return 0;
    }

    // setters & mutators

    /*
     * EFFECTS: sets optional label for this lung profile
     */
    public void setLabel(String label) {
        //  TODO
    }

    public void setHeight(float height) {
        // TODO
    }

    public void setSex(Sex sex) {
        // TODO
    }

    public void setCompliance(int compliance) {
        // TODO
    }

    public void setResistance(float resistance) {
        //  TODO
    }

    // helpers

    /*
     * EFFECTS: uses the height and sex to calcuate the Ideal Body Weight using the Devine formula
     * If height < 152.4cm, will return IBW for a 152.4cm person
     */
    public float calculateIBW() {
        // TODO
        return 0;
    }
}