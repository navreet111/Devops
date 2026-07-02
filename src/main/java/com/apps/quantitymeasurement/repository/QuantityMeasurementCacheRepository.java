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

    public static synchronized
    QuantityMeasurementCacheRepository
    getInstance() {

        if (instance == null) {

            instance =
                    new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    // =====================================================
    // Save
    // =====================================================

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        cache.add(entity);
    }

    // =====================================================
    // Find All
    // =====================================================

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        return new ArrayList<>(cache);
    }

    // =====================================================
    // Find By Operation
    // =====================================================

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByOperation(
            String operation) {

        List<QuantityMeasurementEntity> result =
                new ArrayList<>();

        for (QuantityMeasurementEntity entity
                : cache) {

            if (entity.getOperation()
                    .equalsIgnoreCase(operation)) {

                result.add(entity);
            }
        }

        return result;
    }

    // =====================================================
    // Find By Measurement Type
    // =====================================================

    @Override
    public List<QuantityMeasurementEntity>
    getMeasurementsByType(
            String measurementType) {

        List<QuantityMeasurementEntity> result =
                new ArrayList<>();

        for (QuantityMeasurementEntity entity
                : cache) {

            if (entity.getThisQuantity()
                    .getMeasurementType()
                    .equalsIgnoreCase(
                            measurementType)) {

                result.add(entity);
            }
        }

        return result;
    }

    // =====================================================
    // Total Count
    // =====================================================

    @Override
    public int getTotalCount() {

        return cache.size();
    }

    // =====================================================
    // Delete All
    // =====================================================

    @Override
    public void deleteAll() {

        cache.clear();
    }


    @Override
    public void releaseResources() {

        cache.clear();
    }
}