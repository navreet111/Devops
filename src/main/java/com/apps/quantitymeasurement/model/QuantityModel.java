package com.apps.quantitymeasurement.model;

import com.apps.quantitymeasurement.IMeasurable;

import java.util.Objects;

public class QuantityModel
        <U extends IMeasurable> {

    private final double value;

    private final U unit;

    public QuantityModel(
            double value,
            U unit
    ) {

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {

        return value;
    }

    public U getUnit() {

        return unit;
    }

    @Override
    public boolean equals(
            Object obj
    ) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QuantityModel<?>)) {
            return false;
        }

        QuantityModel<?> other =
                (QuantityModel<?>) obj;

        return Double.compare(
                value,
                other.value) == 0

                && Objects.equals(
                unit,
                other.unit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                value,
                unit);
    }

    @Override
    public String toString() {

        return "QuantityModel{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}