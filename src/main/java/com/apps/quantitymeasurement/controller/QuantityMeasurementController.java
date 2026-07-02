package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import java.util.logging.Logger;

public class QuantityMeasurementController {

    private static final Logger logger =
            Logger.getLogger(
                    QuantityMeasurementController.class.getName());

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        if (service == null) {

            throw new IllegalArgumentException(
                    "Service cannot be null");
        }

        this.service = service;
    }

    // =====================================================
    // Compare
    // =====================================================

    public boolean performCompare(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        logger.info(
                "Performing Comparison");

        return service.compare(
                quantity1,
                quantity2);
    }

    // =====================================================
    // Convert
    // =====================================================

    public QuantityDTO performConvert(
            QuantityDTO quantity,
            QuantityDTO targetUnit) {

        logger.info(
                "Performing Conversion");

        return service.convert(
                quantity,
                targetUnit);
    }

    // =====================================================
    // Add
    // =====================================================

    public QuantityDTO performAdd(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        logger.info(
                "Performing Addition");

        return service.add(
                quantity1,
                quantity2);
    }

    public QuantityDTO performAdd(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        logger.info(
                "Performing Addition");

        return service.add(
                quantity1,
                quantity2,
                targetUnit);
    }

    // =====================================================
    // Subtract
    // =====================================================

    public QuantityDTO performSubtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        logger.info(
                "Performing Subtraction");

        return service.subtract(
                quantity1,
                quantity2);
    }

    public QuantityDTO performSubtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        logger.info(
                "Performing Subtraction");

        return service.subtract(
                quantity1,
                quantity2,
                targetUnit);
    }

    // =====================================================
    // Divide
    // =====================================================

    public double performDivide(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        logger.info(
                "Performing Division");

        return service.divide(
                quantity1,
                quantity2);
    }

    // =====================================================
    // Display
    // =====================================================

    public void displayResult(
            Object result) {

        logger.info(
                String.valueOf(result));
    }
}