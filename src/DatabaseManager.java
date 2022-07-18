import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 * SQLite database manager that supports functions
 * to access and modify time clock data.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */

public class DatabaseManager {
    private final Connection connection;

    /**
     * Constructor for database manager.
     * Sets database connection.
     * @param connection SQLite database connection
     */
    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Load user from Users table in database.
     * @param username username string
     * @return User object
     */
    public User loadUser(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE UserName = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("UserName"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                user.setHasActiveShift(resultSet.getBoolean("hasActiveShift"));
                user.setOnBreak(resultSet.getBoolean("isOnBreak"));
                user.setOnLunch(resultSet.getBoolean("isOnLunch"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save user to Users table in database.
     * @param user User object
     * @return true if saved successfully
     */
    public boolean saveUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE UserName = ?");
            statement.setString(1, user.getUserName());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this user exists, update its fields
                statement = connection.prepareStatement("UPDATE Users SET isAdmin = ?, hasActiveShift = ?, isOnBreak = ?, isOnLunch = ? WHERE UserName = ?");
                statement.setString(5, user.getUserName());
                statement.setInt(1, HelperMethods.booleanToInt(user.isAdmin()));
                statement.setInt(2, HelperMethods.booleanToInt(user.hasActiveShift()));
                statement.setInt(3, HelperMethods.booleanToInt(user.isOnBreak()));
                statement.setInt(4, HelperMethods.booleanToInt(user.isOnLunch()));
            }
            else { // this user does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, user.getUserName());
                statement.setInt(2, HelperMethods.booleanToInt(user.isAdmin()));
                statement.setInt(3, HelperMethods.booleanToInt(user.hasActiveShift()));
                statement.setInt(4, HelperMethods.booleanToInt(user.isOnBreak()));
                statement.setInt(5, HelperMethods.booleanToInt(user.isOnLunch()));
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    /**
     * Save time event entry to TimeEntries table in database.
     * @param entry TimeEntry object
     * @return true if saved successfully
     */
    public boolean saveEntry(TimeEntry entry) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TimeEntries VALUES (?, ?, ?)");
            statement.setString(1, entry.getUserName());
            statement.setString(2, entry.getEntryType().name());
            statement.setString(3, entry.getTimeStamp());
            statement.execute();
            statement.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Load all entries in TimeEntries table in database.
     * @return DefaultTableModel object to pass into JTable
     */
    public DefaultTableModel loadEntriesTable() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TimeEntries");
            ResultSetMetaData metaData = resultSet.getMetaData();

            Vector<String> columns = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int col = 1; col <= columnCount; col++) {
                columns.add(metaData.getColumnName(col));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                for (int col = 1; col <= columnCount; col++) {
                    vector.add(resultSet.getObject(col));
                }
                data.add(vector);
            }
            statement.close();
            return new DefaultTableModel(data, columns);

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }
}
