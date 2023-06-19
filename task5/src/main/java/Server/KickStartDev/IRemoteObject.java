package Server.KickStartDev;

import Client.CallbackSearch;
import Client.PollLogStorage;
import Shared.LogEntry;

public interface IRemoteObject {

    public void singleLog(String entry) throws Exception;
    public void removeOldLogs(int amountToRemove) throws Exception;
    public PollLogStorage increaseStorageSpace(int increaseBy);
    public void addLogsInBulk(LogEntry[] logBulk);

    public void searchLogs(String searchTerm, CallbackSearch callback);


}
