package Client;

import Message.MessageObject;

import java.net.*;
import java.io.*;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        logger.info("Client Started");

        // Open your connection to a server, at port 1254
        Socket socketOne = null;
        while (socketOne == null) {
            try {
                socketOne = new Socket("localhost", 1254);

            } catch (IOException e) {
                logger.trace("Connection not found, retrying...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }


        //---------------------------------------------


        OutputStream outputStreamOne = socketOne.getOutputStream();
        InputStream inputStreamOne = socketOne.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStreamOne);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStreamOne);

        dataOutputStream.writeInt(10);
        int receivedData = dataInputStream.readInt();

        System.out.println("Received message from Data Stream: " + receivedData);


        //---------------------------------------------


        InputStream inputStreamTwo = socketOne.getInputStream();
        OutputStream outputStreamTwo = socketOne.getOutputStream();

        //ObjectOutputStream have to be created before ObjectInputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStreamTwo);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStreamTwo);

        objectOutputStream.writeObject(new MessageObject("Client_1", 10));
        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();

        System.out.println("Received message from Object Stream: " + receivedMessageObject);


        //------------------------------------------

        int[] array = new int[3];
        Arrays.fill(array, 2);

        System.out.print("Array before sending: " + Arrays.toString(array));




        socketOne.close();
        // Close the socket and its streams







    }
}