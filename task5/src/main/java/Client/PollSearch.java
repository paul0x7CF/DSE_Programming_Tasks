package Client;

import java.util.ArrayList;
import java.util.List;

public class PollSearch {
    private boolean searchComplete;
    private List<String> searchResults;

    public PollSearch() {
        this.searchComplete = false;
        this.searchResults = new ArrayList<>();
    }

    public synchronized boolean isSearchComplete() {
        return searchComplete;
    }

    public synchronized void setSearchComplete(boolean searchComplete) {
        this.searchComplete = searchComplete;
    }
    public synchronized void addSearchResult(String searchResult) {
        this.searchResults.add(searchResult);
    }

    public synchronized List<String> getSearchResults() {
        return searchResults;
    }


}
