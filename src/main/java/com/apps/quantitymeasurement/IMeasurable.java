package com.apps.quantitymeasurement;

    public interface IMeasurable {
    SupportsArithmetic supportsArithmetic = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
    default boolean supportsArithmetic() {
    return supportsArithmetic.isSupported();
}
default void validateOperationSupport(String operation) {
    // default : all units support arithmetic
}
}

