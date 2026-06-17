package com.apps.quantitymeasurement;

import java.util.Objects;



public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
   private enum ArithmeticOperation {

    ADD {
        @Override
        double compute(double a, double b) {
            return a + b;
        }
    },

    SUBTRACT {
        @Override
        double compute(double a, double b) {
            return a - b;
        }
    },

    DIVIDE {
        @Override
        double compute(double a, double b) {

            if (b == 0.0) {
                throw new ArithmeticException("Division by zero");
            }

            return a / b;
        }
    };

    abstract double compute(double a, double b);
}
private void validateArithmeticOperands(
        Quantity<U> other,
        U targetUnit,
        boolean targetUnitRequired) {

    if (other == null) {
        throw new IllegalArgumentException("Quantity cannot be null");
    }

    if (targetUnitRequired && targetUnit == null) {
        throw new IllegalArgumentException("Target unit cannot be null");
    }

    if (!unit.getClass().equals(other.unit.getClass())) {
        throw new IllegalArgumentException("Incompatible units");
    }

    if (!Double.isFinite(value) ||
            !Double.isFinite(other.value)) {

        throw new IllegalArgumentException("Value must be finite");
    }
}
private double performArithmetic(
        Quantity<U> other,
        ArithmeticOperation operation) {

    double thisBase =
            unit.convertToBaseUnit(value);

    double otherBase =
            other.unit.convertToBaseUnit(other.value);

    return operation.compute(
            thisBase,
            otherBase);
}

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
    // public Quantity<U> add(Quantity<U> other) {

    //     double thisBase =
    //             unit.convertToBaseUnit(value);

    //     double otherBase =
    //             other.unit.convertToBaseUnit(other.value);

    //     double sumBase =
    //             thisBase + otherBase;

    //     double finalValue =
    //             unit.convertFromBaseUnit(sumBase);

        

    //     return new Quantity<>(finalValue, unit);
    // }
    public Quantity<U> add(Quantity<U> other) {
    return add(other, unit);
}

    // Add quantities with target unit
    // public Quantity<U> add(
    //         Quantity<U> other,
    //         U targetUnit) {

    //     double thisBase =
    //             unit.convertToBaseUnit(value);

    //     double otherBase =
    //             other.unit.convertToBaseUnit(other.value);

    //     double sumBase =
    //             thisBase + otherBase;

    //     double finalValue =
    //             targetUnit.convertFromBaseUnit(sumBase);

        
                

    //     return new Quantity<>(finalValue, targetUnit);
    // }
    public Quantity<U> add(
        Quantity<U> other,
        U targetUnit) {

    validateArithmeticOperands(
            other,
            targetUnit,
            true);

    double resultBase =
            performArithmetic(
                    other,
                    ArithmeticOperation.ADD);

    double result =
            targetUnit.convertFromBaseUnit(resultBase);

    result =
            Math.round(result * 100.0) / 100.0;

    return new Quantity<>(result, targetUnit);
}
//      public Quantity<U> subtract(Quantity<U> other) {

//     if (other == null) {
//         throw new IllegalArgumentException("Quantity cannot be null");
//     }

//     if (!unit.getClass().equals(other.unit.getClass())) {
//         throw new IllegalArgumentException("Incompatible units");
//     }

//     double thisBase = unit.convertToBaseUnit(value);
//     double otherBase = other.unit.convertToBaseUnit(other.value);

//     double resultBase = thisBase - otherBase;

//     double result =
//             unit.convertFromBaseUnit(resultBase);

//     result = Math.round(result * 100.0) / 100.0;

//     return new Quantity<>(result, unit);
// }
public Quantity<U> subtract(Quantity<U> other) {
    return subtract(other, unit);
}
public Quantity<U> subtract(
        Quantity<U> other,
        U targetUnit) {

    validateArithmeticOperands(
            other,
            targetUnit,
            true);

    double resultBase =
            performArithmetic(
                    other,
                    ArithmeticOperation.SUBTRACT);

    double result =
            targetUnit.convertFromBaseUnit(resultBase);

    result =
            Math.round(result * 100.0) / 100.0;

    return new Quantity<>(result, targetUnit);
}
// public Quantity<U> subtract(
//         Quantity<U> other,
//         U targetUnit) {

//     if (other == null) {
//         throw new IllegalArgumentException("Quantity cannot be null");
//     }

//     if (targetUnit == null) {
//         throw new IllegalArgumentException("Target unit cannot be null");
//     }

//     if (!unit.getClass().equals(other.unit.getClass())) {
//         throw new IllegalArgumentException("Incompatible units");
//     }

//     double thisBase = unit.convertToBaseUnit(value);
//     double otherBase = other.unit.convertToBaseUnit(other.value);

//     double resultBase = thisBase - otherBase;

//     double result =
//             targetUnit.convertFromBaseUnit(resultBase);

//     result = Math.round(result * 100.0) / 100.0;

//     return new Quantity<>(result, targetUnit);
// }
// public double divide(Quantity<U> other) {

//     if (other == null) {
//         throw new IllegalArgumentException("Quantity cannot be null");
//     }

//     if (!unit.getClass().equals(other.unit.getClass())) {
//         throw new IllegalArgumentException("Incompatible units");
//     }

//     double divisor =
//             other.unit.convertToBaseUnit(other.value);

//     if (divisor == 0.0) {
//         throw new ArithmeticException("Division by zero");
//     }

//     double dividend =
//             unit.convertToBaseUnit(value);

//     return dividend / divisor;
// }
public double divide(Quantity<U> other) {

    validateArithmeticOperands(
            other,
            null,
            false);

    return performArithmetic(
            other,
            ArithmeticOperation.DIVIDE);
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