package com.aikam.testtask.search;

import com.aikam.testtask.stat.DBUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDBController {
    List<Customer> getCustomersList(SearchCriterion criterion) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        ResultSet resultSet = DBUtility.executeQuery(criterion.getQuery());

        while (resultSet.next())
            customers.add(new Customer(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")));

        return customers;
    }
}
