package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import model.LungProfile;
import model.LungProfile.Sex;
import model.LungProfileManager;
import model.ScalarTime;
import model.exception.InvalidArgumentException;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * A lung simulator application that allows users to configure a lung profile
 * and observe how key scalar metrics are influenced by key lung
 * parameters 
 * 
 * Code reference(s): CPSC210 Lab 4 (Flashcard Reviewer), CPSC 210 JsonSerializationDemo 
 */
public class LungSimulatorConsoleApp {
    private static final String JSON_STORE = "src/main/data/lungProfileManager.json";
    private Scanner scanner;
    private boolean isProgramRunning;
    private LungProfileManager lpManager;
    private LungProfile currentLungProfile;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    /*
     * EFFECTS: creates an instance of the Lung Simulator console ui application
     */
    public LungSimulatorConsoleApp() {
        init();

        printDivider();
        System.out.println("Welcome to the Lung Simulator app!");
        printDivider();

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes the application with the starting values
     */
    public void init() {
        this.lpManager = LungProfileManager.getInstance();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /*
     * EFFECTS: displays and processes inputs for the main menu
     */
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    /*
     * EFFECTS: displays a list of commands that can be used in the main menu
     */
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("n: Create a new lung profile");
        System.out.println("v: View all lung profiles in my list by label");
        System.out.println("s: Save my list of lung profiles to file");
        System.out.println("l: Load my list of lung profiles from file");
        System.out.println("c: Choose a lung profile in my list to view");
        System.out.println("d: Delete a lung profile from my list");
        System.out.println("q: Exit the application");
        printDivider();
    }

    /*
     * EFFECTS: displays a list of commands that can be used in the main menu
     */
    @SuppressWarnings("methodlength") // use approved by TA Bruce
    public void processMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "n":
                createNewLungProfile();
                outputScalarMetrics(currentLungProfile);
                System.out.println();
                askUserAddLungProfileToList();
                break;
            case "v":
                showAllLungProfileLabels();
                break;
            case "s":
                saveLungProfileManager();
                break;
            case "l":
                loadLungProfileManager();
                break;
            case "c":
                selectLungProfileToView();
                break;
            case "d":
                deleteLungProfile();
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
     * EFFECTS: displays all the lung profiles labels in their list
     * Informs the user if their list is empty and goes back to main menu
     */
    public void showAllLungProfileLabels() {
        if (printMsgIfLungProfileListEmpty()) {
            return;
        }
        for (LungProfile lp : lpManager.getLungProfiles()) {
            System.out.println(lp.getLabel());
        }
    }

    /*
     * EFFECTS: returns true and prints an informative message if the lung profile
     * list is empty
     */
    private boolean printMsgIfLungProfileListEmpty() {
        if (this.lpManager.getLungProfiles().isEmpty()) {
            System.out.println("Error: There are no lung profiles in your list. Try adding a lung profile first!");
            return true;
        } else {
            return false;
        }
    }

    /*
     * EFFECTS: looks up a lung profile by user supplied label and processes the
     * output using action (e.g., deletes from list, summarizes characteristics)
     * Informs the user if profile not found and goes back to the main menu
     */
    private void findAndProcessLungProfile(Consumer<LungProfile> action) {
        if (printMsgIfLungProfileListEmpty()) {
            return;
        }

        String input = requestLungProfileLabel();

        Optional<LungProfile> result = lpManager.findLungProfile(input);
        result.ifPresentOrElse(
                action,
                () -> System.out.println("I couldn't find a lung profile with that label."));
    }

    /*
     * EFFECTS: allows a user to select a lung profile by label from their list
     * Informs user if label is not in their list and goes back to main menu
     */
    public void selectLungProfileToView() {
        findAndProcessLungProfile(this::summarizeLungCharacteristics);
    }

    /*
     * EFFECTS: requests and returns a lung profile label from the user
     */
    private String requestLungProfileLabel() {
        System.out.println("Enter the label of the lung profile:");
        String input = this.scanner.nextLine();
        System.out.println();
        return input;
    }

    /*
     * EFFECTS: prints formatted string with all characteristics for
     * the supplied lung profile
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
        outputScalarMetrics(lp);
    }

    /*
     * MODIFIES: this
     * EFFECTS: deletes the lung profile with the supplied label
     * Informs user if label is not in their list or if the lung profile
     * was not deleted and goes back to main menu
     */
    public void deleteLungProfile() {
        findAndProcessLungProfile(lp -> {
            String label = lp.getLabel();
            boolean success = lpManager.deleteLungProfile(lp);
            if (!success) {
                System.out.println("Failed to delete lung profile.");
                return;
            }
            System.out.println("The lung profile with label \"" + label + "\" was deleted.");
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the current lung profile to user's list of lung profiles
     */
    public void addLungProfileToList() {
        lpManager.addLungProfile(currentLungProfile);
        System.out.println("The lung profile \"" + currentLungProfile.getLabel() + "\" was added to your list!");
    }

    /*
     * REQUIRES: requires numbers for height, compliance, resistance, respiratory
     * rate, and tidal volume questions
     * MODIFIES: this
     * EFFECTS: creates a new lung profile using user's input
     */
    public void createNewLungProfile() {
        System.out.println("Enter a label for this lung profile:");
        String label = this.scanner.nextLine();

        System.out.println("Enter the person's height in " + LungProfile.heightUnits + ":");
        float height = this.scanner.nextFloat();
        this.scanner.nextLine();

        System.out.println("Enter M or F for the person's sex:");
        Sex sex = null;
        sex = ensureValidInputForSex(sex, this.scanner);

        System.out.println("Enter the lung compliance in " + LungProfile.complianceUnits + " (normal is 50-170):");
        int compliance = this.scanner.nextInt();
        this.scanner.nextLine();

        System.out.println("Enter the lung resistance in " + LungProfile.resistanceUnits + " (normal is 0.6-2.4):");
        float resistance = this.scanner.nextFloat();
        this.scanner.nextLine();

        System.out.println("Enter the respiratory rate in " + LungProfile.respRateUnits + " (normal is 12-20):");
        int respRate = this.scanner.nextInt();
        this.scanner.nextLine();

        System.out.println("Enter the tidal volume in " + LungProfile.tidalVolumeUnits + " (normal is 400-600):");
        int tidalVolume = this.scanner.nextInt();
        this.scanner.nextLine();

        this.currentLungProfile = new LungProfile(label, height, sex, tidalVolume, respRate, compliance, resistance);
    }

    /*
     * MODIFIES: sex
     * EFFECTS: attempts to convert input into Sex, throws InvalidArgumentException
     * if this fails and informs the user; repeats until valid input provided
     */
    private Sex ensureValidInputForSex(Sex sex, Scanner scanner) {
        while (sex == null) {
            try {
                sex = LungProfile.convertSexStringToSexEnum(scanner.nextLine());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getArg() + " is not a valid input. Please enter M or F.");
            }
        }
        return sex;
    }

    /*
     * EFFECTS: outputs scalar metrics for the supplied lung profile
     */
    private void outputScalarMetrics(LungProfile lp) {
        System.out.println(summarizeScalarTimeMetrics(currentLungProfile.getVolumeTimeScalar()));
        System.out.println(summarizeScalarTimeMetrics(currentLungProfile.getFlowTimeScalar()));
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
     * EFFECTS: asks the user whether they would like to add the lung profile they
     * created to their list or discard and go back to main menu
     */
    public void askUserAddLungProfileToList() {
        System.out.println("Would you like to add this lung profile to your list?");
        System.out.println();
        System.out.println("a: Add lung profile to your list");
        System.out.println("b: Discard and go back to main menu");
        printDivider();
        String input = this.scanner.nextLine();

        if (input.equals("a")) {
            addLungProfileToList();
        }
        System.out.println();
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

    // EFFECTS: saves the user's list of lung profiles to file
    private void saveLungProfileManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(lpManager);
            jsonWriter.close();
            System.out.println("Saved " + lpManager.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user's list of lung profiles from file
    private void loadLungProfileManager() {
        try {
            lpManager = jsonReader.read();
            System.out.println("Loaded " + lpManager.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
