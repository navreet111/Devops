package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private static final Logger logger =
            Logger.getLogger(
                    QuantityMeasurementDatabaseRepository.class.getName());

    private static QuantityMeasurementDatabaseRepository instance;



    // =====================================================
    // SQL Queries
    // =====================================================

    private static final String INSERT_SQL =
            """
            INSERT INTO quantity_measurement_history
            (
                first_value,
                first_unit,
                first_measurement_type,
                second_value,
                second_unit,
                second_measurement_type,
                operation,
                result,
                error_message,
                is_error
            )
            VALUES
            (
                ?,?,?,?,?,?,?,?,?,?
            )
            """;

    private static final String FIND_ALL_SQL =
            """
            SELECT *
            FROM quantity_measurement_history
            ORDER BY id
            """;

    private static final String FIND_BY_OPERATION_SQL =
            """
            SELECT *
            FROM quantity_measurement_history
            WHERE operation=?
            ORDER BY id
            """;

    private static final String FIND_BY_TYPE_SQL =
            """
            SELECT *
            FROM quantity_measurement_history
            WHERE first_measurement_type=?
            ORDER BY id
            """;

    private static final String COUNT_SQL =
            """
            SELECT COUNT(*)
            FROM quantity_measurement_history
            """;

    private static final String DELETE_ALL_SQL =
            """
            DELETE FROM quantity_measurement_history
            """;

    // =====================================================
    // Constructor
    // =====================================================

    private QuantityMeasurementDatabaseRepository() {

        createTableIfNotExists();
    }
    public static synchronized
    QuantityMeasurementDatabaseRepository
    getInstance() {

        if (instance == null) {

            instance =
                    new QuantityMeasurementDatabaseRepository();
        }

        return instance;
    }


    private void createTableIfNotExists() {

        String query =
                """
                CREATE TABLE IF NOT EXISTS quantity_measurement_history
                (

                    id INT AUTO_INCREMENT PRIMARY KEY,

                    first_value DOUBLE,

                    first_unit VARCHAR(30),

                    first_measurement_type VARCHAR(30),

                    second_value DOUBLE,

                    second_unit VARCHAR(30),

                    second_measurement_type VARCHAR(30),

                    operation VARCHAR(30),

                    result VARCHAR(255),

                    error_message VARCHAR(255),

                    is_error BOOLEAN

                )
                """;
        try (

                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement()

        ){

            statement.execute(query);

            logger.info(
                    "Database Initialized Successfully.");

        }

        catch (SQLException e) {

            throw new DatabaseException(

                    "Unable to Initialize Database",

                    e);
        }
    }



    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(INSERT_SQL)

        ) {

            statement.setDouble(
                    1,
                    entity.getThisQuantity().getValue());

            statement.setString(
                    2,
                    entity.getThisQuantity().getUnit());

            statement.setString(
                    3,
                    entity.getThisQuantity().getMeasurementType());

            if (entity.getThatQuantity() != null) {

                statement.setDouble(
                        4,
                        entity.getThatQuantity().getValue());

                statement.setString(
                        5,
                        entity.getThatQuantity().getUnit());

                statement.setString(
                        6,
                        entity.getThatQuantity().getMeasurementType());

            }

            else {

                statement.setNull(
                        4,
                        Types.DOUBLE);

                statement.setNull(
                        5,
                        Types.VARCHAR);

                statement.setNull(
                        6,
                        Types.VARCHAR);
            }

            statement.setString(
                    7,
                    entity.getOperation());

            if (entity.getResult() != null) {

                statement.setString(
                        8,
                        entity.getResult().toString());

            }

            else {

                statement.setNull(
                        8,
                        Types.VARCHAR);
            }

            statement.setString(
                    9,
                    entity.getErrorMessage());

            statement.setBoolean(
                    10,
                    entity.isError());

            statement.executeUpdate();

            logger.info(
                    "Measurement Saved Successfully.");

        }

        catch (SQLException e) {

            logger.severe(
                    "Database Save Failed.");

            throw new DatabaseException(

                    "Unable to Save Measurement",

                    e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> history =
                new ArrayList<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(FIND_ALL_SQL)

        ){

            while (resultSet.next()) {

                history.add(
                        mapRowToEntity(
                                resultSet));
            }

            logger.info(
                    history.size()
                            + " Measurements Retrieved.");

        }

        catch (SQLException e) {

            logger.severe(
                    "Unable to Fetch History.");

            throw new DatabaseException(
                    "Unable to Fetch History",
                    e);
        }

        return history;
    }

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByOperation(
            String operation) {

        List<QuantityMeasurementEntity> history =
                new ArrayList<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                FIND_BY_OPERATION_SQL)

        ) {

            statement.setString(
                    1,
                    operation);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                history.add(
                        mapRowToEntity(
                                resultSet));
            }

        }

        catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to Fetch Measurements By Operation",
                    e);
        }

        return history;
    }
    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByType(
            String measurementType) {

        List<QuantityMeasurementEntity> history =
                new ArrayList<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                FIND_BY_TYPE_SQL)

        ) {

            statement.setString(
                    1,
                    measurementType);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                history.add(
                        mapRowToEntity(
                                resultSet));
            }

        }

        catch (SQLException e) {

            throw new DatabaseException(
                    "Unable to Fetch Measurements By Type",
                    e);
        }

        return history;
    }
    private QuantityMeasurementEntity
    mapRowToEntity(
            ResultSet resultSet)
            throws SQLException {

        QuantityDTO firstQuantity =
                new QuantityDTO(

                        resultSet.getDouble(
                                "first_value"),

                        resultSet.getString(
                                "first_unit"),

                        resultSet.getString(
                                "first_measurement_type")
                );

        QuantityDTO secondQuantity = null;

        if (resultSet.getString(
                "second_unit") != null) {

            secondQuantity =
                    new QuantityDTO(

                            resultSet.getDouble(
                                    "second_value"),

                            resultSet.getString(
                                    "second_unit"),

                            resultSet.getString(
                                    "second_measurement_type")
                    );
        }

        QuantityMeasurementEntity entity;

        if (resultSet.getBoolean(
                "is_error")) {

            entity =
                    new QuantityMeasurementEntity(

                            firstQuantity,

                            secondQuantity,

                            resultSet.getString(
                                    "operation"),

                            resultSet.getString(
                                    "error_message")
                    );

        }

        else {

            String result =
                    resultSet.getString("result");

            Object finalResult = result;

            if ("true".equalsIgnoreCase(result)
                    || "false".equalsIgnoreCase(result)) {

                finalResult =
                        Boolean.valueOf(result);
            }

            entity =
                    new QuantityMeasurementEntity(

                            firstQuantity,

                            secondQuantity,

                            resultSet.getString("operation"),

                            finalResult);
        }

        return entity;
    }

    @Override
    public int getTotalCount() {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(COUNT_SQL)

        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

            return 0;

        }

        catch (SQLException e) {

            logger.severe(
                    "Unable to Count Measurements.");

            throw new DatabaseException(

                    "Unable to Count Measurements",

                    e);
        }
    }


    @Override
    public void deleteAll() {

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement()

        ) {

            int rows =
                    statement.executeUpdate(
                            DELETE_ALL_SQL);

            logger.info(
                    rows
                            + " Measurements Deleted.");

        }

        catch (SQLException e) {

            logger.severe(
                    "Unable to Delete Measurements.");

            throw new DatabaseException(

                    "Unable to Delete Measurements",

                    e);
        }
    }


    @Override
    public void releaseResources() {



        logger.info(
                "Database Resources Released.");
    }

}