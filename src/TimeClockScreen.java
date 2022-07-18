import javax.swing.*;

/**
 * GUI for time clock functions.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class TimeClockScreen extends JFrame {
    private JPanel panel;
    private JButton startButton;
    private JButton endButton;
    private JButton startBreakButton;
    private JButton endBreakButton;
    private JButton takeLunchButton;
    private JButton endLunchButton;
    private JButton switchUserButton;
    private JButton logButton;
    private JTextArea textArea;

    /**
     *
     * @return start shift button
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     *
     * @return end shift button
     */
    public JButton getEndButton() {
        return endButton;
    }

    /**
     *
     * @return start break button
     */
    public JButton getStartBreakButton() {
        return startBreakButton;
    }

    /**
     *
     * @return end break button
     */
    public JButton getEndBreakButton() {
        return endBreakButton;
    }

    /**
     *
     * @return take lunch button
     */
    public JButton getTakeLunchButton() {
        return takeLunchButton;
    }

    /**
     *
     * @return end lunch button
     */
    public JButton getEndLunchButton() {
        return endLunchButton;
    }

    /**
     *
     * @return login button
     */
    public JButton getLogButton() { return logButton; }

    /**
     *
     * @return switch user button
     */
    public JButton getSwitchUserButton() {
        return switchUserButton;
    }

    /**
     *
     * @return text area for status
     */
    public JTextArea getTextArea() { return textArea; }

    /**
     * Constructor for time clock screen.
     */
    public TimeClockScreen() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}
