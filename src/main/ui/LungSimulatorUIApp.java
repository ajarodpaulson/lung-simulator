package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import model.LungProfileManager;

/**
 * Represents a lung simulator GUI application; sets up the GUI's JFrame and
 * adds the UI components to it
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ListDemoProject.zip
 */

public class LungSimulatorUIApp {

    // public static LungProfileManager lpManager = new LungProfileManager("list of
    // lung profiles");
    private SettingsDialog settingsDialog;
    private static final String IMG_PATH = "src/main/data/splash.jpeg";

    /**
     * EFFECTS: Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public LungSimulatorUIApp() {
        // Create and set up the window.
        JFrame frame = new JFrame("Lung Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create and set up the left panel
        JPanel leftPanel = new LungProfileManagerPanel();
        //

        // Create and set up the main panel
        DisplayMetricsPanel mainPanel = new DisplayMetricsPanel();
        LungProfileManager.getInstance().addObserver(mainPanel);

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        displaySplashImage(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displaySplashImage(Dimension size) {
        JWindow splashScreen = new JWindow();
        JLabel label = new JLabel(new ImageIcon(IMG_PATH));
        splashScreen.setSize(size);
        splashScreen.add(label);
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
