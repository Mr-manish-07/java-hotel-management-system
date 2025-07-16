package org.manish07.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Manish@7200";

    public static Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Class.forName("org.postgresql.Driver");

            return connection;

        } catch (ClassNotFoundException  | SQLException f) {
            throw new RuntimeException();
        }

    }
}
