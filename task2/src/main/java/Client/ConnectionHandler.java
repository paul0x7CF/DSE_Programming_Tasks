package Client;

import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(ConnectionHandler.class);
    private Socket socket;
    private String name;
    private int number;

    public ConnectionHandler(Socket socket, String name, int number) {
        this.socket = socket;
        this.name = name;
        this.number = number;
    }

    @Override
    public void run() {

        logger.trace("Thread Id: {}", Thread.currentThread().getId());

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(new MessageObject(name, number, 128));
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
            logger.trace(receivedMessageObject);

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
