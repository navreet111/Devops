package com.apps.quantitymeasurement;

// import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // Enum for units
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Convert to base unit (inches)
    private double toInches() {
        return value * unit.getConversionFactor();
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Double.compare(this.toInches(), other.toInches()) == 0;
    }

    // (Best practice) override hashCode when equals is overridden
    // @Override
    // public int hashCode() {
    //     return Objects.hash(toInches());
    // }

    // Demo method
    public static void demonstrateLengthEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Length equal: " + l1.equals(l2));
    }
}