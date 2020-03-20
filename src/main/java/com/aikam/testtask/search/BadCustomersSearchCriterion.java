package com.aikam.testtask.search;

public class BadCustomersSearchCriterion implements SearchCriterion {
    private static final String functionName = "customers_bad_search";

    private int count;

    public BadCustomersSearchCriterion(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM " + functionName + "(" + count + ")";
    }
}
