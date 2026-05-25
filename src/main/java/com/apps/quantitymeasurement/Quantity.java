package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert quantity to target unit
    public Quantity<U> convertTo(U targetUnit) {

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        convertedValue =
                Math.round(convertedValue * 100.0) / 100.0;

        return new Quantity<>(convertedValue, targetUnit);
    }

    // Add quantities and return result in first quantity unit
    public Quantity<U> add(Quantity<U> other) {

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double sumBase =
                thisBase + otherBase;

        double finalValue =
                unit.convertFromBaseUnit(sumBase);

        finalValue =
                Math.round(finalValue * 100.0) / 100.0;

        return new Quantity<>(finalValue, unit);
    }

    // Add quantities with target unit
    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit) {

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double sumBase =
                thisBase + otherBase;

        double finalValue =
                targetUnit.convertFromBaseUnit(sumBase);

        finalValue =
                Math.round(finalValue * 100.0) / 100.0;

        return new Quantity<>(finalValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        Quantity<?> other =
                (Quantity<?>) obj;

        // Cross-category prevention
        if (this.unit.getClass() !=
                other.unit.getClass()) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {

        double baseValue =
                unit.convertToBaseUnit(value);

        return Objects.hash(baseValue);
    }

    @Override
    public String toString() {

        return "Quantity(" +
                value +
                ", " +
                unit.getUnitName() +
                ")";
    }
}