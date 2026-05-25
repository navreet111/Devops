package com.apps.quantitymeasurement;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // convert to base unit (kilogram)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // convert from kilogram to target unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
     @Override
    public String getUnitName() {
        return name();
    }
}