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

        
                

        return new Quantity<>(finalValue, targetUnit);
    }
     public Quantity<U> subtract(Quantity<U> other) {

    if (other == null) {
        throw new IllegalArgumentException("Quantity cannot be null");
    }

    if (!unit.getClass().equals(other.unit.getClass())) {
        throw new IllegalArgumentException("Incompatible units");
    }

    double thisBase = unit.convertToBaseUnit(value);
    double otherBase = other.unit.convertToBaseUnit(other.value);

    double resultBase = thisBase - otherBase;

    double result =
            unit.convertFromBaseUnit(resultBase);

    result = Math.round(result * 100.0) / 100.0;

    return new Quantity<>(result, unit);
}
public Quantity<U> subtract(
        Quantity<U> other,
        U targetUnit) {

    if (other == null) {
        throw new IllegalArgumentException("Quantity cannot be null");
    }

    if (targetUnit == null) {
        throw new IllegalArgumentException("Target unit cannot be null");
    }

    if (!unit.getClass().equals(other.unit.getClass())) {
        throw new IllegalArgumentException("Incompatible units");
    }

    double thisBase = unit.convertToBaseUnit(value);
    double otherBase = other.unit.convertToBaseUnit(other.value);

    double resultBase = thisBase - otherBase;

    double result =
            targetUnit.convertFromBaseUnit(resultBase);

    result = Math.round(result * 100.0) / 100.0;

    return new Quantity<>(result, targetUnit);
}
public double divide(Quantity<U> other) {

    if (other == null) {
        throw new IllegalArgumentException("Quantity cannot be null");
    }

    if (!unit.getClass().equals(other.unit.getClass())) {
        throw new IllegalArgumentException("Incompatible units");
    }

    double divisor =
            other.unit.convertToBaseUnit(other.value);

    if (divisor == 0.0) {
        throw new ArithmeticException("Division by zero");
    }

    double dividend =
            unit.convertToBaseUnit(value);

    return dividend / divisor;
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

        return Math.abs(thisBase - otherBase) < 0.01;
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