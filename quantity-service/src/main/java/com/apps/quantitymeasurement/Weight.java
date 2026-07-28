package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    public Weight(double value, WeightUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Weight other = (Weight) obj;

        double difference =
                Math.abs(
                        this.unit.convertToBaseUnit(this.value)
                                -
                                other.unit.convertToBaseUnit(other.value)
                );

        return difference < EPSILON;
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double converted =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight other) {

        return add(other, this.unit);
    }

    public Weight add(Weight other,
                      WeightUnit targetUnit) {

        double base1 =
                this.unit.convertToBaseUnit(this.value);

        double base2 =
                other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double converted =
                targetUnit.convertFromBaseUnit(sum);

        return new Weight(converted, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
