package com.aikam.testtask.stat;

import java.sql.*;

public class DBUtility {
    private static final String url = "jdbc:postgresql://localhost/aikamtest";
    private static final String user = "postgres";
    private static final String password = "******";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            return statement.executeQuery();
        }
    }
}
