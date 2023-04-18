package Server;

import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
            logger.trace("Received Object Stream: " + receivedMessageObject);
            receivedMessageObject.incrementNumber();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(receivedMessageObject);

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }
}
