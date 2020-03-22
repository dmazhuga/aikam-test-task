package com.aikam.testtask;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnector {
    private String url;
    private String user;
    private String password;

    public DBConnector() throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = this.getClass().getResourceAsStream("db.properties")) {

            //вместо исключения может просто выдать null, поэтому кинем исключение сами
            if (inputStream == null)
                throw new IOException("Resource cannot be found");

            properties.load(inputStream);
        }

        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
