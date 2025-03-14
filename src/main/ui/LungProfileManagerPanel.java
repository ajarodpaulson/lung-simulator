package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import model.LungProfile;
import model.LungProfileManager;

/**
 * Code reference(s): 
 */

public class LungProfileManagerPanel extends JPanel {
    public LungProfileManagerPanel(LungProfileManager lpManager) {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, 600)); // Set width, full height

        // Create and setup scroll panel
        WorkingLungProfileList workingList = new WorkingLungProfileList();
        JScrollPane scrollPane = new JScrollPane(workingList); // XXX design?
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Create and setup toolbar
        JToolBar toolbar = new LungProfileManagerToolbar(lpManager); // FIXME

        // Add toolbar and scroll panel
        add(toolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
