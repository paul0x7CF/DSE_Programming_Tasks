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

public class ClientRequestHandlerOut {
    private static final Logger logger = LogManager.getLogger(ClientRequestHandlerOut.class);

    //The Class handled the network communication with the server
    public static void sendMessageViaUDPNoAck(byte[] dataToSend) {

        try {
            //Create the socket to send the data
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 8080;

            DatagramPacket request = new DatagramPacket(dataToSend, dataToSend.length, aHost, serverPort);

            clientSocket.send(request);
            logger.debug("Data was sent to server over UDP connection");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] sendMessageViaTCPTargetAck(byte[] dataToSend) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Write the length of the data to the stream
            dataOutputStream.writeInt(dataToSend.length);
            // Write the data to the stream
            dataOutputStream.write(dataToSend);
            logger.debug("Data was sent to server over TCP connection with ACK On Target");

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            byte[] response = new byte[dataInputStream.readInt()];
            logger.trace("response received from server with {} bytes", response.length);
            dataInputStream.readFully(response, 0, response.length);
            logger.trace("received response from server");

            dataOutputStream.close();
            dataInputStream.close();
            clientSocket.close();

            return response;

        } catch (IOException e) {
            logger.error("Error in the Communication from Type: {}, Message: {}", e.getClass(), e.getMessage());
            throw new Exception(e);
        }
    }

    public static void sendMessageViaTCPTransportACK(byte[] dataToSend) throws IOException {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            dataOutputStream.writeInt(dataToSend.length);
            dataOutputStream.write(dataToSend);
            logger.debug("Data was sent to server over TCP connection with ACK On Transport");


            dataOutputStream.close();
            clientSocket.close();


        } catch (IOException e) {
            logger.error("Error in the Communication from Type: {}, Message: {}", e.getClass(), e.getMessage());
            throw new IOException(e);
        }
    }

}
