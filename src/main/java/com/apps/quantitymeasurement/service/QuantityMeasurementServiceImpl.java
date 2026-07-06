package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.WeightUnit;
import com.apps.quantitymeasurement.VolumeUnit;
import com.apps.quantitymeasurement.TemperatureUnit;
import com.apps.quantitymeasurement.IMeasurable;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;


import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {




    @Autowired
    private IQuantityMeasurementRepository repository;
    public QuantityMeasurementServiceImpl() {
    }

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        if (repository == null) {
            throw new IllegalArgumentException(
                    "Repository cannot be null");
        }

        this.repository = repository;
    }

    // =====================================================
    // Compare
    // =====================================================

    @Override
    public boolean compare(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        Quantity<?> q1 =
                createQuantity(quantity1);

        Quantity<?> q2 =
                createQuantity(quantity2);

        boolean result =
                q1.equals(q2);

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "COMPARE",
                        result));



        return result;
    }

    // =====================================================
    // Convert
    // =====================================================

    @Override
    public QuantityDTO convert(
            QuantityDTO quantity,
            QuantityDTO targetUnit) {

        Quantity<?> result =
                performConversion(
                        quantity,
                        targetUnit);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().getUnitName(),
                        targetUnit.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        quantity,
                        targetUnit,
                        "CONVERT",
                        dto));


        return dto;
    }

    // =====================================================
    // Addition
    // =====================================================

    @Override
    public QuantityDTO add(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        Quantity q1 =
                createQuantity(quantity1);

        Quantity q2 =
                createQuantity(quantity2);

        Quantity result =
                q1.add(q2);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().getUnitName(),
                        quantity1.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "ADD",
                        dto));


        return dto;
    }

    @Override
    public QuantityDTO add(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        Quantity q1 =
                createQuantity(quantity1);

        Quantity q2 =
                createQuantity(quantity2);

        Quantity result =
                q1.add(
                        q2,
                        getUnit(targetUnit));

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().getUnitName(),
                        targetUnit.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "ADD",
                        dto));


        return dto;
    }
    // =====================================================
    // Subtraction
    // =====================================================

    @Override
    public QuantityDTO subtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        Quantity q1 =
                createQuantity(quantity1);

        Quantity q2 =
                createQuantity(quantity2);

        Quantity result =
                q1.subtract(q2);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().getUnitName(),
                        quantity1.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "SUBTRACT",
                        dto));


        return dto;
    }


    public QuantityDTO subtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        Quantity q1 =
                createQuantity(quantity1);

        Quantity q2 =
                createQuantity(quantity2);

        Quantity result =
                q1.subtract(
                        q2,
                        getUnit(targetUnit));

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().getUnitName(),
                        targetUnit.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "SUBTRACT",
                        dto));


        return dto;
    }

    // =====================================================
    // Division
    // =====================================================

    @Override
    public double divide(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        Quantity q1 =
                createQuantity(quantity1);

        Quantity q2 =
                createQuantity(quantity2);

        double result =
                q1.divide(q2);

        repository.save(
                new QuantityMeasurementEntity(
                        quantity1,
                        quantity2,
                        "DIVIDE",
                        result));



        return result;
    }
    @Override
    public long getTotalCount() {

        return repository.count();
    }
    // =====================================================
    // Helper Methods
    // =====================================================

    private Quantity<?> createQuantity(
            QuantityDTO dto) {

        switch (
                dto.getMeasurementType().toUpperCase()) {

            case "LENGTH":
                return new Quantity<>(
                        dto.getValue(),
                        LengthUnit.valueOf(
                                dto.getUnit()));

            case "WEIGHT":
                return new Quantity<>(
                        dto.getValue(),
                        WeightUnit.valueOf(
                                dto.getUnit()));

            case "VOLUME":
                return new Quantity<>(
                        dto.getValue(),
                        VolumeUnit.valueOf(
                                dto.getUnit()));

            case "TEMPERATURE":
                return new Quantity<>(
                        dto.getValue(),
                        TemperatureUnit.valueOf(
                                dto.getUnit()));

            default:



                throw new IllegalArgumentException(
                        "Unsupported Measurement Type");
        }
    }

    private IMeasurable getUnit(
            QuantityDTO dto) {

        switch (
                dto.getMeasurementType().toUpperCase()) {

            case "LENGTH":
                return LengthUnit.valueOf(
                        dto.getUnit());

            case "WEIGHT":
                return WeightUnit.valueOf(
                        dto.getUnit());

            case "VOLUME":
                return VolumeUnit.valueOf(
                        dto.getUnit());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(
                        dto.getUnit());

            default:


                throw new IllegalArgumentException(
                        "Unsupported Measurement Type");
        }
    }

    private Quantity<?> performConversion(
            QuantityDTO source,
            QuantityDTO target) {

        switch (
                source.getMeasurementType().toUpperCase()) {

            case "LENGTH":

                return new Quantity<>(
                        source.getValue(),
                        LengthUnit.valueOf(
                                source.getUnit()))
                        .convertTo(
                                LengthUnit.valueOf(
                                        target.getUnit()));

            case "WEIGHT":

                return new Quantity<>(
                        source.getValue(),
                        WeightUnit.valueOf(
                                source.getUnit()))
                        .convertTo(
                                WeightUnit.valueOf(
                                        target.getUnit()));

            case "VOLUME":

                return new Quantity<>(
                        source.getValue(),
                        VolumeUnit.valueOf(
                                source.getUnit()))
                        .convertTo(
                                VolumeUnit.valueOf(
                                        target.getUnit()));

            case "TEMPERATURE":

                return new Quantity<>(
                        source.getValue(),
                        TemperatureUnit.valueOf(
                                source.getUnit()))
                        .convertTo(
                                TemperatureUnit.valueOf(
                                        target.getUnit()));

            default:



                throw new IllegalArgumentException(
                        "Unsupported Measurement Type");
        }
    }
}