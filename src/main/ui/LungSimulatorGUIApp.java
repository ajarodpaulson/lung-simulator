package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.LungProfileManager;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
 */

public class LungSimulatorGUIApp {
    private static final String JSON_STORE = "src/main/data/lungProfileManager.json";
    //public static LungProfileManager lpManager = new LungProfileManager("list of lung profiles");
    private static LungProfileManager lpManager;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    
    /**
     * EFFECTS: Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public LungSimulatorGUIApp() {
        lpManager = new LungProfileManager("New Lung Profile Manager");
        
        // Create and set up the window.
        JFrame frame = new JFrame("Lung Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // Create and set up the left panel
        JPanel leftPanel = new LungProfileManagerPanel(lpManager);

        // Create and set up the main panel
        JPanel mainPanel = new DisplayMetricsPanel();
        
        // Create and set up the bottom panel
        JPanel bottomPanel = new SettingsPanel();

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
