package KickStartDev;

public interface IRemoteObject {

    public void logSingleEntry(LogEntry entry);
    public void removeOldLogs(int amountToRemove);
    public void addLogsInBulk(LogEntry[] logBulk);
    public LogEntry[] search(String searchTerm);


}
