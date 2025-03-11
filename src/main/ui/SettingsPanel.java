package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(800, 100)); // Full width, set height
    }
}
