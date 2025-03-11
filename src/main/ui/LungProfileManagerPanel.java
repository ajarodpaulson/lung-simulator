package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Code reference(s): 
 */

public class LungProfileManagerPanel extends JPanel {
    public LungProfileManagerPanel() {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600)); // Set width, full height

        // Create and setup toolbar
        JToolBar toolbar = new LungProfileManagerToolbar();

        // Create and setup scroll panel

        // Add toolbar and scroll panel
        add(toolbar, BorderLayout.NORTH);
    }
}
