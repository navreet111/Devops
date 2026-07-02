package com.apps.quantitymeasurement.factory;

import com.apps.quantitymeasurement.util.ApplicationConfig;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static IQuantityMeasurementRepository
    getRepository() {

        String repositoryType =
                ApplicationConfig
                        .getInstance()
                        .getProperty(
                                "repository.type",
                                "DATABASE");

        if ("CACHE".equalsIgnoreCase(
                repositoryType)) {

            return QuantityMeasurementCacheRepository
                    .getInstance();
        }

        return QuantityMeasurementDatabaseRepository
                .getInstance();
    }
}