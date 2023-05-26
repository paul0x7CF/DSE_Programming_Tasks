package Client;

import Shared.IMarshall;
import Shared.RequestMessage;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientRequestor {

    private static final Logger logger = LogManager.getLogger(ClientRequestor.class);

    //The Class handles the requests to the Remote Object on the server
    public static void invokeObjectByFireAndForget(RequestMessage requestMessage){
        try {
            ClientRequestHandlerOut.sendMessageViaUDP(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }

    }

    public static void handleSyncWithServer(RequestMessage requestMessage) {
        try {
            ClientRequestHandlerOut.sendMessageViaTCPTargetAck(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }

    public static void handleCallback(RequestMessage requestMessage){
        try {
            ClientRequestHandlerOut.sendMessageViaTCPTransportACK(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }

    public static void handleResponse(byte[] data) {

        try {
            ResponseMessage responseMessage = IMarshall.unmarshall(data);
            switch (responseMessage.getMethod()){
                case Callback -> {
                    logger.info("Callback received from server");

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
