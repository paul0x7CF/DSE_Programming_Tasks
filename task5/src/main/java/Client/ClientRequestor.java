package Client;

import Shared.IMarshall;
import Shared.RequestMessage;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientRequestor {

    public ClientRequestor() {
        new Thread(new UDPClientRequestHandlerIn(this)).start();
    }

    private CallbackIncLogStorage callbackOnIncLogStorage;
    private PollSearch pollSearchObject;

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

    public void handleCallback(RequestMessage requestMessage, CallbackIncLogStorage callback){
        try {
            this.callbackOnIncLogStorage = callback;
            ClientRequestHandlerOut.sendMessageViaTCPTransportACK(requestMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the Callback message");
            throw new RuntimeException(e);
        }
    }

    public PollSearch handlePolling(RequestMessage requestMessage) {
        this.pollSearchObject = new PollSearch();
        new Thread(new PollSearchListener(this.pollSearchObject, requestMessage),"PollListener").start();
        return this.pollSearchObject;
    }

    public void handleResponse(byte[] data) {

        try {
            ResponseMessage responseMessage = IMarshall.unmarshall(data);
            switch (responseMessage.getMethod()){
                case increaseStorageSpace -> {
                    logger.info("Callback received from server");
                    this.callbackOnIncLogStorage.callback((int) responseMessage.getResponseData());

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
