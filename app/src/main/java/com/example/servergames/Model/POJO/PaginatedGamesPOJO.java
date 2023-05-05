package com.example.servergames.Model.POJO;

import java.util.List;

public class PaginatedGamesPOJO {
    private int count;

    private String next;

    private String  previous;

    private List<GamePOJO> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GamePOJO> getResults() {
        return results;
    }

    public void setResults(List<GamePOJO> results) {
        this.results = results;
    }
}
