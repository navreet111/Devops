package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        if (service == null) {
            throw new IllegalArgumentException(
                    "Service cannot be null");
        }

        this.service = service;
    }

    // Comparison

    public boolean performCompare(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        return service.compare(
                quantity1,
                quantity2);
    }

    // Conversion

    public QuantityDTO performConvert(
            QuantityDTO quantity,
            QuantityDTO targetUnit) {

        return service.convert(
                quantity,
                targetUnit);
    }

    // Addition

    public QuantityDTO performAdd(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        return service.add(
                quantity1,
                quantity2);
    }

    public QuantityDTO performAdd(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        return service.add(
                quantity1,
                quantity2,
                targetUnit);
    }

    // Subtraction

    public QuantityDTO performSubtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        return service.subtract(
                quantity1,
                quantity2);
    }

    public QuantityDTO performSubtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit) {

        return service.subtract(
                quantity1,
                quantity2,
                targetUnit);
    }

    // Division

    public double performDivide(
            QuantityDTO quantity1,
            QuantityDTO quantity2) {

        return service.divide(
                quantity1,
                quantity2);
    }

    // Display Helper

    public void displayResult(
            Object result) {

        System.out.println(result);
    }
}