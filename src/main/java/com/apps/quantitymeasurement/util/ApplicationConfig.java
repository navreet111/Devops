package com.apps.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ApplicationConfig {

    private static final Logger logger =
            Logger.getLogger(
                    ApplicationConfig.class.getName());

    private static ApplicationConfig instance;

    private Properties properties;

    private Environment environment;

    // Environment Types

    public enum Environment {

        DEVELOPMENT,

        TESTING,

        PRODUCTION
    }

    // Configuration Keys

    public enum ConfigKey {

        REPOSITORY_TYPE("repository.type"),

        DB_DRIVER_CLASS("db.driver"),

        DB_URL("db.url"),

        DB_USERNAME("db.username"),

        DB_PASSWORD("db.password"),

        DB_POOL_SIZE("db.pool.size");

        private final String key;

        ConfigKey(String key) {

            this.key = key;
        }

        public String getKey() {

            return key;
        }
    }

    private ApplicationConfig() {

        loadConfiguration();
    }

    public static synchronized ApplicationConfig getInstance() {

        if (instance == null) {

            instance = new ApplicationConfig();
        }

        return instance;
    }

    private void loadConfiguration() {

        properties = new Properties();

        try {

            InputStream input =
                    ApplicationConfig.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "application.properties");

            if (input != null) {

                properties.load(input);

                logger.info(
                        "Configuration Loaded.");
            }

            else {

                logger.warning(
                        "Configuration file not found. Using defaults.");

                loadDefaults();
            }

            String env =
                    properties.getProperty(
                            "app.env",
                            "development");

            environment =
                    Environment.valueOf(
                            env.toUpperCase());

        }

        catch (Exception e) {

            logger.severe(
                    e.getMessage());

            loadDefaults();
        }
    }

    private void loadDefaults() {

        properties.setProperty(
                ConfigKey.REPOSITORY_TYPE.getKey(),
                "DATABASE");

        properties.setProperty(
                ConfigKey.DB_DRIVER_CLASS.getKey(),
                "org.postgresql.Driver");

        properties.setProperty(
                ConfigKey.DB_URL.getKey(),
                "jdbc:postgresql://localhost:5432/quantitymeasurement");

        properties.setProperty(
                ConfigKey.DB_USERNAME.getKey(),
                "postgres");

        properties.setProperty(
                ConfigKey.DB_PASSWORD.getKey(),
                "postgres");

        properties.setProperty(
                ConfigKey.DB_POOL_SIZE.getKey(),
                "5");

        environment =
                Environment.DEVELOPMENT;
    }

    public String getProperty(
            String key) {

        return properties.getProperty(
                key);
    }

    public String getProperty(
            String key,
            String defaultValue) {

        return properties.getProperty(
                key,
                defaultValue);
    }

    public int getIntProperty(
            String key,
            int defaultValue) {

        try {

            return Integer.parseInt(
                    properties.getProperty(
                            key,
                            String.valueOf(defaultValue)));

        }

        catch (Exception e) {

            return defaultValue;
        }
    }

    public String getEnvironment() {

        return environment.name();
    }

    public boolean isConfigKey(
            String key) {

        for (ConfigKey configKey
                : ConfigKey.values()) {

            if (configKey.getKey()
                    .equals(key)) {

                return true;
            }
        }

        return false;
    }

    public void printAllProperties() {

        logger.info(
                "========= CONFIGURATION =========");

        properties.forEach(

                (k, v) ->

                        logger.info(
                                k + " = " + v));

        logger.info(
                "Environment = "
                        + environment);

        logger.info(
                "=================================");
    }

    public static void main(
            String[] args) {

        ApplicationConfig config =
                ApplicationConfig.getInstance();

        config.printAllProperties();
    }
}