package com.aikam.testtask.stat;

import com.aikam.testtask.JSONUtility;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StatOperation {
    public String execute(String input) {
        StatJSONParser JSONParser = new StatJSONParser();
        StatDBController DBController = new StatDBController();

        StatInterval statInterval;
        List<StatCustomer> customers;
        int totalExpenses;
        int averageExpenses;

        try {
            statInterval = JSONParser.parse(input);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return JSONUtility.generateError("Неправильный формат даты");
        }
        try {
            totalExpenses = DBController.getTotalExpenses(statInterval);
            averageExpenses = DBController.getAverageExpenses(statInterval);
            customers = DBController.getCustomersList();

            for (StatCustomer customer : customers) {
                customer.setPurchases(DBController.getCustomerExpenses(customer, statInterval));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return JSONUtility.generateError("Произошла ошибка в базе данных");
        }

        return JSONParser.generate(statInterval, customers, totalExpenses, averageExpenses);
    }
}
