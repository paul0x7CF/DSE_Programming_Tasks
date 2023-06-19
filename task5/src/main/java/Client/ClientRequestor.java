package Client;

import Shared.IMarshall;
import Shared.LogEntry;
import Shared.RequestMessage;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientRequestor {

    public ClientRequestor() {
        new Thread(new UDPClientRequestHandlerIn(this)).start();
    }

    private CallbackSearch callbackSearch;
    private PollLogStorage pollLogStorage;

    private static final Logger logger = LogManager.getLogger(ClientRequestor.class);

    //The Class handles the requests to the Remote Object on the server
    public static void invokeObjectByFireAndForget(RequestMessage requestMessage){
        try {
            ClientRequestHandlerOut.sendMessageViaUDPNoAck(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }

    }

    public void handleSyncWithServer(RequestMessage requestMessage) {
        try {
            ClientRequestHandlerOut.sendMessageViaTCPTargetAck(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling Sync with Server the message");
            throw new RuntimeException(e);
        }
    }

    public void handleCallback(RequestMessage requestMessage, CallbackSearch callback){
        try {
            this.callbackSearch = callback;
            ClientRequestHandlerOut.sendMessageViaTCPTransportACK(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the Callback message");
            throw new RuntimeException(e);
        }
    }

    public PollLogStorage handlePolling(RequestMessage requestMessage) {
        this.pollLogStorage = new PollLogStorage();
        new Thread(new PollLogStorageListener(this.pollLogStorage, requestMessage),"PollListener").start();
        return this.pollLogStorage;
    }

    public void handleResponse(byte[] data) {

        try {
            ResponseMessage responseMessage = IMarshall.unmarshall(data);
            switch (responseMessage.getMethod()){
                case searchLogs -> {
                    logger.info("Callback received from server");
                    this.callbackSearch.callback((LogEntry[]) responseMessage.getResponseData());
                }
                default -> {
                    logger.debug("Message not supported");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
