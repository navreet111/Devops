package com.apps.quantitymeasurement;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    private final SupportsArithmetic
            supportsArithmetic =
            () -> false;

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    /**
     * Celsius is treated as base unit
     */
    @Override
    public double convertToBaseUnit(
            double value) {

        switch (this) {

            case CELSIUS:
                return value;

            case FAHRENHEIT:
                return (value - 32) * 5.0 / 9.0;

            case KELVIN:
                return value - 273.15;

            default:
                throw new IllegalStateException(
                        "Unknown temperature unit");
        }
    }

    /**
     * Convert from Celsius(base)
     */
    @Override
    public double convertFromBaseUnit(
            double baseValue) {

        switch (this) {

            case CELSIUS:
                return baseValue;

            case FAHRENHEIT:
                return (baseValue * 9.0 / 5.0) + 32;

            case KELVIN:
                return baseValue + 273.15;

            default:
                throw new IllegalStateException(
                        "Unknown temperature unit");
        }
    }

    /**
     * Optional direct conversion
     */
    public double convertTo(
            double value,
            TemperatureUnit targetUnit) {

        double baseValue =
                convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(
                baseValue);
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
public void validateOperationSupport(String operation) {

    throw new UnsupportedOperationException(
            "Temperature does not support "
                    + operation
                    + " operation");
}
    @Override
    public String toString() {
        return getUnitName();
    }
}