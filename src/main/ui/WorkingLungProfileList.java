package ui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.LungProfile;
import model.LungProfileManager;
import model.Observer;

/**
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-RadioButtonDemoProject.zip
 */
public class WorkingLungProfileList extends JPanel implements Observer {
    private ArrayList<JRadioButton> lungProfileButtons;
    private ButtonGroup group;
    private LungProfileManager lungProfileManager;
    private int workingListSize;

    WorkingLungProfileList() {
        lungProfileButtons = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // XXX
        lungProfileManager = LungProfileManager.getInstance();
        workingListSize = lungProfileManager.getLungProfiles().size();
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a new buttion for each element in @param lungProfiles
     */
    public void updateLungProfiles(List<LungProfile> lungProfiles) {
        System.out.println("Updating lung profile list...");
        removeAll(); // XXX
        lungProfileButtons.clear();
        group = new ButtonGroup();

        for (LungProfile lp : lungProfiles) {
            System.out.println("Adding radio button for: " + lp.getLabel());
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            JRadioButton lungProfileButton = new JRadioButton(lp.getLabel());
            lungProfileButton.setActionCommand(lp.getLabel());
            lungProfileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lungProfileManager.setActiveLungProfile(lp);
                }
            });
            group.add(lungProfileButton);
            lungProfileButtons.add(lungProfileButton);
            JButton removeButton = new JButton("X");
            removeButton.setActionCommand(lp.getLabel());
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lungProfileManager.deleteLungProfile(lp);
                }
            });
            rowPanel.add(lungProfileButton);
            rowPanel.add(removeButton);
            this.add(rowPanel);
        }

        revalidate();
        repaint();
    }

    @Override
    public void update(List<LungProfile> lungProfiles) {
        if (workingListSize == lungProfiles.size()) {
            return;
        }
        workingListSize = lungProfiles.size();
        updateLungProfiles(lungProfiles);
    }
}
