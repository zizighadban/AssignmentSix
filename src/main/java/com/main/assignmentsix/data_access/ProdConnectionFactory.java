package com.main.assignmentsix.data_access;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Profile("production")
public class ProdConnectionFactory implements DatabaseConnectionFactory {
    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
