package ui;

import javax.swing.*;

import model.LungProfile;
import model.LungProfileManager;
import model.LungProfile.Sex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog implements ActionListener {
    private JTextField labelField, heightField, tvField, rrField, complianceField, resistanceField;
    private JRadioButton maleButton, femaleButton;
    private JButton acceptButton, discardButton;

    public SettingsDialog(Frame parent) {
        super(parent, "Settings", true);
        System.out.println("making a new settings dialog");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Label:"), gbc);
        labelField = new JTextField(15);
        gbc.gridx = 1;
        add(labelField, gbc);

        // Height
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Height (" + LungProfile.heightUnits + ")"), gbc);
        heightField = new JTextField(15);
        gbc.gridx = 1;
        add(heightField, gbc);

        // Sex
        gbc.gridx = 0;
        gbc.gridy = 2;
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

        // TV
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Tidal Volume (" + LungProfile.tidalVolumeUnits + "):"), gbc);
        tvField = new JTextField(15);
        gbc.gridx = 1;
        add(tvField, gbc);

        // RR
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Respiratory Rate (" + LungProfile.respRateUnits + "):"), gbc);
        rrField = new JTextField(15);
        gbc.gridx = 1;
        add(rrField, gbc);

        // Compliance
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Compliance (" + LungProfile.complianceUnits + "):"), gbc);
        complianceField = new JTextField(15);
        gbc.gridx = 1;
        add(complianceField, gbc);

        // Resistance
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Resistance (" + LungProfile.resistanceUnits + "):"), gbc);
        resistanceField = new JTextField(15);
        gbc.gridx = 1;
        add(resistanceField, gbc);

        // Buttons
        acceptButton = new JButton("Accept");
        discardButton = new JButton("Discard");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(discardButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        buttonPanel.revalidate();
        buttonPanel.repaint();

        // Button Actions
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

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

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
