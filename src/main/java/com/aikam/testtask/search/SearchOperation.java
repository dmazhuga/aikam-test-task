package com.aikam.testtask.search;

import java.util.List;

public class SearchOperation {
    public String execute(String input) {
        SearchJSONParser JSONParser = new SearchJSONParser();
        List<SearchCriterion> criteriaList = JSONParser.parse(input);


        return "";
    }
}
