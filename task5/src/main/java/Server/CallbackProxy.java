package Server;

import Client.CallbackIncLogStorage;
import KickStartDev.EKnownMethods;
import KickStartDev.ResponseMessage;

public class CallbackProxy implements CallbackIncLogStorage {
    @Override
    public void success() {
        new ResponseMessage("Callback", EKnownMethods.SUCCESS);
    }

    @Override
    public void failure() {
        new ResponseMessage("Callback", EKnownMethods.FAILURE);
    }
}
