package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerRequestHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(ServerRequestHandler.class);

    private final Invoker invoker;

    public ServerRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    private void startHandler() {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
            logger.info("Server started on port 8080");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                socket.receive(request);
                logger.info("Data was received from client over UDP connection");

                invoker.invoke(request.getData());
                logger.info("invocation finished");
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
        startHandler();
    }
}
