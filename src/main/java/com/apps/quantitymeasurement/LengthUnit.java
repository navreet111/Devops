package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert current unit → base unit (FEET)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert base unit → target unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

     @Override
    public String getUnitName() {
        return name();
    }
}