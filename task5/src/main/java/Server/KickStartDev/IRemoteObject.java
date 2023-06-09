package Server.KickStartDev;

import Client.CallbackIncLogStorage;
import Client.PollSearch;

public interface IRemoteObject {

    public void singleLog(String entry) throws Exception;
    public void removeOldLogs(int amountToRemove) throws Exception;
    public void increaseStorageSpace(int increaseBy, CallbackIncLogStorage callback);
    void addLogsInBulk(String[] logBulk);

    public PollSearch searchLogs(String searchTerm);


}
