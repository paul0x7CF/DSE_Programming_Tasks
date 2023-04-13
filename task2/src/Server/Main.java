package Server;
import Message.MessageObject;

import java.net.*;
import java.io.*;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        logger.info("Server Started");

        // Register service on port 1254
        ServerSocket serverSocket = new ServerSocket(1254);
        Socket socketOne = serverSocket.accept();
        // Wait and accept a connection
        // Get a communication stream
        // associated with the socket
        OutputStream outputStream = socketOne.getOutputStream();
        InputStream inputStream = socketOne.getInputStream();

        //---------------------------------------------

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        int receivedData = dataInputStream.readInt();
        logger.info("Received data: " + receivedData);
        receivedData++;
        dataOutputStream.writeInt(receivedData);



        //------------------------------------------


        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
        receivedMessageObject.incrementNumber();
        objectOutputStream.writeObject(receivedMessageObject);
        objectInputStream.close();
        objectOutputStream.close();



        socketOne.close();


    }

}
