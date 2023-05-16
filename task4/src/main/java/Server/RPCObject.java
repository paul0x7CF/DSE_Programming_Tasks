package Server;

import Shared.IRPCObject;

public class RPCObject implements IRPCObject {

    @Override
    public String hello(String name) {
        return ("DSE, " + name);
    }

    @Override
    public String goodbye(String name) {
        return ("I'm afraid this is a farewell, " + name);
    }
}
