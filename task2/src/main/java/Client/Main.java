package Client;

import Message.MessageObject;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

        OutputStream outputStream = socketOne.getOutputStream();
        InputStream inputStream = socketOne.getInputStream();

        //---------------------------------------------


        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        dataOutputStream.writeInt(10);
        int receivedData = dataInputStream.readInt();

        System.out.println("Received message from Data Stream: " + receivedData);


        //---------------------------------------------


        //ObjectOutputStream have to be created before ObjectInputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        objectOutputStream.writeObject(new MessageObject("Client_1", 10));
        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();

        System.out.println("Received message from Object Stream: " + receivedMessageObject);


        //------------------------------------------

        final int DATA_BLOB = 128;
        ArrayList<Integer> processingTime = new ArrayList<>();
        MessageObject messageObject = new MessageObject("Client_2", 0, DATA_BLOB);
        for(int i = 0; i < 1000; i++) {
            if(i % 50 == 0) System.out.print(".");
            messageObject.setDate(new Date(System.currentTimeMillis()));
            objectOutputStream.writeObject(messageObject);
            messageObject = (MessageObject) objectInputStream.readObject();
            Date currentDate = new Date(System.currentTimeMillis());
            processingTime.add((int) (currentDate.getTime() - messageObject.getDate().getTime()));

            logger.trace("Time difference: " + (currentDate.getTime() - messageObject.getDate().getTime()));
            logger.trace("Received message from Object Stream: " + messageObject);
        }

        int entries = processingTime.size();
        int sum = 0;
        for (Integer eachProcessingTime : processingTime) {
            sum += eachProcessingTime;
        }
        System.out.print("\n");
        System.out.println("Average processing time: " + sum/entries + "ms");



        socketOne.close();
        // Close the socket and its streams







    }
}