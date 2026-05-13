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
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
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

    
    
    
    // Convert from one unit to another
     public  Length convertTo(LengthUnit targetUnit) {

    // Validation
    if (targetUnit == null) {
        throw new IllegalArgumentException("Units cannot be null");
    }

    // Convert source → base unit (inches)
    double inches = this.value * this.unit.getConversionFactor();

    // Convert base unit → target
    double convertedValue =inches / targetUnit.conversionFactor;
    return new Length(convertedValue,targetUnit);
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {

        return value + " " + unit;
    }

    //uc6
    public Length add(Length otherLength) {
    if (otherLength == null) {
        throw new IllegalArgumentException("Length cannot be null");
    }

    // convert both into inches
    double thisInches =this.value * this.unit.getConversionFactor();

    double otherInches =otherLength.value * otherLength.unit.getConversionFactor();

    // add
    double totalInches =thisInches + otherInches;

    // convert back into first operand unit
    double resultValue =totalInches / this.unit.getConversionFactor();
    return new Length(resultValue, this.unit);
}
}

