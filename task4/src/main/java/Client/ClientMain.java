package Client;

import Client.Exceptions.InvalidMethodeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);
    public static void main(String[] args) {


        RPCProxy proxy = new RPCProxy();
        String helloResult = null;
        String goodbyeResult = null;

        try {
            helloResult = proxy.hello("Hello");
            goodbyeResult = proxy.goodbye("DSE");
        } catch (InvalidMethodeException e) {
            logger.error("Invalid Methode", e);
        }

        System.out.println(helloResult);
        System.out.println(goodbyeResult);

        try {
            String testResult = proxy.test("Test");
            System.out.println(testResult);
        } catch (InvalidMethodeException e) {
            logger.error("Invalid Methode", e);
        }





    }
}