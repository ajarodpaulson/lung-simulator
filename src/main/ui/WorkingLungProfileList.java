package ui;

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
 * Observes and displays the lung profile list held by
 * LungProfileManager.getInstance() and
 * allows for an individual lung profile to be selected to have its metrics
 * displayed in DisplayMetricsPanel
 * 
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-RadioButtonDemoProject.zip
 */
public class WorkingLungProfileList extends JPanel implements Observer {
    private ArrayList<JRadioButton> lungProfileButtons;
    private ButtonGroup lungProfileButtonGroup;
    private LungProfileManager lungProfileManager;
    private int workingListSize;

    /**
     * EFFECTS: creates a new WorkingLungProfileList with lung profiles placed using
     * a box layout
     */
    WorkingLungProfileList() {
        lungProfileButtons = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lungProfileManager = LungProfileManager.getInstance();
        workingListSize = lungProfileManager.getLungProfiles().size();
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates a new buttion for each element in @param lungProfiles
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

    /**
     * MODIFIES: LungProfileManager.getInstance()
     * EFFECTS: creates a remove button that deletes @param lp from
     * LungProfileManager.getInstance()
     * 
     * @return the remove button for an associated lung profile
     */
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

    /**
     * MODIFIES: LungProfileManager.getInstance()
     * EFFECTS: creates a radio button and label associated with the @param lp;
     * when the button is pressed, sets active lung profile for
     * LungProfileManager.getInstance()
     * 
     * @return the button to select and display metrics for the associated lung
     *         profile
     */
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

    /**
     * MODIFIES: this
     * EFFECTS: receives updates from the Observable
     * LungProfileManager.getInstance()
     * if the size of the lung profile list for LungProfileManager.getInstance() is
     * different
     * than workingListSize, calls updateLungProfiles(lungProfiles)
     */
    @Override
    public void update(List<LungProfile> lungProfiles) {
        if (workingListSize == lungProfiles.size()) {
            return;
        }
        workingListSize = lungProfiles.size();
        updateLungProfiles(lungProfiles);
    }
}
