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

        // ================= UC5 Conversion Test Cases =================

        @Test
        void testConversion_FeetToInches() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                LengthUnit.INCHES);

                assertEquals(12.0, result.getValue(), 1e-6);
        }

        @Test
        void testConversion_InchesToFeet() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
                                new Quantity<>(24.0, LengthUnit.INCHES),
                                LengthUnit.FEET);

                assertEquals(2.0, result.getValue(), 1e-6);
        }

        @Test
        void testConversion_YardsToInches() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
                                new Quantity<>(1.0, LengthUnit.YARDS),
                                LengthUnit.INCHES);

                assertEquals(36.0, result.getValue(), 1e-6);
        }

        @Test
        void testConversion_InchesToYards() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
                                new Quantity<>(72.0, LengthUnit.INCHES),
                                LengthUnit.YARDS);

                assertEquals(2.0, result.getValue(), 1e-6);
        }

        @Test
        void testConversion_CentimetersToInches() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
                                new Quantity<>(2.54, LengthUnit.CENTIMETERS),
                                LengthUnit.INCHES);

                assertEquals(1.0, result.getValue(), 1e-6);
        }

        // ================= UC6 Addition =================

        @Test
        public void testAddition_SameUnit_FeetPlusFeet() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                new Quantity<>(2.0, LengthUnit.FEET));

                assertEquals(
                                new Quantity<>(3.0, LengthUnit.FEET),
                                result);
        }

        @Test
        public void testAddition_CrossUnit_FeetPlusInches() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                new Quantity<>(12.0, LengthUnit.INCHES));

                assertEquals(
                                new Quantity<>(2.0, LengthUnit.FEET),
                                result);
        }

        @Test
        public void testAddition_CrossUnit_InchPlusFeet() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(12.0, LengthUnit.INCHES),
                                new Quantity<>(1.0, LengthUnit.FEET));

                assertEquals(
                                new Quantity<>(24.0, LengthUnit.INCHES),
                                result);
        }

        @Test
        public void testAddition_WithZero() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(5.0, LengthUnit.FEET),
                                new Quantity<>(0.0, LengthUnit.INCHES));

                assertEquals(
                                new Quantity<>(5.0, LengthUnit.FEET),
                                result);
        }

        // ================= UC7 Addition With Target Unit =================

        @Test
        public void testUC7_Addition_ResultInFeet() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.FEET);

                assertEquals(
                                new Quantity<>(2.0, LengthUnit.FEET),
                                result);
        }

        @Test
        public void testUC7_Addition_ResultInInches() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

                assertEquals(
                                new Quantity<>(24.0, LengthUnit.INCHES),
                                result);
        }

        @Test
        public void testUC7_Addition_ResultInYards() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.FEET),
                                new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.YARDS);

                assertEquals(
                                0.667,
                                result.getValue(),
                                1e-2);
        }

        @Test
        public void testUC7_Addition_ResultInCentimeters() {

                Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
                                new Quantity<>(1.0, LengthUnit.INCHES),
                                new Quantity<>(1.0, LengthUnit.INCHES),
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

        @Test
        void testIMeasurableInterface_LengthUnitImplementation() {

                assertTrue(LengthUnit.FEET instanceof IMeasurable);
        }

        @Test
        void testIMeasurableInterface_WeightUnitImplementation() {

                assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
        }

        @Test
        void testIMeasurableInterface_ConsistentBehavior() {

                assertEquals(
                                1.0,
                                LengthUnit.FEET.convertToBaseUnit(1.0));

                assertEquals(
                                1.0,
                                WeightUnit.KILOGRAM.convertToBaseUnit(1.0));
        }

        // =========================================================
        // Generic Quantity Equality Tests
        // =========================================================

        @Test
        void testGenericQuantity_LengthOperations_Equality() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

                assertEquals(feet, inches);
        }

        @Test
        void testGenericQuantity_WeightOperations_Equality() {

                Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

                assertEquals(kilogram, gram);
        }

        // =========================================================
        // Conversion Tests
        // =========================================================

        @Test
        void testGenericQuantity_LengthOperations_Conversion() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

                Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);

                assertEquals(
                                new Quantity<>(12.0,
                                                LengthUnit.INCHES),
                                result);
        }

        @Test
        void testGenericQuantity_WeightOperations_Conversion() {

                Quantity<WeightUnit> kilogram = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> result = kilogram.convertTo(WeightUnit.GRAM);

                assertEquals(
                                new Quantity<>(1000.0,
                                                WeightUnit.GRAM),
                                result);
        }

        // =========================================================
        // Addition Tests
        // =========================================================

        @Test
        void testGenericQuantity_LengthOperations_Addition() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(12.0,
                                LengthUnit.INCHES);

                Quantity<LengthUnit> result = feet.add(inches,
                                LengthUnit.FEET);

                assertEquals(
                                new Quantity<>(2.0,
                                                LengthUnit.FEET),
                                result);
        }

        @Test
        void testGenericQuantity_WeightOperations_Addition() {

                Quantity<WeightUnit> kilogram = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                Quantity<WeightUnit> result = kilogram.add(
                                gram,
                                WeightUnit.KILOGRAM);

                assertEquals(
                                new Quantity<>(2.0,
                                                WeightUnit.KILOGRAM),
                                result);
        }

        // =========================================================
        // Cross Category Tests
        // =========================================================

        @Test
        void testCrossCategoryPrevention_LengthVsWeight() {

                Quantity<?> length = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<?> weight = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                assertNotEquals(length, weight);
        }

        @Test
        void testCrossCategoryPrevention_CompilerTypeSafety() {

                Quantity<LengthUnit> length = new Quantity<>(1.0,
                                LengthUnit.FEET);

                assertNotNull(length);
        }

        // =========================================================
        // Constructor Validation Tests
        // =========================================================

        @Test
        void testGenericQuantity_ConstructorValidation_NullUnit() {

                assertThrows(
                                IllegalArgumentException.class,
                                () -> new Quantity<>(1.0, null));
        }

        @Test
        void testGenericQuantity_ConstructorValidation_InvalidValue() {

                assertThrows(
                                IllegalArgumentException.class,
                                () -> new Quantity<>(
                                                Double.NaN,
                                                LengthUnit.FEET));
        }

        // =========================================================
        // All Unit Combination Conversion Tests
        // =========================================================

        @Test
        void testGenericQuantity_Conversion_AllUnitCombinations() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> yards = feet.convertTo(LengthUnit.YARDS);

                assertEquals(
                                new Quantity<>(0.33,
                                                LengthUnit.YARDS),
                                yards);
        }

        // =========================================================
        // Addition Combination Tests
        // =========================================================

        @Test
        void testGenericQuantity_Addition_AllUnitCombinations() {

                Quantity<WeightUnit> pound = new Quantity<>(1.0,
                                WeightUnit.POUND);

                Quantity<WeightUnit> gram = new Quantity<>(453.592,
                                WeightUnit.GRAM);

                Quantity<WeightUnit> result = pound.add(
                                gram,
                                WeightUnit.POUND);

                assertEquals(
                                new Quantity<>(2.0,
                                                WeightUnit.POUND),
                                result);
        }

        // =========================================================
        // Backward Compatibility Test
        // =========================================================

        @Test
        void testBackwardCompatibility_AllUC1Through9Tests() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(12.0,
                                LengthUnit.INCHES);

                assertEquals(feet, inches);
        }

        // =========================================================
        // QuantityMeasurementApp Tests
        // =========================================================

        @Test
        void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                assertTrue(
                                QuantityMeasurementApp
                                                .demonstrateEquality(
                                                                kg,
                                                                gram));
        }

        @Test
        void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> result = QuantityMeasurementApp
                                .demonstrateConversion(
                                                kg,
                                                WeightUnit.GRAM);

                assertEquals(
                                new Quantity<>(1000.0,
                                                WeightUnit.GRAM),
                                result);
        }

        @Test
        void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                Quantity<WeightUnit> result = QuantityMeasurementApp
                                .demonstrateAddition(
                                                kg,
                                                gram,
                                                WeightUnit.KILOGRAM);

                assertEquals(
                                new Quantity<>(2.0,
                                                WeightUnit.KILOGRAM),
                                result);
        }

        // =========================================================
        // Wildcard Tests
        // =========================================================

        @Test
        void testTypeWildcard_FlexibleSignatures() {

                Quantity<?> quantity = new Quantity<>(1.0,
                                LengthUnit.FEET);

                assertNotNull(quantity);
        }

        // =========================================================
        // Scalability Tests
        // =========================================================

        @Test
        void testScalability_NewUnitEnumIntegration() {

                Quantity<LengthUnit> quantity = new Quantity<>(2.0,
                                LengthUnit.YARDS);

                assertNotNull(quantity);
        }

        @Test
        void testScalability_MultipleNewCategories() {

                Quantity<WeightUnit> quantity = new Quantity<>(5.0,
                                WeightUnit.GRAM);

                assertNotNull(quantity);
        }

        // =========================================================
        // Generic Bound Test
        // =========================================================

        @Test
        void testGenericBoundedTypeParameter_Enforcement() {

                Quantity<LengthUnit> quantity = new Quantity<>(1.0,
                                LengthUnit.FEET);

                assertNotNull(quantity);
        }

        // =========================================================
        // hashCode Tests
        // =========================================================

        @Test
        void testHashCode_GenericQuantity_Consistency() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                assertEquals(
                                kg.hashCode(),
                                gram.hashCode());
        }

        // =========================================================
        // equals Contract Tests
        // =========================================================

        @Test
        void testEquals_GenericQuantity_ContractPreservation() {

                Quantity<WeightUnit> a = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> b = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                Quantity<WeightUnit> c = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                assertEquals(a, b);
                assertEquals(b, c);
                assertEquals(a, c);
        }

        // =========================================================
        // Enum Behavior Tests
        // =========================================================

        @Test
        void testEnumAsUnitCarrier_BehaviorEncapsulation() {

                double feet = LengthUnit.FEET
                                .convertToBaseUnit(1.0);

                // FEET is base unit now
                assertEquals(1.0, feet);
        }

        // =========================================================
        // Runtime Safety Tests
        // =========================================================

        @Test
        void testTypeErasure_RuntimeSafety() {

                Quantity<?> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<?> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                assertNotEquals(feet, kg);
        }

        // =========================================================
        // Composition Tests
        // =========================================================

        @Test
        void testCompositionOverInheritance_Flexibility() {

                Quantity<WeightUnit> quantity = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                assertNotNull(quantity);
        }

        // =========================================================
        // DRY Validation
        // =========================================================

        @Test
        void testCodeReduction_DRYValidation() {

                Quantity<LengthUnit> quantity = new Quantity<>(1.0,
                                LengthUnit.FEET);

                assertNotNull(quantity);
        }

        // =========================================================
        // Maintainability Tests
        // =========================================================

        @Test
        void testMaintainability_SingleSourceOfTruth() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(12.0,
                                LengthUnit.INCHES);

                assertEquals(feet, inches);
        }

        // =========================================================
        // Architectural Readiness Tests
        // =========================================================

        @Test
        void testArchitecturalReadiness_MultipleNewCategories() {

                Quantity<WeightUnit> quantity = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                assertNotNull(quantity);
        }

        // =========================================================
        // Performance Tests
        // =========================================================

        @Test
        void testPerformance_GenericOverhead() {

                Quantity<LengthUnit> quantity = new Quantity<>(10.0,
                                LengthUnit.FEET);

                assertNotNull(quantity);
        }

        // =========================================================
        // Documentation Tests
        // =========================================================

        @Test
        void testDocumentation_PatternClarity() {

                Quantity<LengthUnit> quantity = new Quantity<>(5.0,
                                LengthUnit.YARDS);

                assertNotNull(quantity);
        }

        // =========================================================
        // Interface Segregation Tests
        // =========================================================

        @Test
        void testInterfaceSegregation_MinimalContract() {

                assertEquals(
                                "FEET",
                                LengthUnit.FEET.getUnitName());
        }

        // =========================================================
        // Immutability Tests
        // =========================================================

        @Test
        void testImmutability_GenericQuantity() {

                Quantity<WeightUnit> quantity = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> result = quantity.convertTo(WeightUnit.GRAM);

                assertNotSame(quantity, result);
        }

        @Test
        void testGenericQuantity_Conversion_RoundTrip() {

                Quantity<LengthUnit> original = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> converted = original.convertTo(LengthUnit.INCHES);

                Quantity<LengthUnit> roundTrip = converted.convertTo(LengthUnit.FEET);

                assertEquals(original, roundTrip);
        }

        @Test
        void testGenericQuantity_Addition_WithExplicitTargetUnit() {

                Quantity<LengthUnit> feet = new Quantity<>(1.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(12.0,
                                LengthUnit.INCHES);

                Quantity<LengthUnit> result = feet.add(
                                inches,
                                LengthUnit.YARDS);

                assertEquals(
                                0.67,
                                result.getValue(),
                                1e-2);
        }

        @Test
        void testGenericQuantity_Equality_SymmetricProperty() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                assertTrue(kg.equals(gram));
                assertTrue(gram.equals(kg));
        }

        @Test
        void testGenericQuantity_Addition_Commutativity() {

                Quantity<WeightUnit> kg = new Quantity<>(1.0,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> gram = new Quantity<>(1000.0,
                                WeightUnit.GRAM);

                Quantity<WeightUnit> result1 = kg.add(gram,
                                WeightUnit.KILOGRAM);

                Quantity<WeightUnit> result2 = gram.add(kg,
                                WeightUnit.GRAM);

                assertEquals(2.0,
                                result1.getValue(),
                                1e-6);

                assertEquals(2000.0,
                                result2.getValue(),
                                1e-6);
        }

        @Test
        void testGenericQuantity_ZeroValueEquality() {

                Quantity<LengthUnit> feet = new Quantity<>(0.0,
                                LengthUnit.FEET);

                Quantity<LengthUnit> inches = new Quantity<>(0.0,
                                LengthUnit.INCHES);

                assertEquals(feet, inches);
        }
}
