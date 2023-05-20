package KickStartDev;

public interface IRemoteObject {

    public void singleLog(String entry) throws Exception;
    public void removeOldLogs(int amountToRemove) throws Exception;
    public void increaseStorageSpace(int increaseBy);
    public void addLogsInBulk(LogEntry[] logBulk);
    public LogEntry[] searchLogs(String searchTerm);


}
