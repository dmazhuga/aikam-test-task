package com.aikam.testtask;

import java.sql.*;

public class DBUtility {
    private static final String url = "jdbc:postgresql://localhost/aikamtest";
    private static final String user = "postgres";
    private static final String password = "******";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
