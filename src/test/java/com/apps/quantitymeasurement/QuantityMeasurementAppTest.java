package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
// import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    // Inches == Inches (same value)
    @Test
    public void testInchesEquality() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    // Feet == Inches (conversion check)
    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    // Feet != Feet (different values)
    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    // Inches != Inches (different values)
    @Test
    public void testInchesInequality() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    // Cross unit inequality
    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(10.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    // Multiple comparison (bigger values)
    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(2.0, LengthUnit.FEET);
        Length l2 = new Length(24.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToYard_SameValue() {

        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {

        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {

        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_InchesToYard_EquivalentValue() {

        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(inches.equals(yard));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(2.0, LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {

        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalentValue() {

        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_YardWithNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Length yard = new Length(1.0, null);
                    yard.equals(new Length(1.0, LengthUnit.YARDS));
                });
    }

    @Test
    public void testEquality_YardSameReference() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    public void testEquality_YardNullComparison() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    public void testEquality_CentimetersWithNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Length cm = new Length(1.0, null);
                    cm.equals(new Length(1.0, LengthUnit.CENTIMETERS));
                });
    }

    @Test
    public void testEquality_CentimetersSameReference() {

        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    @Test
    public void testEquality_CentimetersNullComparison() {

        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {

        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);
        Length inches = new Length(72.0, LengthUnit.INCHES);
        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }

    // UC5 Conversion Test Cases
    @Test
    void testConversion_FeetToInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_InchesToFeet() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_YardsToInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_InchesToYards() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_CentimetersToInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        assertEquals(1.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_FeetToYard() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                6.0,
                LengthUnit.FEET,
                LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        Length first = QuantityMeasurementApp.demonstrateLengthConversion(
                5.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        Length second = QuantityMeasurementApp.demonstrateLengthConversion(
                first.getValue(),
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(5.0, second.getValue(), 1e-6);
    }

    @Test
    void testConversion_ZeroValue() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(0.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_NegativeValue() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                -1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(-12.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthConversion(
                        1.0,
                        null,
                        LengthUnit.INCHES));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthConversion(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthConversion(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    void testConversion_PrecisionTolerance() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        assertEquals(
                1.0,
                result.getValue(),
                1e-6);
    }

    // uc6
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length expected = new Length(3.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {

        Length l1 = new Length(6.0, LengthUnit.INCHES);
        Length l2 = new Length(6.0, LengthUnit.INCHES);

        Length expected = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length expected = new Length(2.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        Length expected = new Length(24.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        Length expected = new Length(2.0, LengthUnit.YARDS);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {

        Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    public void testAddition_WithZero() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);

        Length expected = new Length(5.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_NegativeValues() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length expected = new Length(3.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_NullSecondOperand() {

        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthAddition(l1, null));
    }

    @Test
    public void testAddition_LargeValues() {

        Length l1 = new Length(1_000_000.0, LengthUnit.FEET);
        Length l2 = new Length(1_000_000.0, LengthUnit.FEET);

        Length expected = new Length(2_000_000.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertTrue(result.equals(expected));
    }

    @Test
    public void testAddition_SmallValues() {

        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        assertEquals(0.003, result.getValue(), 1e-6);
    }

    @Test
    public void testAddition_Commutativity() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result1 = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);

        Length result2 = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.FEET));

        Length result1InInches = result1.convertTo(LengthUnit.INCHES);

        assertTrue(result1InInches.equals(result2));
    }
    // uc7

    @Test
    public void testUC7_Addition_ResultInFeet() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(
                new Length(2.0, LengthUnit.FEET),
                result);
    }

    @Test
    public void testUC7_Addition_ResultInInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertEquals(
                new Length(24.0, LengthUnit.INCHES),
                result);
    }

    @Test
    public void testUC7_Addition_ResultInYards() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(
                0.667,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC7_Addition_ResultInCentimeters() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        assertEquals(
                5.08,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC7_Addition_TargetMatchesFirstOperand() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(
                new Length(3.0, LengthUnit.YARDS),
                result);
    }

    @Test
    public void testUC7_Addition_TargetMatchesSecondOperand() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.FEET);

        assertEquals(
                new Length(9.0, LengthUnit.FEET),
                result);
    }

    @Test
    public void testUC7_Addition_CommutativeProperty() {

        Length result1 = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        Length result2 = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(result1, result2);
    }

    @Test
    public void testUC7_Addition_WithZeroValue() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(
                1.667,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC7_Addition_WithNegativeValue() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(
                new Length(36.0, LengthUnit.INCHES),
                result);
    }

    @Test
    public void testUC7_Addition_NullTargetUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES),
                        null));
    }

    @Test
    public void testUC7_Addition_LargeScaleToSmallScale() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1000.0, LengthUnit.FEET),
                new Length(500.0, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(
                18000.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC7_Addition_SmallScaleToLargeScale() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(12.0, LengthUnit.INCHES),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(
                0.667,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC7_Addition_AllUnitCombinationScenario() {

        Length result1 = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        Length result2 = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(36.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(60.96, result1.getValue(), 1e-2);

        assertEquals(6.0, result2.getValue(), 1e-2);
    }

    @Test
    public void testUC7_Addition_FloatingPointPrecision() {

        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        assertEquals(
                5.08,
                result.getValue(),
                1e-2);
    }

    // 1
    @Test
    public void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor());
    }

    // 2
    @Test
    public void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12,
                LengthUnit.INCHES.getConversionFactor(),
                1e-2);
    }

    // 3
    @Test
    public void testLengthUnitEnum_YardsConstant() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.getConversionFactor());
    }

    // 4
    @Test
    public void testLengthUnitEnum_CentimetersConstant() {

        assertEquals(
                1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                1e-2);
    }

    // 5
    @Test
    public void testConvertToBaseUnit_FeetToFeet() {

        assertEquals(
                5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                1e-2);
    }

    // 6
    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                1e-2);
    }

    // 7
    @Test
    public void testConvertToBaseUnit_YardsToFeet() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                1e-2);
    }

    // 8
    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {

        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                1e-2);
    }

    // 9
    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {

        assertEquals(
                2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                1e-2);
    }

    // 10
    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                1e-2);
    }

    // 11
    @Test
    public void testConvertFromBaseUnit_FeetToYards() {

        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                1e-2);
    }

    // 12
    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {

        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                1e-2);
    }

    // 13
    @Test
    public void testQuantityLengthRefactored_Equality() {

        Length length1 = new Length(1.0, LengthUnit.FEET);

        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(length1.equals(length2));
    }

    // 14
    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(
                new Length(12.0, LengthUnit.INCHES),
                result);
    }

    // 15
    @Test
    public void testQuantityLengthRefactored_Add() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(
                        new Length(
                                12.0,
                                LengthUnit.INCHES),
                        LengthUnit.FEET);

        assertEquals(
                new Length(2.0, LengthUnit.FEET),
                result);
    }

    // 16
    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(
                        new Length(
                                12.0,
                                LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(
                0.667,
                result.getValue(),
                1e-2);
    }

    // 17
    @Test
    public void testQuantityLengthRefactored_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    // 18
    @Test
    public void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(
                        Double.NaN,
                        LengthUnit.FEET));
    }

    // 19
    @Test
    public void testBackwardCompatibility_UC1EqualityTests() {

        Length length1 = new Length(1.0, LengthUnit.FEET);

        Length length2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(length1.equals(length2));
    }

    // 20
    @Test
    public void testBackwardCompatibility_UC5ConversionTests() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(
                new Length(12.0, LengthUnit.INCHES),
                result);
    }

    // 21
    @Test
    public void testBackwardCompatibility_UC6AdditionTests() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(
                        new Length(
                                12.0,
                                LengthUnit.INCHES));

        assertEquals(
                new Length(2.0, LengthUnit.FEET),
                result);
    }

    // 22
    @Test
    public void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {

        Length result = new Length(1.0, LengthUnit.FEET)
                .add(
                        new Length(
                                12.0,
                                LengthUnit.INCHES),
                        LengthUnit.INCHES);

        assertEquals(
                new Length(24.0, LengthUnit.INCHES),
                result);
    }

    // 23
    @Test
    public void testArchitecturalScalability_MultipleCategories() {

        assertNotNull(LengthUnit.FEET);

        assertTrue(true);
    }

    // 24
    @Test
    public void testRoundTripConversion_RefactoredDesign() {

        Length original = new Length(5.0, LengthUnit.FEET);

        Length converted = original.convertTo(LengthUnit.INCHES);

        Length back = converted.convertTo(LengthUnit.FEET);

        assertEquals(
                original.getValue(),
                back.getValue(),
                1e-2);
    }

    // 25
    @Test
    public void testUnitImmutability() {

        LengthUnit unit = LengthUnit.FEET;

        assertEquals(
                1.0,
                unit.getConversionFactor());
    }
    // uc9
    // ========================= UC9 TEST CASES =========================

    @Test
    public void testUC9_Equality_KilogramToKilogram_SameValue() {

        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_KilogramToKilogram_DifferentValue() {

        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(2.0, WeightUnit.KILOGRAM);

        assertEquals(false, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_KilogramToGram_EquivalentValue() {

        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(1000.0, WeightUnit.GRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_GramToKilogram_EquivalentValue() {

        Weight weight1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight weight2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_WeightVsLength_Incompatible() {

        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertEquals(false, weight.equals(length));
    }

    @Test
    public void testUC9_Equality_NullComparison() {

        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(false, weight.equals(null));
    }

    @Test
    public void testUC9_Equality_SameReference() {

        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(true, weight.equals(weight));
    }

    @Test
    public void testUC9_Equality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    public void testUC9_Equality_TransitiveProperty() {

        Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(1000.0, WeightUnit.GRAM);
        Weight weight3 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertEquals(true, weight1.equals(weight2));
        assertEquals(true, weight2.equals(weight3));
        assertEquals(true, weight1.equals(weight3));
    }

    @Test
    public void testUC9_Equality_ZeroValue() {

        Weight weight1 = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(0.0, WeightUnit.GRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_NegativeWeight() {

        Weight weight1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(-1000.0, WeightUnit.GRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_LargeWeightValue() {

        Weight weight1 = new Weight(1000000.0, WeightUnit.GRAM);
        Weight weight2 = new Weight(1000.0, WeightUnit.KILOGRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Equality_SmallWeightValue() {

        Weight weight1 = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(1.0, WeightUnit.GRAM);

        assertEquals(true, weight1.equals(weight2));
    }

    @Test
    public void testUC9_Conversion_PoundToKilogram() {

        Weight converted = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                1.0,
                converted.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Conversion_KilogramToPound() {

        Weight converted = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);

        assertEquals(
                2.20462,
                converted.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Conversion_SameUnit() {

        Weight converted = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                5.0,
                converted.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Conversion_ZeroValue() {

        Weight converted = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(
                0.0,
                converted.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Conversion_NegativeValue() {

        Weight converted = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(
                -1000.0,
                converted.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Conversion_RoundTrip() {

        Weight original = new Weight(1.5, WeightUnit.KILOGRAM);

        Weight roundTrip = original.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                1.5,
                roundTrip.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_SameUnit_KilogramPlusKilogram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));

        assertEquals(
                3.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_CrossUnit_KilogramPlusGram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(
                2.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_CrossUnit_PoundPlusKilogram() {

        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(
                4.40924,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_ExplicitTargetUnit_Kilogram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(
                        new Weight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM);

        assertEquals(
                2000.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_Commutativity() {

        Weight result1 = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        Weight result2 = new Weight(1000.0, WeightUnit.GRAM)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(
                result1.convertTo(WeightUnit.KILOGRAM).getValue(),
                result2.convertTo(WeightUnit.KILOGRAM).getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_WithZero() {

        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM));

        assertEquals(
                5.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_NegativeValues() {

        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM));

        assertEquals(
                3.0,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_LargeValues() {

        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM));

        assertEquals(
                2e6,
                result.getValue(),
                1e-2);
    }

    @Test
    public void testUC9_Addition_ExplicitTargetUnit_Pound() {

        Weight result = new Weight(1.0, WeightUnit.POUND)
                .add(new Weight(453.592, WeightUnit.GRAM), WeightUnit.POUND);

        assertEquals(
                2.0,
                result.getValue(),
                1e-2);
    }

}
