package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import model.LungProfileManager;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Represents a toolbar for adding, saving, and loading new lung profiles; these
 * actions update
 * the Observable lungProfileManager
 * 
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ToolBarDemo2Project.zip
 */
public class LungProfileManagerToolbar extends JToolBar implements ActionListener {
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "src/main/data/lungProfileManager.json";
    private LungProfileManager lungProfileManager = LungProfileManager.getInstance();

    private static enum Action {
        NEW, LOAD, SAVE
    }

    /**
     * EFFECTS: constructs a new LungProfileManagerToolbar with JsonWriter and
     * JsonReader for
     * saving and loading, respectively
     */
    public LungProfileManagerToolbar() {
        addButtons();
        setFloatable(false);
        setRollover(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /**
     * MODIFIES: this
     * EFFECTS: adds new, save, and load buttions to this
     */
    protected void addButtons() {
        addButton(Action.NEW.toString(), "Create a new lung profile");
        addButton(Action.SAVE.toString(), "Save lung profiles in current list");
        addButton(Action.LOAD.toString(), "Load the most recently saved lung profiles");
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a new button with the supplied @param label and @param
     * toolTipText
     * and adds it to this
     */
    private void addButton(String label, String toolTipText) {
        JButton button = new JButton(label);
        button.setActionCommand(label);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        add(button);
    }

    /**
     * EFFECTS: handles the action when one of the buttons on this is pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (Action.NEW.toString().equals(cmd)) {
            new SettingsDialog(getFrame(this.getParent()));
        } else if (Action.SAVE.toString().equals(cmd)) {
            saveLungProfileManager();
        } else if (Action.LOAD.toString().equals(cmd)) {
            loadLungProfileManager();
        }
    }

    /**
     * REQUIRES: this must have a JFrame parent in the UI hierarchy
     * EFFECTS: obtains the JFrame parent of this in the UI hierarchy and returns it
     */
    private JFrame getFrame(Container parent) {
        if (parent instanceof JFrame) {
            return (JFrame) parent;
        } else {
            return getFrame(parent.getParent());
        }
    }

    // EFFECTS: saves the user's list of lung profiles to file
    private void saveLungProfileManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(lungProfileManager);
            jsonWriter.close();
            System.out.println("Saved " + lungProfileManager.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /**
     * MODIFIES: this, lungProfileManager
     * EFFECTS: clears the profiles in lungProfileManager and
     * loads user's list of lung profiles from file, which may be an empty list
     */
    private void loadLungProfileManager() {
        try {
            lungProfileManager.getLungProfiles().clear();
            lungProfileManager = jsonReader.read();
            System.out.println("Loaded " + lungProfileManager.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
