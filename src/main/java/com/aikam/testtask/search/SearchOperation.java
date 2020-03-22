package com.aikam.testtask.search;

import com.aikam.testtask.JSONUtility;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchOperation {
    private static final String DBErrorMessage = "Произошла ошибка в базе данных";
    private static final String JSONErrorMessage = "Неправильный синтаксис входного фала";
    private static final String UnknownCriterionMessage = "Неверный тип критерия";

    public String execute(String input) {
        SearchJSONParser JSONParser = new SearchJSONParser();
        SearchDBController DBController = new SearchDBController();

        List<SearchCriterion> criteriaList;
        try {
            criteriaList = JSONParser.parse(input);
        } catch (JSONException e) {
            return JSONUtility.generateError(JSONErrorMessage);
        } catch (RuntimeException e) {
            return JSONUtility.generateError(UnknownCriterionMessage);
        }

        List<SuccessfulSearch> searches = new ArrayList<>();
        try {
            for (SearchCriterion criterion : criteriaList) {
                List<Customer> customers = DBController.getCustomersList(criterion);
                searches.add(new SuccessfulSearch(criterion, customers));
            }
        } catch (SQLException e) {
            return JSONUtility.generateError(DBErrorMessage);
        }

        return JSONParser.generate(searches);
    }
}
