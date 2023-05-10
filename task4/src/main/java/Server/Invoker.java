package Server;

public class Invoker {

    private static RPCObject remoteObject = new RPCObject();

    public static byte[] invoke(byte[] data) {
        Object[] args = ServerMarshaller.unmarshall(data);
        String methodName = (String) args[0];
        String methodeParam = (String) args[1];

        String methodeResult = switch (methodName) {
            case "hello" -> remoteObject.hello(methodeParam);
            case "goodbye" -> remoteObject.goodbye(methodeParam);
            default -> "Error";
        };
        if (methodeResult.equals("Error")) return ServerMarshaller.marshall("Error", "");
        byte[] result = ServerMarshaller.marshall(methodName, methodeResult);
        return result;
    }
}
