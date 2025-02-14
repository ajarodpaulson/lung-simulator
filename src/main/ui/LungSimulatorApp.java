package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import model.LungProfile;
import model.LungProfileManager;
import model.ScalarTime;

/*
 * A lung simulator application that allows users to configure a lung profile
 * and observe how key scalar metrics change are influenced by key lung
 * parameters 
 * 
 * Code reference(s): CPSC210 Lab 4 (Flashcard Reviewer)
 */
public class LungSimulatorApp {
    private Scanner scanner;
    private boolean isProgramRunning;
    private LungProfileManager lpManager;
    private int currentLungProfileIndex = 0;
    private LungProfile currentLungProfile;

    // EFFECTS: creates an instance of the Lung Simulator console ui application
    public LungSimulatorApp() {
        init();

        printDivider();
        System.out.println("Welcome to the Lung Simulator app!");
        printDivider();

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        this.lpManager = new LungProfileManager();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("n: Create a new lung profile");
        System.out.println("v: View all lung profiles by label");
        System.out.println("c: Choose a lung profile to view by label");
        System.out.println("q: Exit the application");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    @SuppressWarnings("methodlength")
    public void processMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "n":
                createNewLungProfile();
                break;
            case "v":
                showAllLungProfileLabels();
                break;
            case "c":
                selectLungProfileToView();
                break;
            case "m":
                modifyLungProfile();
                break;
            case "d":
                deleteLungProfile();
                break;
            case "s":
                addLungProfileToList();
                break;
            case "q":
                quitApplication();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    /*
     * EFFECTS: displays all the lung profiles labels in the list
     * Informs the user if the list is empty
     */
    public void showAllLungProfileLabels() {
        if (this.lpManager.getLungProfiles().isEmpty()) {
            System.out.println("Error: There are no lung profiles in the list. Try adding a lung profile first!");
            return;
        }
        for (LungProfile lp : lpManager.getLungProfiles()) {
            System.out.println(lp.getLabel());
        }
    }

    /*
     * EFFECTS: looks up a lung profile by user supplied label and processes the
     * output using action or
     * informs the user if profile not found
     */
    private void findAndProcessLungProfile(Consumer<LungProfile> action) {
        String input = requestLungProfileLabel();

        Optional<LungProfile> result = lpManager.findLungProfile(input);
        result.ifPresentOrElse(
                action,
                () -> System.out.println("I couldn't find a lung profile with that label."));
    }

    /*
     * EFFECTS: allows a user to select a lung profile by label from the list
     * Informs user if label is not in the list and goes back to main menu
     */
    // XXX: need more help understanding this syntax
    public void selectLungProfileToView() {
        findAndProcessLungProfile(this::summarizeLungCharacteristics);
    }

    /*
     * EFFECTS: requests and returns a lung profile label from the user
     */
    private String requestLungProfileLabel() {
        System.out.println("Enter the label of a lung profile you'd like to view:");
        String input = this.scanner.nextLine();
        System.out.println();
        return input;
    }

    /*
     * EFFECTS: prints formatted string with all lung characteristics
     */
    public void summarizeLungCharacteristics(LungProfile lp) {
        System.out.println(String.format(
                "Lung Profile: %s\n" 
                + "Height: %.1f %s\n" 
                + "Sex: %s\n" 
                + "Tidal Volume: %d %s\n" 
                + "Respiratory Rate: %d %s\n" 
                + "Compliance: %d %s\n" 
                + "Resistance: %.2f %s\n" 
                + "Ideal Body Weight: %.1f %s",
                lp.getLabel(),
                lp.getHeight(), LungProfile.heightUnits,
                lp.getSex(),
                lp.getTidalVolume(), LungProfile.tidalVolumeUnits,
                lp.getRespRate(), LungProfile.respRateUnits,
                lp.getCompliance(), LungProfile.complianceUnits,
                lp.getResistance(), LungProfile.resistanceUnits,
                lp.getIBW(), LungProfile.idealBWUnits));
    }

    /*
     * MODIFIES: this
     * EFFECTS: deletes the lung profile with the supplied label
     * Informs user if label is not in the list or if the lung profile
     * was not deleted and goes back to main menu
     */
    public void deleteLungProfile() {
        findAndProcessLungProfile(lp -> {
            boolean success = lpManager.deleteLungProfile(lp);
            if (!success) {
                System.out.println("Failed to delete lung profile.");
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: allows the user to provide new parameters for the lung profile with
     * the supplied label
     * Informs user if label is not in the list and goes back to the main menu
     */
    public void modifyLungProfile() {
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: adds the current lung profile to the list of lung profiles
    public void addLungProfileToList() {
        lpManager.addLungProfile(currentLungProfile);
        System.out.println("The lung profile \"" + currentLungProfile.getLabel() + "\" was added to the list!");
    }

    // MODIFIES: this
    // EFFECTS: creates a new lung profile and outputs scalar metrics
    public void createNewLungProfile() {
        System.out.println("Enter a label for this lung profile:");
        String label = this.scanner.nextLine();

        System.out.println("Enter the person's height in " + LungProfile.heightUnits + ":");
        float height = this.scanner.nextFloat();
        this.scanner.nextLine();

        System.out.println("Enter M or F for the person's sex:");
        String sex = this.scanner.nextLine();

        System.out.println("Enter the lung compliance in " + LungProfile.complianceUnits + ":");
        int compliance = this.scanner.nextInt();
        this.scanner.nextLine();

        System.out.println("Enter the lung resistance in " + LungProfile.resistanceUnits + ":");
        float resistance = this.scanner.nextFloat();
        this.scanner.nextLine();

        System.out.println("Enter the respiratory rate in " + LungProfile.respRateUnits + ":");
        int respRate = this.scanner.nextInt();
        this.scanner.nextLine();

        System.out.println("Enter the tidal volume in " + LungProfile.tidalVolumeUnits + ":");
        int tidalVolume = this.scanner.nextInt();
        this.scanner.nextLine();

        // XXX probably need a handler right here to ensure input was correct
        this.currentLungProfile = new LungProfile(label, height, sex, tidalVolume, respRate, compliance, resistance);

        System.out.println(summarizeScalarTimeMetrics(currentLungProfile.getVolumeTimeScalar()));
        System.out.println();
        saveOrMakeNewLungProfile();
    }

    /*
     * EFFECTS: summarizes and return metrics for the supplied ScalarTime
     */
    protected String summarizeScalarTimeMetrics(ScalarTime st) {
        return String.format(
                "%nThe %s-time waveform has:%n" 
                + "Amplitude: %s %s%n" 
                + "Maximum: %s %s%n" 
                + "Minimum: %s %s%n"
                + "Breath Cycle Time: %s s",
                st.getScalarName(),
                st.calculateAmplitude(), st.getUnits(),
                st.calculateMaximumScalarValue(), st.getUnits(),
                st.calculateMinimumScalarValue(), st.getUnits(),
                st.calculateBreathCycleTime());
    }

    /*
     * EFFECTS: displays a list of commands that can be used in the main menu
     */
    public void saveOrMakeNewLungProfile() {
        System.out.println("Would you like to save this lung profile or make a new one?");
        System.out.println();
        System.out.println("s: Save lung profile");
        System.out.println("n: Make a new lung profile");
        printDivider();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitApplication() {
        System.out.println("Thanks for using the Lung Simulator app!");
        System.out.println("See you next time!");
        this.isProgramRunning = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }
}
