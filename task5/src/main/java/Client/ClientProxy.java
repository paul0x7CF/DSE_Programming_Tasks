package Client;

import Shared.EKnownMethods;
import Server.KickStartDev.IRemoteObject;
import Shared.LogEntry;
import Shared.RequestMessage;
import Shared.EResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientProxy implements IRemoteObject {

    private final ClientRequestor requestor = new ClientRequestor();
    private static final Logger logger = LogManager.getLogger(ClientProxy.class);
    @Override
    public void singleLog(String entry) throws Exception {
        logger.debug("asking for singleLog methode with parameter {}", entry);
        ClientRequestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.singleLog, entry, EResult.NO_RESULT));

    }
    @Override
    public void removeOldLogs(int amountToRemove) throws Exception {
        logger.debug("asking for removeOldLogs methode with parameter {}", amountToRemove);
        requestor.handleSyncWithServer(new RequestMessage(EKnownMethods.removeOldLogs, amountToRemove, EResult.ACK_ON_TARGET));

    }

    @Override
    public PollLogStorage increaseStorageSpace(int increaseBy) {
        logger.debug("asking for increaseStorageSpace methode with parameter {}", increaseBy);
        PollLogStorage resultPoll = requestor.handlePolling(new RequestMessage(EKnownMethods.increaseStorageSpace, increaseBy, EResult.ACK_ON_TARGET));
        return resultPoll;

    }


    @Override
    public void addLogsInBulk(LogEntry[] logBulk) {
        logger.debug("asking for addLogsInBulk methode with {} parameters", logBulk.length);
        ClientRequestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.addLogsInBulk, logBulk, EResult.NO_RESULT));

    }

    @Override
    public void searchLogs(String searchTerm, CallbackSearch callback) {
        requestor.handleCallback(new RequestMessage(EKnownMethods.searchLogs, searchTerm, EResult.CALLBACK), callback);
    }
}
