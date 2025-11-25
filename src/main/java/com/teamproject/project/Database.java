package com.teamproject.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // database url
    private static final String URL = "jdbc:sqlite:database.db";
    private Connection connection;


    public Database() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("connection established!");
        } catch (SQLException e) {
            System.err.println("connection failed: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("connection closed!");
            }
        } catch (SQLException e) {
            System.err.println("failed to close connection: " + e.getMessage());
        }
    }
}
