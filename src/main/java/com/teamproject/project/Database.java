package com.teamproject.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // database url
    private static final String URL = "jdbc:sqlite:database.db";
    private Connection connection;

    private static Database instance = new Database();


    private Database() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("connection established!");
        } catch (SQLException e) {
            System.err.println("connection failed: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                try {
                    connection = DriverManager.getConnection(URL);
                    System.out.println("connection established!");
                    return connection;
                } catch (SQLException e) {
                    System.err.println("connection failed: " + e.getMessage());
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("failed to check connection: " + e.getMessage());
            return null;
        }
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

    public static Database getInstance() {
        return instance;
    }
}
