package Client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PollLogStorage {

    private static final Logger logger = LogManager.getLogger(PollLogStorage.class);
    private boolean incLogStorageComplete;
    private int incrementAmount;


    public PollLogStorage() {
        this.incLogStorageComplete = false;
        this.incrementAmount = 0;
    }

    public int getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(int incrementAmount) {
        logger.debug("Received successfully increased LogStorage on Server with amount {}", incrementAmount);
        this.incrementAmount = incrementAmount;
    }


    public boolean isIncreaseComplete() {
        return incLogStorageComplete;
    }

    public void setLogStorageComplete(boolean incLogStorageComplete) {
        this.incLogStorageComplete = incLogStorageComplete;
    }
}
