package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

public class UDPServerRequestHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(UDPServerRequestHandler.class);

    private final Invoker invoker;

    public UDPServerRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    private void startUDPHandler() {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
            logger.info("Server started on port 8080");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                socket.receive(request);
                logger.debug("Data was received from client over UDP connection");

                invoker.invoke(request.getData());
                logger.trace("invocation finished");
            }
        } catch (SocketException e) {
            logger.error("Error while creating socket {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Error while receiving data {}", e.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }



    @Override
    public void run() {
        startUDPHandler();
    }
}
