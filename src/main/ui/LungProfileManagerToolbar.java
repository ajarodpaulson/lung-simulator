package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.LungProfile;
import model.LungProfile.Sex;
import model.LungProfileManager;

/**
 * Code reference(s): https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ToolBarDemo2Project.zip
 */

public class LungProfileManagerToolbar extends JToolBar implements ActionListener {
    static final private String NEW = "NEW";
    static final private String LOAD = "LOAD";
    static final private String SAVE = "SAVE";
    private LungProfileManager lpManager;

    public LungProfileManagerToolbar(LungProfileManager lpManager) {
        this.lpManager = lpManager;
        addButtons(this);
        setFloatable(false);
        setRollover(true);
    }

     protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        button = new JButton("New");
        button.setActionCommand(NEW);
        button.setToolTipText("Create a new lung profile");
        button.addActionListener(this);
        add(button);

        button = new JButton("Save");
        button.setActionCommand(SAVE);
        button.setToolTipText("Save lung profiles in current list");
        button.addActionListener(this);
        add(button);

        button = new JButton("Load");
        button.setActionCommand(LOAD);
        button.setToolTipText("Load the most recently saved lung profiles");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (NEW.equals(cmd)) {
            System.out.println("Adding a new lung profile...");
            int num = lpManager.getLungProfiles().size();
            lpManager.addLungProfile(new LungProfile("New Lung Profile" + num, 152.4f, Sex.FEMALE, 400, 16, 100, 1.0f));
        } else if (SAVE.equals(cmd)) {
            // TODO: save currently displayed lung profiles to file
        } else if (LOAD.equals(cmd)) {
            // TODO: load currently displayed lung profiles to file
        }
    }
}
