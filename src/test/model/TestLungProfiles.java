package model;

import model.LungProfile;

/*
 * This class represents pre-configured lung profiles for testing use.
 */
public class TestLungProfiles {
    protected static final LungProfile lp1 = new LungProfile(
            "COPD", 158.0f, LungProfile.Sex.FEMALE, 350, 16, 100, 1.0f);
    protected static final LungProfile lp2 = new LungProfile(
            "Asthma", 180.0f, LungProfile.Sex.MALE, 450, 10, 120, 2.0f);
    protected static final LungProfile lp3 = new LungProfile(
            "Tuberculosis", 152.3f, LungProfile.Sex.MALE, 18, 300, 85, 1.8f);
    protected static final LungProfile lp4 = new LungProfile(
            152.4f, LungProfile.Sex.MALE, 300, 30, 120, 2.0f);
    protected static final LungProfile lp5 = new LungProfile(
            "COPD", 152.5f, LungProfile.Sex.MALE, 500, 10, 100, 1.0f);
    protected static final LungProfile lp6 = new LungProfile(
            152.3f, LungProfile.Sex.FEMALE, 600, 12, 120, 2.0f);
    protected static final LungProfile lp7 = new LungProfile(
            "COPD", 152.4f, LungProfile.Sex.FEMALE, 225, 14, 100, 1.0f);
    protected static final LungProfile lp8 = new LungProfile(
            152.5f, LungProfile.Sex.FEMALE, 325, 22, 120, 2.0f);
}

/*
 * XXX I'm wondering why this works though? If I have methods that are mutating
 * these objects and they're not being
 * reset by the setup method...
 * See LungProfile.testValueOfLP1()
 */
