import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for time clock screen. Handles all button events
 * and dispatches requests to database manager. Displays status
 * for current session.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class TimeClockController implements ActionListener {
    private TimeClockScreen timeClockScreen;
    private DatabaseManager databaseManager;
    private JTextArea outputLog;

    /**
     * Constructor for time clock controller. Establishes database connection
     * and action listeners. Sets outputLog field
     * @param timeClockScreen
     * @param databaseManager
     */
    public TimeClockController(TimeClockScreen timeClockScreen, DatabaseManager databaseManager) {
        this.timeClockScreen = timeClockScreen;
        this.databaseManager = databaseManager;
        this.outputLog = this.timeClockScreen.getTextArea();

        this.timeClockScreen.getStartButton().addActionListener(this);
        this.timeClockScreen.getEndButton().addActionListener(this);
        this.timeClockScreen.getStartBreakButton().addActionListener(this);
        this.timeClockScreen.getEndBreakButton().addActionListener(this);
        this.timeClockScreen.getTakeLunchButton().addActionListener(this);
        this.timeClockScreen.getEndLunchButton().addActionListener(this);
        this.timeClockScreen.getLogButton().addActionListener(this);
        this.timeClockScreen.getSwitchUserButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timeClockScreen.getStartButton()) {
            startShift();
        }
        else if (e.getSource() == timeClockScreen.getEndButton()) {
            endShift();
        }
        else if (e.getSource() == timeClockScreen.getStartBreakButton()) {
            startBreak();
        }
        else if (e.getSource() == timeClockScreen.getEndBreakButton()) {
            endBreak();
        }
        else if (e.getSource() == timeClockScreen.getTakeLunchButton()) {
            startLunch();
        }
        else if (e.getSource() == timeClockScreen.getEndLunchButton()) {
            endLunch();
        }
        else if (e.getSource() == timeClockScreen.getLogButton()) {
            viewLogs();
        }
        else if (e.getSource() == timeClockScreen.getSwitchUserButton()) {
            Application.getInstance().setCurrentUser(null);
            outputLog.setText(null);
            Application.getInstance().getTimeClockScreen().setVisible(false);
            Application.getInstance().getMainScreen().setVisible(true);
        }
    }

    private void startShift() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin() && currentUser.hasActiveShift()) {
            JOptionPane.showMessageDialog(null, "User already has active shift!");
            return;
        }
        TimeEntry entry = new TimeEntry();
        entry.setUserName(currentUser.getUserName());
        entry.setEntryType(TimeEntry.ENTRY_TYPE.START_SHIFT);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());

        currentUser.setHasActiveShift(true);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("Start shift for " + currentUser.getUserName() + "\n");
    }

    private void endShift() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin()) {
            if (!currentUser.hasActiveShift()) {
                JOptionPane.showMessageDialog(null, "User does not have an active shift!");
                return;
            }
            if (currentUser.isOnBreak()) {
                JOptionPane.showMessageDialog(null, "Cannot end shift while on break.");
                return;
            }
            if (currentUser.isOnLunch()) {
                JOptionPane.showMessageDialog(null, "Cannot end shift while on lunch.");
                return;
            }
        }
        TimeEntry entry = new TimeEntry();
        entry.setUserName(currentUser.getUserName());
        entry.setEntryType(TimeEntry.ENTRY_TYPE.END_SHIFT);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());

        currentUser.setHasActiveShift(false);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("End shift for " + currentUser.getUserName() + "\n");
    }

    private void startBreak() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin()) {
            if (!currentUser.hasActiveShift()) {
                JOptionPane.showMessageDialog(null, "User does not have an active shift!");
                return;
            }
            if (currentUser.isOnBreak()) {
                JOptionPane.showMessageDialog(null, "User already has active break!");
                return;
            }
        }

        TimeEntry entry = new TimeEntry();
        entry.setUserName(currentUser.getUserName());
        entry.setEntryType(TimeEntry.ENTRY_TYPE.START_BREAK);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());

        currentUser.setOnBreak(true);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("Start break for " + currentUser.getUserName() + "\n");
    }

    private void endBreak() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin()) {
            if (!currentUser.hasActiveShift()) {
                JOptionPane.showMessageDialog(null, "User does not have an active shift!");
                return;
            }
            if (!currentUser.isOnBreak()) {
                JOptionPane.showMessageDialog(null, "User does not have an active break!");
                return;
            }
        }
        TimeEntry entry = new TimeEntry();
        entry.setUserName(currentUser.getUserName());
        entry.setEntryType(TimeEntry.ENTRY_TYPE.END_BREAK);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());

        currentUser.setOnBreak(false);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("End break for " + currentUser.getUserName() + "\n");
    }

    private void startLunch() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin()) {
            if (!currentUser.hasActiveShift()) {
                JOptionPane.showMessageDialog(null, "User does not have an active shift!");
                return;
            }
            if (currentUser.isOnLunch()) {
                JOptionPane.showMessageDialog(null, "User already on lunch!");
                return;
            }
        }

        TimeEntry entry = new TimeEntry();
        entry.setUserName(currentUser.getUserName());
        entry.setEntryType(TimeEntry.ENTRY_TYPE.START_LUNCH);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());

        currentUser.setOnLunch(true);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("Start lunch for " + currentUser.getUserName() + "\n");
    }

    private void endLunch() {
        User currentUser = Application.getInstance().getCurrentUser();
        if (!currentUser.isAdmin()) {
            if (!currentUser.hasActiveShift()) {
                JOptionPane.showMessageDialog(null, "User does not have an active shift!");
                return;
            }
            if (!currentUser.isOnLunch()) {
                JOptionPane.showMessageDialog(null, "User is not on lunch.");
                return;
            }
        }

        TimeEntry entry = makeEntry(currentUser, TimeEntry.ENTRY_TYPE.END_LUNCH);

        currentUser.setOnLunch(false);
        databaseManager.saveEntry(entry);
        databaseManager.saveUser(currentUser);

        outputLog.append("End lunch for " + currentUser.getUserName() + "\n");
    }

    private void viewLogs() {
        User currentUser = Application.getInstance().getCurrentUser();
        System.out.println("view logs");
        if (!currentUser.isAdmin()) {
            JOptionPane.showMessageDialog(null, "User does not have admin privileges.");
            return;
        }
        JTable table = new JTable(databaseManager.loadEntriesTable());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,500));
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    private TimeEntry makeEntry(User user, TimeEntry.ENTRY_TYPE type) {
        TimeEntry entry = new TimeEntry();
        entry.setUserName(user.getUserName());
        entry.setEntryType(type);
        entry.setTimeStamp(HelperMethods.getCurrentTimestamp());
        return entry;
    }
}
