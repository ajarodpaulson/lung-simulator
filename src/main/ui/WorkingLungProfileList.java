package ui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.LungProfile;

/**
 * Code reference(s):
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-RadioButtonDemoProject.zip
 */
public class WorkingLungProfileList extends JPanel implements ActionListener {
    private ArrayList<JRadioButton> lungProfileButtons;
    private ButtonGroup group;

    WorkingLungProfileList() {
        lungProfileButtons = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // XXX
        setPreferredSize(new Dimension(200, 600));
        updateLungProfiles();
    }

    public void updateLungProfiles() {
        System.out.println("Updating lung profile list...");
        removeAll(); // XXX
        lungProfileButtons.clear();
        group = new ButtonGroup();

        for (LungProfile lp : LungSimulatorGUIApp.lpManager.getLungProfiles()) {
            System.out.println("Adding radio button for: " + lp.getLabel());
            JRadioButton lungProfileButton = new JRadioButton(lp.getLabel());
            lungProfileButton.setActionCommand(lp.getLabel());
            lungProfileButton.addActionListener(this);
            group.add(lungProfileButton);
            lungProfileButtons.add(lungProfileButton);
            this.add(lungProfileButton);
        }

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO get selected lung profile to display metrics
    }
}
