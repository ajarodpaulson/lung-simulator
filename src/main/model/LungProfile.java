package model;

import model.exception.InvalidArgumentException;

/* 
* Represents a simplified adult lung profile with an optional label, height (cm), biological sex,
* tidal volume (ml), respiratory rate (breaths/min), compliance (ml/cmH2O), resistance (cmH20/L/s),
* and ideal body weight (kg)
*/
public class LungProfile {

    private String label;
    private float height;
    private Sex sex;
    private int tidalVolume;
    private int respRate;
    private int compliance;
    private float resistance;
    private float idealBodyWeight; // TODO remove this field

    public static final String heightUnits = "cm";
    public static final String tidalVolumeUnits = "mls";
    public static final String respRateUnits = "breaths/min";
    public static final String complianceUnits = "ml/cmH2O";
    public static final String resistanceUnits = "cmH2O/L/s";
    public static final String idealBWUnits = "kg";

    public enum Sex {
        MALE, FEMALE
    }

    /*
     * EFFECTS: constructs a new lung profile with a label, height, biological
     * sex, tidal volume, respiratory rate, compliance, and resistance
     * Also sets ideal body weight (kg)
     */
    public LungProfile(String label, float height, Sex sex, int tv, int rr, int compliance, float resistance) {
        this.label = label;
        this.height = height;
        this.sex = sex;
        this.tidalVolume = tv;
        this.respRate = rr;
        this.compliance = compliance;
        this.resistance = resistance;
        setIBW(calculateIBW(height, this.sex));
    }

    /*
     * EFFECTS: constructs a new lung profile without a label
     */
    public LungProfile(float height, Sex sex, int tv, int rr, int compliance, float resistance) {
        this(null, height, sex, tv, rr, compliance, resistance);
    }

    public String getLabel() {
        return this.label;
    }

    public float getHeight() {
        return this.height;
    }

    public Sex getSex() {
        return this.sex;
    }

    public int getTidalVolume() {
        return this.tidalVolume;
    }

    public int getRespRate() {
        return this.respRate;
    }

    public int getCompliance() {
        return this.compliance;
    }

    public float getResistance() {
        return this.resistance;
    }

    public float getIBW() {
        return this.idealBodyWeight;
    }

    public void setTidalVolume(int tv) {
        this.tidalVolume = tv;
    }

    public void setRespRate(int rr) {
        this.respRate = rr;
    }

    public void setCompliance(int compliance) {
        this.compliance = compliance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    private void setIBW(float idealBW) {
        this.idealBodyWeight = idealBW;
    }

    /*
     * EFFECTS: creates and returns a new VolumeTimeScalar for this lung profile
     */
    public VolumeTimeScalar getVolumeTimeScalar() {
        return new VolumeTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

    /*
     * EFFECTS: creates and returns a new FlowTimeScalar for this lung profile
     */
    public FlowTimeScalar getFlowTimeScalar() {
        return new FlowTimeScalar(tidalVolume, respRate, compliance, resistance);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets optional label for this lung profile
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /*
     * EFFECTS: sets the height and updates the IBW
     */
    public void setHeight(float height) {
        this.height = height;
        setIBW(calculateIBW(this.height, this.sex));
    }

    /*
     * EFFECTS: sets the sex and updates the IBW
     */
    public void setSex(Sex sex) {
        this.sex = sex;
        setIBW(calculateIBW(this.height, this.sex));
    }

    /*
     * EFFECTS: uses the height and sex to calcuate and return the Ideal Body Weight
     * using the Devine formula
     * If height < 152.4cm, will return IBW for a 152.4cm person
     * Throws IllegalArgumentException if sex is invalid
     * XXX TESTME
     */
    public float calculateIBW(float height, Sex sex) throws IllegalArgumentException {
        switch (sex) {
            case MALE:
                if (height <= 152.4) {
                    return (50.0f + 0.91f * (152.4f - 152.4f));
                }
                return (50.0f + 0.91f * (height - 152.4f));

            case FEMALE:
                if (height <= 152.4) {
                    return (45.5f + 0.91f * (152.4f - 152.4f));
                }
                return (45.5f + 0.91f * (height - 152.4f));
        }
        throw new IllegalArgumentException();
    }

    /*
     * EFFECTS: converts non-case sensitive "M" or "F" to corresponding Sex enum value
     * Throws IllegalArgumentException if sex is not an "M" or an "F"
     * XXX does this method belong here? it seems like a lung profile shouldn't contain such a behaviour...
     * but where would i put it?
     * XXX also, tried to write this using "line of sight rule" but this was awkward because there isn't
     * just a single case in the event that the argument is a valid one
     */
    public static Sex convertSexStringToSexEnum(String sex) throws InvalidArgumentException {
        if (sex.equalsIgnoreCase("m")) {
            return Sex.MALE;
        } else if (sex.equalsIgnoreCase("f")) {
            return Sex.FEMALE;
        } else {
            throw new InvalidArgumentException(sex, "is not a valid argument for sex.");
        }
    }
}