package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
