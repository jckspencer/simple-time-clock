/**
 * Represents an application user. Users may
 * have admin privileges.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class User {
    private String userName;
    private boolean isAdmin = false;
    private boolean hasActiveShift = false;
    private boolean isOnBreak = false;
    private boolean isOnLunch = false;

    /**
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return true if user has admin privileges
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param admin boolean
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     *
     * @return true if user has active shift
     */
    public boolean hasActiveShift() {
        return hasActiveShift;
    }

    /**
     *
     * @param hasActiveShift boolean
     */
    public void setHasActiveShift(boolean hasActiveShift) {
        this.hasActiveShift = hasActiveShift;
    }

    /**
     *
     * @return true if user on break
     */
    public boolean isOnBreak() {
        return isOnBreak;
    }

    /**
     *
     * @param onBreak boolean
     */
    public void setOnBreak(boolean onBreak) {
        isOnBreak = onBreak;
    }

    /**
     *
     * @return true if user on lunch
     */
    public boolean isOnLunch() {
        return isOnLunch;
    }

    /**
     *
     * @param onLunch boolean
     */
    public void setOnLunch(boolean onLunch) {
        isOnLunch = onLunch;
    }
}

