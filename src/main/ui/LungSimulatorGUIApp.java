package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import model.LungProfileManager;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Represents a lung simulator GUI application; sets up the GUI's JFrame and
 * adds the UI components to it
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
 */

public class LungSimulatorGUIApp {

    // public static LungProfileManager lpManager = new LungProfileManager("list of
    // lung profiles");
    private SettingsDialog settingsDialog;

    /**
     * EFFECTS: Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public LungSimulatorGUIApp() {
        displaySplashImage();
        // Create and set up the window.
        JFrame frame = new JFrame("Lung Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create and set up the left panel
        JPanel leftPanel = new LungProfileManagerPanel();

        // Create and set up the main panel
        DisplayMetricsPanel mainPanel = new DisplayMetricsPanel();
        LungProfileManager.getInstance().addObserver(mainPanel);

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displaySplashImage() {
        JWindow splashScreen = new JWindow();
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        ImageIcon icon = new ImageIcon("src/main/data/splash.png");
        System.out.println("Image width: " + icon.getIconWidth());
        System.out.println("Image height: " + icon.getIconHeight());
        JLabel label = new JLabel(icon);
        splashScreen.add(label);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);
        splashScreen.paintAll(splashScreen.getGraphics());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.setVisible(false);
        splashScreen.dispose();
    }
}
