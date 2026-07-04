package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityDTO quantity1,
            QuantityDTO quantity2);

    QuantityDTO convert(
            QuantityDTO quantity,
            QuantityDTO targetUnit);

    QuantityDTO add(
            QuantityDTO quantity1,
            QuantityDTO quantity2);

    QuantityDTO subtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2);

    double divide(
            QuantityDTO quantity1,
            QuantityDTO quantity2);
    QuantityDTO add(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit);
    QuantityDTO subtract(
            QuantityDTO quantity1,
            QuantityDTO quantity2,
            QuantityDTO targetUnit);
    long getTotalCount();
}