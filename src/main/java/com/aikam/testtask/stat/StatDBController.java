package com.aikam.testtask.stat;

import com.aikam.testtask.DBUtility;

import java.sql.*;
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

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(getCustomerExpensesQuery)) {

            statement.setDate(1, interval.getStartDate());
            statement.setDate(2, interval.getEndDate());
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

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, interval.getStartDate());
            statement.setDate(2, interval.getEndDate());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            returnInt = resultSet.getInt(columnName);
        }

        return returnInt;
    }
}
