package Client;

import Shared.EKnownMethods;
import Server.KickStartDev.IRemoteObject;
import Server.KickStartDev.LogEntry;
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
        requestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.singleLog, entry, EResult.NO_RESULT));

    }
    @Override
    public void removeOldLogs(int amountToRemove) throws Exception {
        logger.debug("asking for removeOldLogs methode with parameter {}", amountToRemove);
        requestor.handleSyncWithServer(new RequestMessage(EKnownMethods.removeOldLogs, amountToRemove, EResult.ACK_ON_TARGET));

    }

    @Override
    public void increaseStorageSpace(int increaseBy, CallbackIncLogStorage callback) {
        logger.debug("asking for increaseStorageSpace methode with parameter {}", increaseBy);
        requestor.handleCallback(new RequestMessage(EKnownMethods.increaseStorageSpace, increaseBy, EResult.CALLBACK), callback);

    }


    @Override
    public void addLogsInBulk(String[] logBulk) {
        logger.debug("asking for addLogsInBulk methode with {} parameters", logBulk.length);

    }

    @Override
    public PollSearch searchLogs(String searchTerm) {
        //return new LogEntry[0];
        //TODO: implement logic
        requestor.handlePolling(new RequestMessage(EKnownMethods.searchLogs, searchTerm, EResult.ACK_ON_TARGET));
        return null;
    }
}
