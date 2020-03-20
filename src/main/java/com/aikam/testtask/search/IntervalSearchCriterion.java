package com.aikam.testtask.search;

public class IntervalSearchCriterion implements SearchCriterion {
    private static final String functionName = "customers_interval_search";

    private int minExpenses;
    private int maxExpenses;

    public IntervalSearchCriterion(int minExpenses, int maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public int getMinExpenses() {
        return minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM " + functionName + "(" + minExpenses + ", " + maxExpenses + ")";
    }
}
