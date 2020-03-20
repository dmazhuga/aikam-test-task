package com.aikam.testtask.search;

import com.aikam.testtask.stat.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDBController {
    List<Customer> getCustomersList(SearchCriterion criterion) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection connection = DBUtility.connect();
             PreparedStatement statement = connection.prepareStatement(criterion.getQuery())) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                customers.add(new Customer(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
        }

        return customers;
    }
}
