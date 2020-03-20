package com.aikam.testtask.search;

public class IntervalSearchCriterion implements SearchCriterion {
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
}
