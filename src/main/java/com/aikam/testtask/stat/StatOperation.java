package com.aikam.testtask.stat;

import com.aikam.testtask.JSONUtility;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.List;

public class StatOperation {
    private static final String DBErrorMessage = "Произошла ошибка в базе данных";
    private static final String dateFormatErrorMessage = "Неправильный формат даты";
    private static final String JSONErrorMessage = "Неправильный синтаксис входного фала";

    public String execute(String input) {
        StatJSONParser JSONParser = new StatJSONParser();
        StatDBController DBController = new StatDBController();

        StatInterval statInterval;
        List<StatCustomer> customers;
        int totalExpenses;
        int averageExpenses;

        try {
            statInterval = JSONParser.parse(input);
        } catch (IllegalArgumentException e) {
            return JSONUtility.generateError(dateFormatErrorMessage);
        } catch (JSONException e) {
            return JSONUtility.generateError(JSONErrorMessage);
        }
        try {
            totalExpenses = DBController.getTotalExpenses(statInterval);
            averageExpenses = DBController.getAverageExpenses(statInterval);
            customers = DBController.getCustomersList();

            for (StatCustomer customer : customers) {
                customer.setPurchases(DBController.getCustomerExpenses(customer, statInterval));
            }
        } catch (SQLException e) {
            return JSONUtility.generateError(DBErrorMessage);
        }

        return JSONParser.generate(statInterval, customers, totalExpenses, averageExpenses);
    }
}
