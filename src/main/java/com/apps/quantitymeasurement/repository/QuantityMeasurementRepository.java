package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<
        QuantityMeasurementEntity,
        Integer> {

    List<QuantityMeasurementEntity>
    findByOperation(
            String operation);

    List<QuantityMeasurementEntity>
    findByThisQuantityMeasurementType(
            String measurementType);
}