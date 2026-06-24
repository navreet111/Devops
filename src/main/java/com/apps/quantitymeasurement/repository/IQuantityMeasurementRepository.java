package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;

import java.util.List;

    public interface IQuantityMeasurementRepository {

    void save(
            QuantityMeasurementEntity entity
    );

    List<QuantityMeasurementEntity> findAll();
}

