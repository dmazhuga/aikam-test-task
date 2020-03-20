package com.aikam.testtask.search;

import java.util.List;

public class SuccessfulSearch {
    private SearchCriterion criterion;
    private List<Customer> result;

    public SuccessfulSearch(SearchCriterion criterion, List<Customer> result) {
        this.criterion = criterion;
        this.result = result;
    }

    public SearchCriterion getCriterion() {
        return criterion;
    }

    public List<Customer> getResult() {
        return result;
    }
}
