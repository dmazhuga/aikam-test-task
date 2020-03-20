package com.aikam.testtask.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchOperation {
    public String execute(String input) {
        SearchJSONParser JSONParser = new SearchJSONParser();
        SearchDBController DBController = new SearchDBController();

        List<SearchCriterion> criteriaList = JSONParser.parse(input);

        List<SuccessfulSearch> searches = new ArrayList<>();
        try {
            for (SearchCriterion criterion : criteriaList) {
                List<Customer> customers = DBController.getCustomersList(criterion);
                searches.add(new SuccessfulSearch(criterion, customers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return JSONParser.generateError("Произошла ошибка в базе данных");
        }

        return JSONParser.generate(searches);
    }
}
