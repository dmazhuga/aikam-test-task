package com.aikam.testtask.stat;

import com.aikam.testtask.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StatDBController {
    private static final String getCustomersQuery = "SELECT * FROM get_customers()";
    private static final String getTotalExpensesQuery = "SELECT * FROM total_expenses(?, ?)";
    private static final String getAverageExpensesQuery = "SELECT * FROM average_expenses(?, ?)";
    private static final String getCustomerExpensesQuery = "SELECT * FROM customers_product_stat(?, ?, ?)";

    public List<StatCustomer> getCustomersList() throws SQLException {
        ArrayList<StatCustomer> customers = new ArrayList<>();

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(getCustomersQuery)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                customers.add(new StatCustomer(resultSet.getInt("customer_id"),
                        resultSet.getString("first_name") + " " + resultSet.getString("last_name")));
        }

        return customers;
    }

    public List<StatPurchase> getCustomerExpenses (StatCustomer customer, StatInterval interval) throws SQLException {
        ArrayList<StatPurchase> purchases = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringStartDate = interval.getStartDate().format(dateTimeFormatter);
        String stringEndDate = interval.getEndDate().format(dateTimeFormatter);

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(getCustomerExpensesQuery)) {

            statement.setString(1, stringStartDate);
            statement.setString(2, stringEndDate);
            statement.setInt(3, customer.getId());

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                purchases.add(new StatPurchase(resultSet.getString("product_name"),
                        resultSet.getInt("sum_expense")));
            }
        }

        return purchases;
    }

    public int getTotalExpenses(StatInterval interval) throws SQLException {
        return executeExpensesQuery(getTotalExpensesQuery, interval, "total_expenses");
    }

    public int getAverageExpenses(StatInterval interval) throws SQLException {
        return executeExpensesQuery(getAverageExpensesQuery, interval, "average_expenses");
    }

    private int executeExpensesQuery(String query, StatInterval interval, String columnName) throws SQLException {
        int returnInt;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringStartDate = interval.getStartDate().format(dateTimeFormatter);
        String stringEndDate = interval.getEndDate().format(dateTimeFormatter);

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, stringStartDate);
            statement.setString(2, stringEndDate);

            ResultSet resultSet = statement.executeQuery();
            returnInt = resultSet.getInt("total_expenses");
        }

        return returnInt;
    }
}
