package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.LungProfile;
import model.LungProfileManager;
import model.Observer;
import model.ScalarTime;

/**
 * Represents a panel that displays the metrics of the currently selected lung
 * profile in the WorkingLungProfileList, or an informative message if no profile
 * currently selected
 */

public class DisplayMetricsPanel extends JPanel implements Observer {
    private LungProfile lungProfile;
    private JTextArea metrics;

    /**
     * EFFECTS: constructs a new display metrics panel with non-editable text area for 
     * displaying metrics from currently selected lung profile
     */
    public DisplayMetricsPanel() {
        super();
        this.setBackground(Color.WHITE);
        setPreferredSize(new Dimension(450, 600));
        lungProfile = LungProfileManager.getInstance().getActiveLungProfile();
        metrics = new JTextArea();
        metrics.setEditable(false);

        if (lungProfile == null) {
            metrics.setText("No lung profile has been selected.");
        } else {
            metrics.setText(summarizeLungCharacteristics(lungProfile));
        }
        add(metrics);
    }

    /*
     * EFFECTS: prints formatted string with all characteristics for
     * the supplied lung profile
     */
    public String summarizeLungCharacteristics(LungProfile lp) {
        String summary = String.format(
                "Lung Profile: %s\n"
                        + "Height: %.1f %s\n"
                        + "Sex: %s\n"
                        + "Tidal Volume: %d %s\n"
                        + "Respiratory Rate: %d %s\n"
                        + "Compliance: %d %s\n"
                        + "Resistance: %.2f %s\n"
                        + "Ideal Body Weight: %.1f %s\n"
                        + "---------------------------\n",
                lp.getLabel(),
                lp.getHeight(), LungProfile.heightUnits,
                lp.getSex(),
                lp.getTidalVolume(), LungProfile.tidalVolumeUnits,
                lp.getRespRate(), LungProfile.respRateUnits,
                lp.getCompliance(), LungProfile.complianceUnits,
                lp.getResistance(), LungProfile.resistanceUnits,
                lp.getIBW(), LungProfile.idealBWUnits)
                + outputScalarMetrics(lp);
        return summary;
    }

    /*
     * EFFECTS: summarizes and return metrics for the supplied ScalarTime
     */
    protected String summarizeScalarTimeMetrics(ScalarTime st) {
        return String.format(
                "%nThe %s-time waveform has:%n"
                        + "Amplitude: %s %s%n"
                        + "Maximum: %s %s%n"
                        + "Minimum: %s %s%n"
                        + "Breath Cycle Time: %s s",
                st.getScalarName(),
                st.calculateAmplitude(), st.getUnits(),
                st.calculateMaximumScalarValue(), st.getUnits(),
                st.calculateMinimumScalarValue(), st.getUnits(),
                st.calculateBreathCycleTime());
    }

    /*
     * EFFECTS: outputs scalar metrics for the supplied lung profile
     */
    private String outputScalarMetrics(LungProfile lp) {
        return summarizeScalarTimeMetrics(lungProfile.getVolumeTimeScalar())
                + "\n"
                + summarizeScalarTimeMetrics(lungProfile.getFlowTimeScalar());
    }

    /**
     * MODIFIES: this
     * EFFECTS: displays the active lung profile in LungProfileManager.getInstance() and 
     * refreshes the display
     */
    @Override
    public void update(List<LungProfile> lungProfiles) {
        lungProfile = LungProfileManager.getInstance().getActiveLungProfile();
        if (lungProfile != null) {
            metrics.setText(summarizeLungCharacteristics(lungProfile));
        } else {
            metrics.setText("No active lung profile.");
        }
        revalidate();
        repaint();
    }
}
