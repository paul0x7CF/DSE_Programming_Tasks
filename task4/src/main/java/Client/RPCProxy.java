package Client;

import Client.Exceptions.InvalidMethodeException;
import Shared.IRPCObject;

public class RPCProxy implements IRPCObject {


    @Override
    public String hello(String name) throws InvalidMethodeException {
        return ClientRequestor.request("hello", name);
    }

    @Override
    public String goodbye(String name) {
        return null;
    }
}
