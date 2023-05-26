package Client;

import Shared.EKnownMethods;
import Server.KickStartDev.IRemoteObject;
import Server.KickStartDev.LogEntry;
import Shared.RequestMessage;
import Shared.EResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientProxy implements IRemoteObject {
    private static final Logger logger = LogManager.getLogger(ClientProxy.class);
    @Override
    public void singleLog(String entry) throws Exception {
        logger.debug("asking for singleLog methode with parameter {}", entry);
        ClientRequestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.singleLog, entry, EResult.NO_RESULT));

    }
    @Override
    public void removeOldLogs(int amountToRemove) throws Exception {
        logger.debug("asking for removeOldLogs methode with parameter {}", amountToRemove);
        ClientRequestor.handleSyncWithServer(new RequestMessage(EKnownMethods.removeOldLogs, amountToRemove, EResult.ACK_ON_TARGET));

    }

    @Override
    public void increaseStorageSpace(int increaseBy, CallbackIncLogStorage callback) {
        logger.debug("asking for increaseStorageSpace methode with parameter {}", increaseBy);
        ClientRequestor.handleCallback(new RequestMessage(EKnownMethods.increaseStorageSpace, increaseBy, EResult.CALLBACK));

    }


    @Override
    public void addLogsInBulk(String[] logBulk) {
        logger.debug("asking for addLogsInBulk methode with {} parameters", logBulk.length);

    }

    @Override
    public LogEntry[] searchLogs(String searchTerm) {
        return new LogEntry[0];
    }
}
