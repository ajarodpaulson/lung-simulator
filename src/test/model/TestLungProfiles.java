package model;

import model.LungProfile.Sex;

/*
 * This class represents pre-configured lung profiles for testing use.
 */
public class TestLungProfiles {
    protected final LungProfile lp1;
    protected final LungProfile lp2;
    protected final LungProfile lp3;
    protected final LungProfile lp4;
    protected final LungProfile lp5;
    protected final LungProfile lp6;
    protected final LungProfile lp7;
    protected final LungProfile lp8;

    protected TestLungProfiles() {
        lp1 = new LungProfile(
                "COPD", 158.0f, Sex.FEMALE, 350, 16, 100, 1.0f);
        lp2 = new LungProfile(
                "Asthma", 180.0f, Sex.MALE, 450, 10, 120, 2.0f);
        lp3 = new LungProfile(
                "Tuberculosis", 152.3f, Sex.MALE, 300, 18, 85, 1.8f);
        lp4 = new LungProfile(
                152.4f, Sex.MALE, 300, 30, 120, 2.0f);
        lp5 = new LungProfile(
                "COPD", 152.5f, Sex.MALE, 500, 10, 100, 1.0f);
        lp6 = new LungProfile(
                152.3f, Sex.FEMALE, 600, 12, 120, 2.0f);
        lp7 = new LungProfile(
                "COPD", 152.4f, Sex.FEMALE, 225, 14, 100, 1.0f);
        lp8 = new LungProfile(
                152.5f, Sex.FEMALE, 325, 22, 120, 2.0f);
    }
}
