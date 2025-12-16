package com.project.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private DataSource dataSource;

    // Return a list of all database names
    public List<String> listDatabases() {
        List<String> databases = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW DATABASES")) {

            while (resultSet.next()) {
                databases.add(resultSet.getString(1));  // First column contains the database name
            }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving databases", e);
        }

        return databases;
    }

    // Return a list of all tables in the given database
    public List<String> listTables(String databaseName) {
        List<String> tables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("USE " + databaseName);
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()) {
                tables.add(resultSet.getString(1));  // First column contains the table name
            }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving tables for database: " + databaseName, e);
        }

        return tables;
    }
}