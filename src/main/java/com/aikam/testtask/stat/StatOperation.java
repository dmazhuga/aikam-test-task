package com.aikam.testtask.stat;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StatOperation {
    public String execute(String input) {
        StatJSONParser statJSONParser = new StatJSONParser();
        StatDBController statDBController = new StatDBController();

        StatInterval statInterval;
        List<StatCustomer> customers;
        int totalExpenses;
        int averageExpenses;

        try {
            statInterval = statJSONParser.parse(input);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return statJSONParser.generateError("Неправильный формат даты");
        }
        try {
            totalExpenses = statDBController.getTotalExpenses(statInterval);
            averageExpenses = statDBController.getAverageExpenses(statInterval);
            customers = statDBController.getCustomersList();

            for (StatCustomer customer : customers) {
                customer.setPurchases(statDBController.getCustomerExpenses(customer, statInterval));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return statJSONParser.generateError("Произошла ошибка в базе данных");
        }

        return statJSONParser.generate(statInterval, customers, totalExpenses, averageExpenses);
    }
}
