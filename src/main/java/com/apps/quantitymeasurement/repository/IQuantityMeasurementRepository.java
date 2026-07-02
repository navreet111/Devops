package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(
            QuantityMeasurementEntity entity
    );

    List<QuantityMeasurementEntity> findAll();

    List<QuantityMeasurementEntity> getMeasurementsByOperation(
            String operation
    );

    List<QuantityMeasurementEntity> getMeasurementsByType(
            String measurementType
    );

    int getTotalCount();

    void deleteAll();

    void releaseResources();
}