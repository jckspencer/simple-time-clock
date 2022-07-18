import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for main screen. Handles all button events
 * and dispatches requests to database manager.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class MainScreenController implements ActionListener {
    private final MainScreen mainScreen;
    private final DatabaseManager databaseManager;

    /**
     * Constructor for main screen controller. Establishes database
     * connection and action listeners for buttons.
     * @param mainScreen main screen instantiated by the application
     * @param databaseManager database manager instantiated by the application
     */
    public MainScreenController(MainScreen mainScreen, DatabaseManager databaseManager) {
        this.mainScreen = mainScreen;
        this.databaseManager = databaseManager;
        this.mainScreen.getEnterButton().addActionListener(this);
        this.mainScreen.getCreateButton().addActionListener(this);
    }

    /**
     * Handles button events.
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = mainScreen.getTxtUserName().getText().trim();
        if (e.getSource() == mainScreen.getEnterButton()) {
            logIn(username);
        }
        else if (e.getSource() == mainScreen.getCreateButton()) {
            createUser(username);
        }
    }

    /**
     * Loads user based on input name and sets current application
     * user.
     * @param username
     */
    private void logIn(String username) {
        User user = databaseManager.loadUser(username);
        if (user == null) { // check if user does not exist
            JOptionPane.showMessageDialog(null, "User not found. Click 'Create' to make a new user.");
        }
        else {
            Application.getInstance().setCurrentUser(user);
            System.out.println(Application.getInstance().getCurrentUser().getUserName());
            this.mainScreen.setVisible(false);
            Application.getInstance().getTimeClockScreen().setVisible(true);
        }
    }

    /**
     * Creates new user and saves to database.
     * @param username
     */
    private void createUser(String username) {
        User user = databaseManager.loadUser(username);
        if (user != null) { // check if user exists
            JOptionPane.showMessageDialog(null, "User already exists!");
        }
        else {
            boolean isAdmin = mainScreen.getSetAdminCheckBox().isSelected();
            user = new User();
            user.setUserName(username);
            user.setAdmin(isAdmin);
            databaseManager.saveUser(user);
        }
    }
}
