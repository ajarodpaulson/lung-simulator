package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * Code reference(s): https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ToolBarDemo2Project.zip
 */

public class LungProfileManagerToolbar extends JToolBar implements ActionListener {
    static final private String NEW = "NEW";
    static final private String LOAD = "LOAD";
    static final private String SAVE = "SAVE";

    public LungProfileManagerToolbar() {
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
            // TODO: add new lung profile to displayed list
        } else if (SAVE.equals(cmd)) {
            // TODO: save currently displayed lung profiles to file
        } else if (LOAD.equals(cmd)) {
            // TODO: load currently displayed lung profiles to file
        }
    }
}
