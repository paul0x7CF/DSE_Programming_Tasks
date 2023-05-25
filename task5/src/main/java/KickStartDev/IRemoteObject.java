package KickStartDev;

import Client.CallbackIncLogStorage;

public interface IRemoteObject {

    public void singleLog(String entry) throws Exception;
    public void removeOldLogs(int amountToRemove) throws Exception;
    public void increaseStorageSpace(int increaseBy, CallbackIncLogStorage callback);
    void addLogsInBulk(String[] logBulk);

    public LogEntry[] searchLogs(String searchTerm);


}
