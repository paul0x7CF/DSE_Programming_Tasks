package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandler {
    private static final Logger logger = LogManager.getLogger(ClientRequestHandler.class);

    //The Class handled the network communication with the server
    public static void sendMessageViaUDP(byte[] dataToSend) {

        try {
            //Create the socket to send the data
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 8080;
            DatagramPacket request = new DatagramPacket(dataToSend, dataToSend.length, aHost, serverPort);
            clientSocket.send(request);
            logger.info("Data was sent to server over UDP connection");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
