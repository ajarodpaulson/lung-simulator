package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.LungProfile;
import model.LungProfile.Sex;
import model.LungProfileManager;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ToolBarDemo2Project.zip
 */

public class LungProfileManagerToolbar extends JToolBar implements ActionListener {
    private static final String NEW = "NEW";
    private static final String LOAD = "LOAD";
    private static final String SAVE = "SAVE";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "src/main/data/lungProfileManager.json";
    private LungProfileManager lungProfileManager = LungProfileManager.getInstance();

    public LungProfileManagerToolbar() {
        addButtons(this);
        setFloatable(false);
        setRollover(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        button = new JButton("New");
        button.setActionCommand(NEW);
        button.setToolTipText("Create a new lung profile");
        button.addActionListener(this);
        add(button);

        button = new JButton("Save");
        button.setActionCommand(SAVE);
        button.setToolTipText("Save lung profiles in current list");
        button.addActionListener(this);
        add(button);

        button = new JButton("Load");
        button.setActionCommand(LOAD);
        button.setToolTipText("Load the most recently saved lung profiles");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (NEW.equals(cmd)) {
            new SettingsDialog(getFrame(this.getParent())); // XXX who is the parent frame?
        } else if (SAVE.equals(cmd)) {
            saveLungProfileManager();
        } else if (LOAD.equals(cmd)) {
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

    // MODIFIES: this
    // EFFECTS: loads user's list of lung profiles from file
    private void loadLungProfileManager() {
        try {
            lungProfileManager = jsonReader.read();
            System.out.println("Loaded " + lungProfileManager.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
