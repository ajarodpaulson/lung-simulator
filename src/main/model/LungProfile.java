package model;

/* 
* Represents a simplified adult lung profile with an optional label, height (cm), biological sex,
* compliance (ml/cmH2O), and resistance (cmH20/L/s), and ideal body weight (kg)
*/
public class LungProfile {

    String label;
    float height;
    Sex sex;
    int compliance;
    float resistance;
    float idealBodyWeight;

    enum Sex {
        MALE, FEMALE
    }

    /*
     * EFFECTS: constructs a new lung profile with a label, height (cm), biological sex,
     * compliance (ml/cmH2O), and resistance (cmH20/L/s)
     * Also sets ideal body weight (kg)
     */
    public LungProfile(String label, float height, Sex sex, int compliance, float resistance) {
        this.label = label;
        this.height = height;
        this.sex = sex;
        this.compliance = compliance;
        this.resistance = resistance;
        setIBW(calculateIBW(height, sex));
    }

    /*
     * EFFECTS: constructs a new lung profile without a label
     */
    public LungProfile(float height, Sex sex, int compliance, float resistance) {
        this(null, height, sex, compliance, resistance);
    }

    // getters

    public String getLabel() {
        return this.label;
    }

    public float getHeight() {
        return this.height;
    }

    public Sex getSex() {
        return this.sex;
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

    // setters & mutators

    /*
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

    public void setCompliance(int compliance) {
        this.compliance = compliance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    private void setIBW(float idealBW) {
        this.idealBodyWeight = idealBW;
    }

    // helpers

    /*
     * EFFECTS: uses the height and sex to calcuate the Ideal Body Weight using the Devine formula
     * If height < 152.4cm, will return IBW for a 152.4cm person
     * XXX don't really want a default block but can't get full code coverage o/w
     */
    public float calculateIBW(float height, Sex sex) {
        switch (sex) {
            case MALE:
                if (height <= 152.4) {
                    return (50.0f + 0.91f * (152.4f - 152.4f));
                } 
                return (50.0f + 0.91f * (height - 152.4f));
        
            default:
            if (height <= 152.4) {
                return (45.5f + 0.91f * (152.4f - 152.4f));
            } 
            return (45.5f + 0.91f * (height - 152.4f));
        }
    }
}