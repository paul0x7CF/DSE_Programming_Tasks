package Client;

import KickStartDev.RequestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientRequestor {

    private static final Logger logger = LogManager.getLogger(ClientRequestor.class);

    //The Class handles the requests to the Remote Object on the server
    public static void invokeObjectByFireAndForget(RequestMessage requestMessage){
        try {
            ClientRequestHandler.sendMessageViaUDP(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }

    }

    public static void handleSyncWithServer(RequestMessage requestMessage) {
        try {
            ClientRequestHandler.sendMessageViaTCP(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }

    public static void handleCallback(RequestMessage requestMessage){
        try {
            ClientRequestHandler.sendMessageViaTCP(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }
}
