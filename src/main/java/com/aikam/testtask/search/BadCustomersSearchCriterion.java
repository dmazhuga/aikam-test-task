package com.aikam.testtask.search;

public class BadCustomersSearchCriterion implements SearchCriterion {
    private int count;

    public BadCustomersSearchCriterion(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
