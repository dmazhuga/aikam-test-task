package com.aikam.testtask.search;

public class LastNameSearchCriterion implements SearchCriterion {
    private static final String functionName = "customer_last_name_search";

    private String lastName;

    public LastNameSearchCriterion(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM " + functionName + "(\'" + lastName + "\')";
    }
}
