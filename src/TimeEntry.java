/**
 * Represents a time clock event entry.
 *
 * @author Jack Spencer
 * @version 1.0
 * @date 07-14-2022
 */
public class TimeEntry {

    public enum ENTRY_TYPE {
        START_SHIFT, END_SHIFT, START_LUNCH, END_LUNCH, START_BREAK, END_BREAK;
    }

    private String userName;
    private ENTRY_TYPE entryType;
    private String timeStamp;

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
     * @return entry type
     */
    public ENTRY_TYPE getEntryType() {
        return entryType;
    }

    /**
     *
     * @param entryType to set
     */
    public void setEntryType(ENTRY_TYPE entryType) {
        this.entryType = entryType;
    }

    /**
     *
     * @return timestamp formatted as 'MM-dd-yyyy HH:mm:ss'
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     *
     * @param timeStamp formatted as 'MM-dd-yyyy HH:mm:ss'
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
