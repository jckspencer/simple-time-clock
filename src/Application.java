import java.sql.*;

/**
 * A simple time clock application that supports employees
 * starting/ending shifts, breaks, and lunches. All events
 * are logged and viewable by admin users. Users are
 * identified with a unique username.
 *
 * Log in with 'demoUser' for basic functions. Log in with
 * 'adminUser' for admin privileges. Alternatively, create
 * a new user within the application.
 *
 * @author  Jack Spencer
 * @version 1.0
 * @since   07-14-2022
 */

public class Application {

    private static Application instance;

    /**
     * Get instance of the running application. Enforces
     * singleton design principles.
     *
     * @return current application instance
     */
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    private Connection connection;

    private final DatabaseManager databaseManager;

    private User currentUser = null;

    /**
     *
     * @param user set active application user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     *
     * @return active application user
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    private final MainScreen mainScreen = new MainScreen();

    /**
     *
     * @return main screen
     */
    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public final MainScreenController mainScreenController;

    private final TimeClockScreen timeClockScreen = new TimeClockScreen();

    /**
     *
     * @return time clock screen
     */
    public TimeClockScreen getTimeClockScreen() {
        return timeClockScreen;
    }

    public final TimeClockController timeClockController;

    /**
     * Database initialization is not yet implemented.
     * @param statement SQLite statement for database initialization
     * @throws SQLException
     */
    private void initializeDatabase(Statement statement) throws SQLException {
        System.out.println("Database initialization not yet implemented.");
    }

    /**
     * Constructor for application instance.
     * Establishes connection to SQLite database and instantiates controllers.
     */
    private Application() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data/timeclock.db");

            Statement statement = connection.createStatement();
//            if (!statement.executeQuery("SELECT * FROM Users").next()) {
//                initializeDatabase(statement);
//            }
        }
        catch (ClassNotFoundException ex) {
            System.out.println("SQLite is not installed. Exit with error.");
            ex.printStackTrace();
            System.exit(1);
        }
        catch (SQLException ex) {
            System.out.println("SQLite database is not ready. Exit with error.");
            ex.printStackTrace();
            System.exit(2);
        }
        databaseManager = new DatabaseManager(connection);
        mainScreenController = new MainScreenController(mainScreen, databaseManager);
        timeClockController = new TimeClockController(timeClockScreen, databaseManager);
    }

    public static void main(String[] args) {
        Application.getInstance().getMainScreen().setVisible(true);
    }
}
