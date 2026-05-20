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

    

   

    // Equals method
   @Override
public boolean equals(Object obj) {

    if (this == obj) {
        return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }

    Length other = (Length) obj;

    double difference =
            Math.abs(
                    this.unit.convertToBaseUnit(this.value)
                    -
                    other.unit.convertToBaseUnit(other.value)
            );

    return difference < 1e-6;
}

    // (Best practice) override hashCode when equals is overridden
    // @Override
    // public int hashCode() {
    //     return Objects.hash(toInches());
    // }

    
    
    
    public Length convertTo(LengthUnit targetUnit) {

    if (targetUnit == null) {
        throw new IllegalArgumentException("Units cannot be null");
    }

    double baseValue =
            unit.convertToBaseUnit(this.value);

    double convertedValue =
            targetUnit.convertFromBaseUnit(baseValue);

    return new Length(convertedValue, targetUnit);
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

    public Length add(Length otherLength) {

    if (otherLength == null) {
        throw new IllegalArgumentException("Length cannot be null");
    }

    double thisBase =
            unit.convertToBaseUnit(this.value);

    double otherBase =
            otherLength.unit.convertToBaseUnit(
                    otherLength.value
            );

    double totalBase = thisBase + otherBase;

    double resultValue =
            this.unit.convertFromBaseUnit(totalBase);

    return new Length(resultValue, this.unit);
}

    public Length add(
        Length otherLength,
        LengthUnit targetUnit
) {

    if (otherLength == null || targetUnit == null) {
        throw new IllegalArgumentException("Invalid input");
    }

    double thisBase =
            unit.convertToBaseUnit(this.value);

    double otherBase =
            otherLength.unit.convertToBaseUnit(
                    otherLength.value
            );

    double totalBase = thisBase + otherBase;

    double resultValue =
            targetUnit.convertFromBaseUnit(totalBase);

    return new Length(resultValue, targetUnit);
}
}

