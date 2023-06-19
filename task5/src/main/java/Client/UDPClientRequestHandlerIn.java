package Client;

import Server.UDPServerRequestHandlerIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClientRequestHandlerIn implements Runnable{

    ClientRequestor requestor;

    public UDPClientRequestHandlerIn(ClientRequestor clientRequestor) {
        this.requestor = clientRequestor;
    }

    private static final Logger logger = LogManager.getLogger(UDPClientRequestHandlerIn.class);

    private void handleMessage() {
        DatagramSocket socket = null;
        try {
            final int port = 5000;
            socket = new DatagramSocket(port);
            while (true) {
                byte[] buffer = new byte[4000];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);

                logger.info("Listening for response from server on Port {}", port);
                socket.receive(response);
                logger.debug("Response received from server");
                requestor.handleResponse(response.getData());
            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null)
                socket.close();
        }

    }

    @Override
    public void run() {
       handleMessage();

    }
}
