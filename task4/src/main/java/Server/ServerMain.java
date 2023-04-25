package Server;

public class ServerMain {

    public static void main(String[] args) {

        ServerRequestHandler serverRequestHandler = new ServerRequestHandler();
        serverRequestHandler.startHandler();

    }
}
