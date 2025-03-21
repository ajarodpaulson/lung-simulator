package ui;

import javax.swing.*;

import model.LungProfile;
import model.LungProfileManager;
import model.LungProfile.Sex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a settings dialog for creating a new lung profile
 *
 * Code reference(s): https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 */

public class SettingsDialog extends JDialog {
    private JTextField labelField;
    private JTextField heightField;
    private JTextField tvField;
    private JTextField rrField;
    private JTextField complianceField;
    private JTextField resistanceField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JButton acceptButton;
    private JButton discardButton;
    private GridBagConstraints gbc;

    /**
     * EFFECTS: constructs a new settings dialog that allows a new lung profile to be 
     * created from the supplied information
     */
    public SettingsDialog(Frame parent) {
        super(parent, "Settings", true);
        setupGridLayout();

        createTextFieldLabel(0, "Label:");
        labelField = new JTextField(15);
        placeOnDialog(labelField);

        createTextFieldLabel(1, "Height (" + LungProfile.heightUnits + ")");
        heightField = new JTextField(15);
        placeOnDialog(heightField);

        createSexPickerAndPlaceOnDialog(2);

        createTextFieldLabel(3, "Tidal Volume (" + LungProfile.tidalVolumeUnits + "):");
        tvField = new JTextField(15);
        placeOnDialog(tvField);

        createTextFieldLabel(4, "Respiratory Rate (" + LungProfile.respRateUnits + "):");
        rrField = new JTextField(15);
        placeOnDialog(rrField);

        createTextFieldLabel(5, "Compliance (" + LungProfile.complianceUnits + "):");
        complianceField = new JTextField(15);
        placeOnDialog(complianceField);

        createTextFieldLabel(6, "Resistance (" + LungProfile.resistanceUnits + "):");
        resistanceField = new JTextField(15);
        placeOnDialog(resistanceField);

        getAcceptAndDiscardButtonPanel(7);

        finishDialogSetup(parent);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets the layout of this to a grid bag layout
     */
    private void setupGridLayout() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    }

    /**
     * MODIFIES: this
     * EFFECTS: finishes setup of this 
     */
    private void finishDialogSetup(Frame parent) {
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates the accept and discard buttons that allow for the inputted information
     * to be used in the creation of a new lung profile, requires @param row for placement in the grid layout
     */
    private void getAcceptAndDiscardButtonPanel(int row) {
        acceptButton = new JButton("Accept");
        discardButton = new JButton("Discard");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(discardButton);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        buttonPanel.revalidate();
        buttonPanel.repaint();

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAccept();
            }
        });

        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates grouped radio buttons for choosing the sex for the lung
     * profile to be created; requires @param row for placement in the grid layout
     */
    private void createSexPickerAndPlaceOnDialog(int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Sex:"), gbc);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleButton);
        sexGroup.add(femaleButton);
        JPanel sexPanel = new JPanel();
        sexPanel.add(maleButton);
        sexPanel.add(femaleButton);
        gbc.gridx = 1;
        add(sexPanel, gbc);
    }

    /**
     * MODIFIES: this
     * EFFECTS: places the @param textField on to this in the first column of the grid layout
     */
    private void placeOnDialog(JTextField textField) {
        gbc.gridx = 1;
        add(textField, gbc);
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates and places a label on this to be used for a text field
     * requires @param row for placement in the grid layout
     */
    private void createTextFieldLabel(int row, String label) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel(label), gbc);
    }

    /**
     * MODIFIES: LungProfileManager.getInstance()
     * EFFECTS: adds the lung profile with the inputted information to LungProfileManager.getInstance()
     * Throws NumberFormatException if input is invalid
     */
    private void handleAccept() {
        try {
            String label = labelField.getText();
            float height = Float.parseFloat(heightField.getText());
            Sex sex = maleButton.isSelected() ? Sex.MALE : Sex.FEMALE;
            int tv = Integer.parseInt(tvField.getText());
            int rr = Integer.parseInt(rrField.getText());
            int compliance = Integer.parseInt(complianceField.getText());
            float resistance = Float.parseFloat(resistanceField.getText());

            LungProfile lungProfile = new LungProfile(label, height, sex, tv, rr, compliance, resistance);
            LungProfileManager.getInstance().addLungProfile(lungProfile);

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter appropriate values.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
