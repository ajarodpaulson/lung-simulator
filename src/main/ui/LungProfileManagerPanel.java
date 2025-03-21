package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import model.LungProfileManager;

/**
 * Represents a panel for adding, saving, loading, and displaying lung profiles
 */
public class LungProfileManagerPanel extends JPanel {

    /**
     * EFFECTS: constructs a new LungProfileManagerPanel which has a LungProfileManagerToolbar for updating
     *  WorkingLungProfileList, which displays the lung profiles and is scrollable
     */
    public LungProfileManagerPanel() {
        super();
        this.setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 600));

        WorkingLungProfileList workingList = new WorkingLungProfileList();
        LungProfileManager.getInstance().addObserver(workingList);
        JScrollPane scrollPane = new JScrollPane(workingList); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JToolBar toolbar = new LungProfileManagerToolbar(); 

        add(toolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
