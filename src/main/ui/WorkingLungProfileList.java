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
    private ButtonGroup lungProfileButtonGroup;
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

     /*
      * jobs:
      remove all the components from 
      */
    public void updateLungProfiles(List<LungProfile> lungProfiles) {
        removeAll();
        lungProfileButtons.clear();
        lungProfileButtonGroup = new ButtonGroup();

        for (LungProfile lp : lungProfiles) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            JRadioButton lungProfileButton = makeLungProfileButton(lp);
            lungProfileButtons.add(lungProfileButton);
            JButton removeButton = makeRemoveButton(lp);
            rowPanel.add(lungProfileButton);
            rowPanel.add(removeButton);
            this.add(rowPanel);
        }
        revalidate();
        repaint();
    }

    private JButton makeRemoveButton(LungProfile lp) {
        JButton removeButton = new JButton("X");
        removeButton.setActionCommand(lp.getLabel());
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lungProfileManager.deleteLungProfile(lp);
            }
        });
        return removeButton;
    }

    private JRadioButton makeLungProfileButton(LungProfile lp) {
        JRadioButton lungProfileButton = new JRadioButton(lp.getLabel());
        lungProfileButton.setActionCommand(lp.getLabel());
        lungProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lungProfileManager.setActiveLungProfile(lp);
            }
        });
        lungProfileButtonGroup.add(lungProfileButton);
        return lungProfileButton;
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
