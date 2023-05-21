package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRequestHandler {
    private static final Logger logger = LogManager.getLogger(ClientRequestHandler.class);

    //The Class handled the network communication with the server
    public static void sendMessageViaUDP(byte[] dataToSend) {

        try {
            //Create the socket to send the data
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 8080;

            logger.debug("Creating the packet to send");
            DatagramPacket request = new DatagramPacket(dataToSend, dataToSend.length, aHost, serverPort);

            clientSocket.send(request);
            logger.debug("Data was sent to server over UDP connection");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendMessageViaTCP(byte[] dataToSend) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            dataOutputStream.writeInt(dataToSend.length);
            dataOutputStream.write(dataToSend);
            logger.debug("Data was sent to server over TCP connection");

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            final int ACK = dataInputStream.readInt();

            dataOutputStream.close();
            dataInputStream.close();
            clientSocket.close();


        } catch (IOException e) {
            logger.error("Error while creating socket from Type: {}, Message: {}", e.getClass(), e.getMessage());
            throw new Exception(e);
        }
    }

}
