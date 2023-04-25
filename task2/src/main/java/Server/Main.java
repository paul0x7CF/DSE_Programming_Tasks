package Server;

import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        logger.info("Server Started");


        /*
        // Open a server socket on port 1254
        // Bind the server socket to a an port
        ServerSocket serverSocket = new ServerSocket(1254);

        // Wait, listen and accept for incoming client connections
        Socket socketOne = serverSocket.accept();


        // Write/Read data to/from the client
        buildDataStreamConnection(socketOne);

        buildObjectStreamConnection(socketOne);
        socketOne.close();

        Socket socketTwo = serverSocket.accept();
        sendDataBlob(socketTwo);

        //close the socket
        socketOne.close();

        threadingConnection(serverSocket);
        */



        logger.info("task4");

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(7000);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                int receivedData = ByteBuffer.wrap(request.getData()).getInt();
                logger.debug("Received Data : " + receivedData);
                receivedData++;
                byte[] replyBuffer = ByteBuffer.allocate(Integer.BYTES).putInt(receivedData).array();
                DatagramPacket reply = new DatagramPacket(
                        replyBuffer,
                        replyBuffer.length,
                        request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();

        }

    }

    private static void buildDataStreamConnection(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            int receivedData = dataInputStream.readInt();
            logger.debug("Received Data Stream: " + receivedData);
            receivedData++;
            dataOutputStream.writeInt(receivedData);

        } catch (IOException e) {
            logger.error("Error in Data Stream Connection {}", e.getMessage());
        }

    }

    private static void buildObjectStreamConnection(Socket socket) {

        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
            logger.debug("Received Object Stream: " + receivedMessageObject);
            receivedMessageObject.incrementNumber();
            objectOutputStream.writeObject(receivedMessageObject);

            inputStream.close();
            outputStream.close();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException e) {
            logger.error("Error in Object Stream Connection {}", e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("Error in Object Stream Connection {}", e.getMessage());
        }

    }

    private static void sendDataBlob(Socket socket) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            for (int i = 0; i < 6000; i++) {
                MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
                logger.trace("Received Object Stream: " + receivedMessageObject);
                receivedMessageObject.incrementNumber();
                objectOutputStream.writeObject(receivedMessageObject);
            }
        } catch (IOException e) {
            logger.error("Error in Object Stream Connection {}", e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("Error in Object Stream Connection {}", e.getMessage());
        }
    }

    private static void threadingConnection(ServerSocket serverSocket) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 1000; i++) {
                Socket socketThreading = null;
                socketThreading = serverSocket.accept();
                Runnable worker = new ClientHandler(socketThreading);
                executor.execute(worker);
            }
            executor.shutdown();
        } catch (IOException e) {
            logger.error("Error in threading Connection {}", e.getMessage());
        }

    }


}
