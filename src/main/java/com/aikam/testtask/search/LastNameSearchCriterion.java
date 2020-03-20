package com.aikam.testtask.search;

public class LastNameSearchCriterion implements SearchCriterion {
    private String lastName;

    public LastNameSearchCriterion(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
