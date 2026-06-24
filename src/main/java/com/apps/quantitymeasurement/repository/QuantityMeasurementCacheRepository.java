package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache;

    private QuantityMeasurementCacheRepository() {

        cache = new ArrayList<>();
    }

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {

            instance =
                    new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        if (entity == null) {

            throw new IllegalArgumentException(
                    "Entity cannot be null");
        }

        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        return new ArrayList<>(cache);
    }
}