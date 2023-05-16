package Client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
