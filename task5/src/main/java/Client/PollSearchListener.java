package Client;

import Server.Invoker;
import Shared.IMarshall;
import Shared.RequestMessage;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PollSearchListener implements Runnable{

    private static final Logger logger = LogManager.getLogger(PollSearchListener.class);

    private PollSearch pollSearch;
    private RequestMessage requestMessage;

    public PollSearchListener(PollSearch pollSearch, RequestMessage requestMessage) {
        this.pollSearch = pollSearch;
        this.requestMessage = requestMessage;
    }
    @Override
    public void run() {

        try {
            byte[] response = ClientRequestHandlerOut.sendMessageViaTCPTargetAck(requestMessage.marshall());
            ResponseMessage responseMessage = IMarshall.unmarshall(response);
            String[] searchResults = (String[]) responseMessage.getResponseData();
            for(String eachResult : searchResults){
                this.pollSearch.addSearchResult(eachResult);
            }
            this.pollSearch.setSearchComplete(true);
            logger.debug("Received search results added to pollSearch object");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
