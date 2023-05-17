package KickStartDev;

public interface IRemoteObject {

    public void singleLog(LogEntry entry);
    public void removeOldLogs(int amountToRemove);
    public void increaseStorageSpace(int increaseBy);
    public void addLogsInBulk(LogEntry[] logBulk);
    public LogEntry[] searchLogs(String searchTerm);


}
