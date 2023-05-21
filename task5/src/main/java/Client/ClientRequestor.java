package Client;

import KickStartDev.RequestMessage;

public class ClientRequestor {

    //The Class handles the requests to the Remote Object on the server
    public static void invokeObjectByFireAndForget(RequestMessage requestMessage) throws Exception {
        ClientRequestHandler.sendMessageViaUDP(requestMessage.marshall());

    }

    public static void handleSyncWithServer(RequestMessage requestMessage) throws Exception {
        ClientRequestHandler.sendMessageViaTCP(requestMessage.marshall());
    }
}
