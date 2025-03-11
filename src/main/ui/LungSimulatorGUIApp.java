package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.LungProfileManager;

/**
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
 */

public class LungSimulatorGUIApp {
    //public static final LungProfileManager lpManager;
    /**
     * EFFECTS: Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public LungSimulatorGUIApp() {
        // Create and set up the window.
        JFrame frame = new JFrame("Lung Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        //lpManager = 

        // Create and set up the left panel
        JPanel leftPanel = new LungProfileManagerPanel();

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
