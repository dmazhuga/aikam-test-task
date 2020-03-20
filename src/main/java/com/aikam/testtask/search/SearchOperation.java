package com.aikam.testtask.search;

import java.util.List;

public class SearchOperation {
    public String execute(String input) {
        SearchJSONParser JSONParser = new SearchJSONParser();
        SearchDBController DBController = new SearchDBController();

        List<SearchCriterion> criteriaList = JSONParser.parse(input);

        for (SearchCriterion criterion : criteriaList) {

            List<Customer> customers = DBController.getCustomersList(criterion);
        }

        return "";
    }
}
