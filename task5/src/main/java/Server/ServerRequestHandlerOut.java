package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ServerRequestHandlerOut {

    private static final Logger logger = LogManager.getLogger(ServerRequestHandlerOut.class);

    public static void sendViaUDP(byte[] dataToSend) {
        try {
            DatagramSocket aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 5000;

            DatagramPacket response = new DatagramPacket(
                    dataToSend,
                    dataToSend.length,
                    aHost,
                    serverPort
            );

            logger.debug("Sending response to client on port {}", serverPort);
            aSocket.send(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
