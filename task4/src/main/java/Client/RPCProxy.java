package Client;

import Client.Exceptions.InvalidMethodeException;
import Shared.IRPCObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RPCProxy implements IRPCObject {

    private static final Logger logger = LogManager.getLogger(RPCProxy.class);


    @Override
    public String hello(String name) throws InvalidMethodeException {
        logger.debug("asking for hello methode with parameter {}", name);
        return ClientRequestor.request("hello", name);
    }

    @Override
    public String goodbye(String name) throws InvalidMethodeException {
        logger.debug("asking for goodbye methode with parameter {}", name);
        return ClientRequestor.request("goodbye", name);
    }

    public String test(String name) throws InvalidMethodeException {
        logger.debug("asking for test methode with parameter {}", name);
        return ClientRequestor.request("test", name);
    }
}
