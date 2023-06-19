package Server;

import Client.CallbackSearch;
import Shared.EKnownMethods;
import Shared.LogEntry;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CallbackProxy implements CallbackSearch {

    private static final Logger logger = LogManager.getLogger(CallbackProxy.class);

    @Override
    public void callback(LogEntry[] logEntries) {
        ResponseMessage responseMessage = new ResponseMessage(EKnownMethods.searchLogs, logEntries);
        try {
            ServerRequestHandlerOut.sendViaUDP(responseMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }

}
