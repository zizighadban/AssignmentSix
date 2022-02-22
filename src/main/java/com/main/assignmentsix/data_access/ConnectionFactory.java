package com.main.assignmentsix.data_access;

import com.main.assignmentsix.ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final static String URL = ConnectionHelper.CONNECTION_URL;

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
