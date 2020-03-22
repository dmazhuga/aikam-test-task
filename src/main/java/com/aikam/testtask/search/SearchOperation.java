package com.aikam.testtask.search;

import com.aikam.testtask.DBConnector;
import com.aikam.testtask.TestTaskException;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchOperation {
    public String execute(String input, DBConnector dbConnector) throws TestTaskException, JSONException, SQLException {
        SearchJSONParser JSONParser = new SearchJSONParser();
        SearchDBController DBController = new SearchDBController(dbConnector);

        List<SearchCriterion> criteriaList = JSONParser.parse(input);
        List<SuccessfulSearch> searches = new ArrayList<>();

        for (SearchCriterion criterion : criteriaList) {
            List<Customer> customers = DBController.getCustomersList(criterion);
            searches.add(new SuccessfulSearch(criterion, customers));
        }

        return JSONParser.generate(searches);
    }
}
