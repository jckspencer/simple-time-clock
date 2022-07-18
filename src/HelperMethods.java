import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Miscellaneous helper methods for simple time clock.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class HelperMethods {

    /**
     * Convert boolean value to integer for safe storage in SQLite database.
     * @param bool
     * @return 1 if true, 0 if false
     */
    public static int booleanToInt(boolean bool) {
        int conversion = (bool)? 1 : 0;
        return conversion;
    }

    /**
     * Get current timestamp formatted as 'MM-dd-yyyy HH:mm:ss'
     * @return timestamp string
     */
    public static String getCurrentTimestamp() {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return timestamp.format(formatter);
    }
}
