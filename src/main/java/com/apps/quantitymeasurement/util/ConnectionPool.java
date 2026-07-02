package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger logger =
            Logger.getLogger(
                    ConnectionPool.class.getName());

    private static Connection connection;

    private ConnectionPool() {
    }

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName(
                        ApplicationConfig
                                .getInstance()
                                .getProperty("db.driver"));

                connection =
                        DriverManager.getConnection(

                                ApplicationConfig
                                        .getInstance()
                                        .getProperty("db.url"),

                                ApplicationConfig
                                        .getInstance()
                                        .getProperty("db.username"),

                                ApplicationConfig
                                        .getInstance()
                                        .getProperty("db.password")
                        );

                logger.info(
                        "Database Connection Created.");
            }

            return connection;

        }

        catch (ClassNotFoundException | SQLException e) {

            logger.severe(
                    "Unable to Connect Database.");

            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {

        try {

            if (connection != null &&
                    !connection.isClosed()) {

                connection.close();

                connection = null;

                logger.info(
                        "Database Connection Closed.");
            }

        }

        catch (SQLException e) {

            logger.severe(
                    "Unable to Close Database.");
        }
    }

    public static boolean validateConnection(
            Connection connection) {

        try (

                Statement statement =
                        connection.createStatement()

        ) {

            statement.execute("SELECT 1");

            return true;

        }

        catch (SQLException e) {

            return false;
        }
    }

    public static void main(String[] args) {

        Connection connection =
                getConnection();

        logger.info(
                "Connection Valid : "
                        + validateConnection(connection));

        closeConnection();
    }
}