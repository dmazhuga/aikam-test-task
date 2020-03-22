package com.aikam.testtask.stat;

import com.aikam.testtask.TestTaskException;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.List;

public class StatOperation {
    private static final String dateFormatErrorMessage = "Неправильный формат даты";

    public String execute(String input) throws TestTaskException, JSONException, SQLException {
        StatJSONParser JSONParser = new StatJSONParser();
        StatDBController DBController = new StatDBController();

        StatInterval statInterval;
        List<StatCustomer> customers;
        int totalExpenses;
        int averageExpenses;

        try {
            statInterval = JSONParser.parse(input);
        } catch (IllegalArgumentException e) {
            throw new TestTaskException(dateFormatErrorMessage);
        }
        totalExpenses = DBController.getTotalExpenses(statInterval);
        averageExpenses = DBController.getAverageExpenses(statInterval);
        customers = DBController.getCustomersList();

        for (StatCustomer customer : customers) {
            customer.setPurchases(DBController.getCustomerExpenses(customer, statInterval));
        }

        return JSONParser.generate(statInterval, customers, totalExpenses, averageExpenses);
    }
}
