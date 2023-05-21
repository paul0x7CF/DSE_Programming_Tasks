package KickStartDev;

public interface IRemoteObject {

    public void singleLog(String entry) throws Exception;
    public void removeOldLogs(int amountToRemove) throws Exception;
    public void increaseStorageSpace(int increaseBy);
    public void addLogsInBulk(String[] logBulk);
    public LogEntry[] searchLogs(String searchTerm);


}
