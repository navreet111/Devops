//package com.apps.quantitymeasurement;
//
//import org.junit.jupiter.api.Test;
//
//import com.apps.quantitymeasurement.app.QuantityMeasurementApp;
//import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
//import com.apps.quantitymeasurement.model.QuantityDTO;
//import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
//import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
//import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
//import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//// import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
//// import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;
//
//public class QuantityMeasurementAppTest {
//
//        private static final double EPSILON = 1e-2;
//
////         @Test
////         public void testFeetEquality() {
////                 Length l1 = new Length(1.0, LengthUnit.FEET);
////                 Length l2 = new Length(1.0, LengthUnit.FEET);
//
////                 assertTrue(l1.equals(l2));
////         }
//
////         // Inches == Inches (same value)
////         @Test
////         public void testInchesEquality() {
////                 Length l1 = new Length(1.0, LengthUnit.INCHES);
////                 Length l2 = new Length(1.0, LengthUnit.INCHES);
//
////                 assertTrue(l1.equals(l2));
////         }
//
////         // Feet == Inches (conversion check)
////         @Test
////         public void testFeetInchesComparison() {
////                 Length l1 = new Length(1.0, LengthUnit.FEET);
////                 Length l2 = new Length(12.0, LengthUnit.INCHES);
//
////                 assertTrue(l1.equals(l2));
////         }
//
////         // Feet != Feet (different values)
////         @Test
////         public void testFeetInequality() {
////                 Length l1 = new Length(1.0, LengthUnit.FEET);
////                 Length l2 = new Length(2.0, LengthUnit.FEET);
//
////                 assertFalse(l1.equals(l2));
////         }
//
////         // Inches != Inches (different values)
////         @Test
////         public void testInchesInequality() {
////                 Length l1 = new Length(1.0, LengthUnit.INCHES);
////                 Length l2 = new Length(2.0, LengthUnit.INCHES);
//
////                 assertFalse(l1.equals(l2));
////         }
//
////         // Cross unit inequality
////         @Test
////         public void testCrossUnitInequality() {
////                 Length l1 = new Length(1.0, LengthUnit.FEET);
////                 Length l2 = new Length(10.0, LengthUnit.INCHES);
//
////                 assertFalse(l1.equals(l2));
////         }
//
////         // Multiple comparison (bigger values)
////         @Test
////         public void testMultipleFeetComparison() {
////                 Length l1 = new Length(2.0, LengthUnit.FEET);
////                 Length l2 = new Length(24.0, LengthUnit.INCHES);
//
////                 assertTrue(l1.equals(l2));
////         }
//
////         @Test
////         public void testEquality_YardToYard_SameValue() {
//
////                 Length yard1 = new Length(1.0, LengthUnit.YARDS);
////                 Length yard2 = new Length(1.0, LengthUnit.YARDS);
////                 assertTrue(yard1.equals(yard2));
////         }
//
////         @Test
////         public void testEquality_YardToYard_DifferentValue() {
//
////                 Length yard1 = new Length(1.0, LengthUnit.YARDS);
////                 Length yard2 = new Length(2.0, LengthUnit.YARDS);
////                 assertFalse(yard1.equals(yard2));
////         }
//
////         @Test
////         public void testEquality_YardToFeet_EquivalentValue() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 Length feet = new Length(3.0, LengthUnit.FEET);
////                 assertTrue(yard.equals(feet));
////         }
//
////         @Test
////         public void testEquality_FeetToYard_EquivalentValue() {
//
////                 Length feet = new Length(3.0, LengthUnit.FEET);
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 assertTrue(feet.equals(yard));
////         }
//
////         @Test
////         public void testEquality_YardToInches_EquivalentValue() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 Length inches = new Length(36.0, LengthUnit.INCHES);
////                 assertTrue(yard.equals(inches));
////         }
//
////         @Test
////         public void testEquality_InchesToYard_EquivalentValue() {
//
////                 Length inches = new Length(36.0, LengthUnit.INCHES);
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 assertTrue(inches.equals(yard));
////         }
//
////         @Test
////         public void testEquality_YardToFeet_NonEquivalentValue() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 Length feet = new Length(2.0, LengthUnit.FEET);
////                 assertFalse(yard.equals(feet));
////         }
//
////         @Test
////         public void testEquality_CentimetersToInches_EquivalentValue() {
//
////                 Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
////                 Length inches = new Length(0.393701, LengthUnit.INCHES);
////                 assertTrue(cm.equals(inches));
////         }
//
////         @Test
////         public void testEquality_CentimetersToFeet_NonEquivalentValue() {
//
////                 Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
////                 Length feet = new Length(1.0, LengthUnit.FEET);
////                 assertFalse(cm.equals(feet));
////         }
//
////         @Test
////         public void testEquality_MultiUnit_TransitiveProperty() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 Length feet = new Length(3.0, LengthUnit.FEET);
////                 Length inches = new Length(36.0, LengthUnit.INCHES);
//
////                 assertTrue(yard.equals(feet));
////                 assertTrue(feet.equals(inches));
////                 assertTrue(yard.equals(inches));
////         }
//
////         @Test
////         public void testEquality_YardWithNullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> {
////                                         Length yard = new Length(1.0, null);
////                                         yard.equals(new Length(1.0, LengthUnit.YARDS));
////                                 });
////         }
//
////         @Test
////         public void testEquality_YardSameReference() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 assertTrue(yard.equals(yard));
////         }
//
////         @Test
////         public void testEquality_YardNullComparison() {
//
////                 Length yard = new Length(1.0, LengthUnit.YARDS);
////                 assertFalse(yard.equals(null));
////         }
//
////         @Test
////         public void testEquality_CentimetersWithNullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> {
////                                         Length cm = new Length(1.0, null);
////                                         cm.equals(new Length(1.0, LengthUnit.CENTIMETERS));
////                                 });
////         }
//
////         @Test
////         public void testEquality_CentimetersSameReference() {
//
////                 Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
////                 assertTrue(cm.equals(cm));
////         }
//
////         @Test
////         public void testEquality_CentimetersNullComparison() {
//
////                 Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
////                 assertFalse(cm.equals(null));
////         }
//
////         @Test
////         public void testEquality_AllUnits_ComplexScenario() {
//
////                 Length yards = new Length(2.0, LengthUnit.YARDS);
////                 Length feet = new Length(6.0, LengthUnit.FEET);
////                 Length inches = new Length(72.0, LengthUnit.INCHES);
////                 assertTrue(yards.equals(feet));
////                 assertTrue(feet.equals(inches));
////                 assertTrue(yards.equals(inches));
////         }
//
////         // ================= UC5 Conversion Test Cases =================
//
////         @Test
////         void testConversion_FeetToInches() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 LengthUnit.INCHES);
//
////                 assertEquals(12.0, result.getValue(), 1e-6);
////         }
//
////         @Test
////         void testConversion_InchesToFeet() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
////                                 new Quantity<>(24.0, LengthUnit.INCHES),
////                                 LengthUnit.FEET);
//
////                 assertEquals(2.0, result.getValue(), 1e-6);
////         }
//
////         @Test
////         void testConversion_YardsToInches() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
////                                 new Quantity<>(1.0, LengthUnit.YARDS),
////                                 LengthUnit.INCHES);
//
////                 assertEquals(36.0, result.getValue(), 1e-6);
////         }
//
////         @Test
////         void testConversion_InchesToYards() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
////                                 new Quantity<>(72.0, LengthUnit.INCHES),
////                                 LengthUnit.YARDS);
//
////                 assertEquals(2.0, result.getValue(), 1e-6);
////         }
//
////         @Test
////         void testConversion_CentimetersToInches() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(
////                                 new Quantity<>(2.54, LengthUnit.CENTIMETERS),
////                                 LengthUnit.INCHES);
//
////                 assertEquals(1.0, result.getValue(), 1e-6);
////         }
//
////         // ================= UC6 Addition =================
//
////         @Test
////         public void testAddition_SameUnit_FeetPlusFeet() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 new Quantity<>(2.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 new Quantity<>(3.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         @Test
////         public void testAddition_CrossUnit_FeetPlusInches() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 new Quantity<>(12.0, LengthUnit.INCHES));
//
////                 assertEquals(
////                                 new Quantity<>(2.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         @Test
////         public void testAddition_CrossUnit_InchPlusFeet() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(12.0, LengthUnit.INCHES),
////                                 new Quantity<>(1.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 new Quantity<>(24.0, LengthUnit.INCHES),
////                                 result);
////         }
//
////         @Test
////         public void testAddition_WithZero() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(5.0, LengthUnit.FEET),
////                                 new Quantity<>(0.0, LengthUnit.INCHES));
//
////                 assertEquals(
////                                 new Quantity<>(5.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         // ================= UC7 Addition With Target Unit =================
//
////         @Test
////         public void testUC7_Addition_ResultInFeet() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 new Quantity<>(12.0, LengthUnit.INCHES),
////                                 LengthUnit.FEET);
//
////                 assertEquals(
////                                 new Quantity<>(2.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         @Test
////         public void testUC7_Addition_ResultInInches() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 new Quantity<>(12.0, LengthUnit.INCHES),
////                                 LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Quantity<>(24.0, LengthUnit.INCHES),
////                                 result);
////         }
//
////         @Test
////         public void testUC7_Addition_ResultInYards() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.FEET),
////                                 new Quantity<>(12.0, LengthUnit.INCHES),
////                                 LengthUnit.YARDS);
//
////                 assertEquals(
////                                 0.667,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC7_Addition_ResultInCentimeters() {
//
////                 Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateAddition(
////                                 new Quantity<>(1.0, LengthUnit.INCHES),
////                                 new Quantity<>(1.0, LengthUnit.INCHES),
////                                 LengthUnit.CENTIMETERS);
//
////                 assertEquals(
////                                 5.08,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         // 1
////         @Test
////         public void testLengthUnitEnum_FeetConstant() {
//
////                 assertEquals(
////                                 1.0,
////                                 LengthUnit.FEET.getConversionFactor());
////         }
//
////         // 2
////         @Test
////         public void testLengthUnitEnum_InchesConstant() {
//
////                 assertEquals(
////                                 1.0 / 12,
////                                 LengthUnit.INCHES.getConversionFactor(),
////                                 1e-2);
////         }
//
////         // 3
////         @Test
////         public void testLengthUnitEnum_YardsConstant() {
//
////                 assertEquals(
////                                 3.0,
////                                 LengthUnit.YARDS.getConversionFactor());
////         }
//
////         // 4
////         @Test
////         public void testLengthUnitEnum_CentimetersConstant() {
//
////                 assertEquals(
////                                 1.0 / 30.48,
////                                 LengthUnit.CENTIMETERS.getConversionFactor(),
////                                 1e-2);
////         }
//
////         // 5
////         @Test
////         public void testConvertToBaseUnit_FeetToFeet() {
//
////                 assertEquals(
////                                 5.0,
////                                 LengthUnit.FEET.convertToBaseUnit(5.0),
////                                 1e-2);
////         }
//
////         // 6
////         @Test
////         public void testConvertToBaseUnit_InchesToFeet() {
//
////                 assertEquals(
////                                 1.0,
////                                 LengthUnit.INCHES.convertToBaseUnit(12.0),
////                                 1e-2);
////         }
//
////         // 7
////         @Test
////         public void testConvertToBaseUnit_YardsToFeet() {
//
////                 assertEquals(
////                                 3.0,
////                                 LengthUnit.YARDS.convertToBaseUnit(1.0),
////                                 1e-2);
////         }
//
////         // 8
////         @Test
////         public void testConvertToBaseUnit_CentimetersToFeet() {
//
////                 assertEquals(
////                                 1.0,
////                                 LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
////                                 1e-2);
////         }
//
////         // 9
////         @Test
////         public void testConvertFromBaseUnit_FeetToFeet() {
//
////                 assertEquals(
////                                 2.0,
////                                 LengthUnit.FEET.convertFromBaseUnit(2.0),
////                                 1e-2);
////         }
//
////         // 10
////         @Test
////         public void testConvertFromBaseUnit_FeetToInches() {
//
////                 assertEquals(
////                                 12.0,
////                                 LengthUnit.INCHES.convertFromBaseUnit(1.0),
////                                 1e-2);
////         }
//
////         // 11
////         @Test
////         public void testConvertFromBaseUnit_FeetToYards() {
//
////                 assertEquals(
////                                 1.0,
////                                 LengthUnit.YARDS.convertFromBaseUnit(3.0),
////                                 1e-2);
////         }
//
////         // 12
////         @Test
////         public void testConvertFromBaseUnit_FeetToCentimeters() {
//
////                 assertEquals(
////                                 30.48,
////                                 LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
////                                 1e-2);
////         }
//
////         // 13
////         @Test
////         public void testQuantityLengthRefactored_Equality() {
//
////                 Length length1 = new Length(1.0, LengthUnit.FEET);
//
////                 Length length2 = new Length(12.0, LengthUnit.INCHES);
//
////                 assertTrue(length1.equals(length2));
////         }
//
////         // 14
////         @Test
////         public void testQuantityLengthRefactored_ConvertTo() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .convertTo(LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Length(12.0, LengthUnit.INCHES),
////                                 result);
////         }
//
////         // 15
////         @Test
////         public void testQuantityLengthRefactored_Add() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .add(
////                                                 new Length(
////                                                                 12.0,
////                                                                 LengthUnit.INCHES),
////                                                 LengthUnit.FEET);
//
////                 assertEquals(
////                                 new Length(2.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         // 16
////         @Test
////         public void testQuantityLengthRefactored_AddWithTargetUnit() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .add(
////                                                 new Length(
////                                                                 12.0,
////                                                                 LengthUnit.INCHES),
////                                                 LengthUnit.YARDS);
//
////                 assertEquals(
////                                 0.667,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         // 17
////         @Test
////         public void testQuantityLengthRefactored_NullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Length(1.0, null));
////         }
//
////         // 18
////         @Test
////         public void testQuantityLengthRefactored_InvalidValue() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Length(
////                                                 Double.NaN,
////                                                 LengthUnit.FEET));
////         }
//
////         // 19
////         @Test
////         public void testBackwardCompatibility_UC1EqualityTests() {
//
////                 Length length1 = new Length(1.0, LengthUnit.FEET);
//
////                 Length length2 = new Length(12.0, LengthUnit.INCHES);
//
////                 assertTrue(length1.equals(length2));
////         }
//
////         // 20
////         @Test
////         public void testBackwardCompatibility_UC5ConversionTests() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .convertTo(LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Length(12.0, LengthUnit.INCHES),
////                                 result);
////         }
//
////         // 21
////         @Test
////         public void testBackwardCompatibility_UC6AdditionTests() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .add(
////                                                 new Length(
////                                                                 12.0,
////                                                                 LengthUnit.INCHES));
//
////                 assertEquals(
////                                 new Length(2.0, LengthUnit.FEET),
////                                 result);
////         }
//
////         // 22
////         @Test
////         public void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
//
////                 Length result = new Length(1.0, LengthUnit.FEET)
////                                 .add(
////                                                 new Length(
////                                                                 12.0,
////                                                                 LengthUnit.INCHES),
////                                                 LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Length(24.0, LengthUnit.INCHES),
////                                 result);
////         }
//
////         // 23
////         @Test
////         public void testArchitecturalScalability_MultipleCategories() {
//
////                 assertNotNull(LengthUnit.FEET);
//
////                 assertTrue(true);
////         }
//
////         // 24
////         @Test
////         public void testRoundTripConversion_RefactoredDesign() {
//
////                 Length original = new Length(5.0, LengthUnit.FEET);
//
////                 Length converted = original.convertTo(LengthUnit.INCHES);
//
////                 Length back = converted.convertTo(LengthUnit.FEET);
//
////                 assertEquals(
////                                 original.getValue(),
////                                 back.getValue(),
////                                 1e-2);
////         }
//
////         // 25
////         @Test
////         public void testUnitImmutability() {
//
////                 LengthUnit unit = LengthUnit.FEET;
//
////                 assertEquals(
////                                 1.0,
////                                 unit.getConversionFactor());
////         }
////         // uc9
////         // ========================= UC9 TEST CASES =========================
//
////         @Test
////         public void testUC9_Equality_KilogramToKilogram_SameValue() {
//
////                 Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(1.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_KilogramToKilogram_DifferentValue() {
//
////                 Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(2.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(false, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_KilogramToGram_EquivalentValue() {
//
////                 Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(1000.0, WeightUnit.GRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_GramToKilogram_EquivalentValue() {
//
////                 Weight weight1 = new Weight(1000.0, WeightUnit.GRAM);
////                 Weight weight2 = new Weight(1.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_WeightVsLength_Incompatible() {
//
////                 Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
////                 Length length = new Length(1.0, LengthUnit.FEET);
//
////                 assertEquals(false, weight.equals(length));
////         }
//
////         @Test
////         public void testUC9_Equality_NullComparison() {
//
////                 Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(false, weight.equals(null));
////         }
//
////         @Test
////         public void testUC9_Equality_SameReference() {
//
////                 Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(true, weight.equals(weight));
////         }
//
////         @Test
////         public void testUC9_Equality_NullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Weight(1.0, null));
////         }
//
////         @Test
////         public void testUC9_Equality_TransitiveProperty() {
//
////                 Weight weight1 = new Weight(1.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(1000.0, WeightUnit.GRAM);
////                 Weight weight3 = new Weight(1.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////                 assertEquals(true, weight2.equals(weight3));
////                 assertEquals(true, weight1.equals(weight3));
////         }
//
////         @Test
////         public void testUC9_Equality_ZeroValue() {
//
////                 Weight weight1 = new Weight(0.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(0.0, WeightUnit.GRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_NegativeWeight() {
//
////                 Weight weight1 = new Weight(-1.0, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(-1000.0, WeightUnit.GRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_LargeWeightValue() {
//
////                 Weight weight1 = new Weight(1000000.0, WeightUnit.GRAM);
////                 Weight weight2 = new Weight(1000.0, WeightUnit.KILOGRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Equality_SmallWeightValue() {
//
////                 Weight weight1 = new Weight(0.001, WeightUnit.KILOGRAM);
////                 Weight weight2 = new Weight(1.0, WeightUnit.GRAM);
//
////                 assertEquals(true, weight1.equals(weight2));
////         }
//
////         @Test
////         public void testUC9_Conversion_PoundToKilogram() {
//
////                 Weight converted = new Weight(2.20462, WeightUnit.POUND)
////                                 .convertTo(WeightUnit.KILOGRAM);
//
////                 assertEquals(
////                                 1.0,
////                                 converted.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Conversion_KilogramToPound() {
//
////                 Weight converted = new Weight(1.0, WeightUnit.KILOGRAM)
////                                 .convertTo(WeightUnit.POUND);
//
////                 assertEquals(
////                                 2.20462,
////                                 converted.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Conversion_SameUnit() {
//
////                 Weight converted = new Weight(5.0, WeightUnit.KILOGRAM)
////                                 .convertTo(WeightUnit.KILOGRAM);
//
////                 assertEquals(
////                                 5.0,
////                                 converted.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Conversion_ZeroValue() {
//
////                 Weight converted = new Weight(0.0, WeightUnit.KILOGRAM)
////                                 .convertTo(WeightUnit.GRAM);
//
////                 assertEquals(
////                                 0.0,
////                                 converted.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Conversion_NegativeValue() {
//
////                 Weight converted = new Weight(-1.0, WeightUnit.KILOGRAM)
////                                 .convertTo(WeightUnit.GRAM);
//
////                 assertEquals(
////                                 -1000.0,
////                                 converted.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Conversion_RoundTrip() {
//
////                 Weight original = new Weight(1.5, WeightUnit.KILOGRAM);
//
////                 Weight roundTrip = original.convertTo(WeightUnit.GRAM)
////                                 .convertTo(WeightUnit.KILOGRAM);
//
////                 assertEquals(
////                                 1.5,
////                                 roundTrip.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_SameUnit_KilogramPlusKilogram() {
//
////                 Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
////                                 .add(new Weight(2.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(
////                                 3.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_CrossUnit_KilogramPlusGram() {
//
////                 Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
////                                 .add(new Weight(1000.0, WeightUnit.GRAM));
//
////                 assertEquals(
////                                 2.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_CrossUnit_PoundPlusKilogram() {
//
////                 Weight result = new Weight(2.20462, WeightUnit.POUND)
////                                 .add(new Weight(1.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(
////                                 4.40924,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_ExplicitTargetUnit_Kilogram() {
//
////                 Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
////                                 .add(
////                                                 new Weight(1000.0, WeightUnit.GRAM),
////                                                 WeightUnit.GRAM);
//
////                 assertEquals(
////                                 2000.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_Commutativity() {
//
////                 Weight result1 = new Weight(1.0, WeightUnit.KILOGRAM)
////                                 .add(new Weight(1000.0, WeightUnit.GRAM));
//
////                 Weight result2 = new Weight(1000.0, WeightUnit.GRAM)
////                                 .add(new Weight(1.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(
////                                 result1.convertTo(WeightUnit.KILOGRAM).getValue(),
////                                 result2.convertTo(WeightUnit.KILOGRAM).getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_WithZero() {
//
////                 Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
////                                 .add(new Weight(0.0, WeightUnit.GRAM));
//
////                 assertEquals(
////                                 5.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_NegativeValues() {
//
////                 Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
////                                 .add(new Weight(-2000.0, WeightUnit.GRAM));
//
////                 assertEquals(
////                                 3.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_LargeValues() {
//
////                 Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
////                                 .add(new Weight(1e6, WeightUnit.KILOGRAM));
//
////                 assertEquals(
////                                 2e6,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         public void testUC9_Addition_ExplicitTargetUnit_Pound() {
//
////                 Weight result = new Weight(1.0, WeightUnit.POUND)
////                                 .add(new Weight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
//
////                 assertEquals(
////                                 2.0,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         void testIMeasurableInterface_LengthUnitImplementation() {
//
////                 assertTrue(LengthUnit.FEET instanceof IMeasurable);
////         }
//
////         @Test
////         void testIMeasurableInterface_WeightUnitImplementation() {
//
////                 assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
////         }
//
////         @Test
////         void testIMeasurableInterface_ConsistentBehavior() {
//
////                 assertEquals(
////                                 1.0,
////                                 LengthUnit.FEET.convertToBaseUnit(1.0));
//
////                 assertEquals(
////                                 1.0,
////                                 WeightUnit.KILOGRAM.convertToBaseUnit(1.0));
////         }
//
////         // =========================================================
////         // Generic Quantity Equality Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_LengthOperations_Equality() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 assertEquals(feet, inches);
////         }
//
////         @Test
////         void testGenericQuantity_WeightOperations_Equality() {
//
////                 Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
//
////                 assertEquals(kilogram, gram);
////         }
//
////         // =========================================================
////         // Conversion Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_LengthOperations_Conversion() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Quantity<>(12.0,
////                                                 LengthUnit.INCHES),
////                                 result);
////         }
//
////         @Test
////         void testGenericQuantity_WeightOperations_Conversion() {
//
////                 Quantity<WeightUnit> kilogram = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> result = kilogram.convertTo(WeightUnit.GRAM);
//
////                 assertEquals(
////                                 new Quantity<>(1000.0,
////                                                 WeightUnit.GRAM),
////                                 result);
////         }
//
////         // =========================================================
////         // Addition Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_LengthOperations_Addition() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0,
////                                 LengthUnit.INCHES);
//
////                 Quantity<LengthUnit> result = feet.add(inches,
////                                 LengthUnit.FEET);
//
////                 assertEquals(
////                                 new Quantity<>(2.0,
////                                                 LengthUnit.FEET),
////                                 result);
////         }
//
////         @Test
////         void testGenericQuantity_WeightOperations_Addition() {
//
////                 Quantity<WeightUnit> kilogram = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 Quantity<WeightUnit> result = kilogram.add(
////                                 gram,
////                                 WeightUnit.KILOGRAM);
//
////                 assertEquals(
////                                 new Quantity<>(2.0,
////                                                 WeightUnit.KILOGRAM),
////                                 result);
////         }
//
////         // =========================================================
////         // Cross Category Tests
////         // =========================================================
//
////         @Test
////         void testCrossCategoryPrevention_LengthVsWeight() {
//
////                 Quantity<?> length = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<?> weight = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 assertNotEquals(length, weight);
////         }
//
////         @Test
////         void testCrossCategoryPrevention_CompilerTypeSafety() {
//
////                 Quantity<LengthUnit> length = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 assertNotNull(length);
////         }
//
////         // =========================================================
////         // Constructor Validation Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_ConstructorValidation_NullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(1.0, null));
////         }
//
////         @Test
////         void testGenericQuantity_ConstructorValidation_InvalidValue() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(
////                                                 Double.NaN,
////                                                 LengthUnit.FEET));
////         }
//
////         // =========================================================
////         // All Unit Combination Conversion Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_Conversion_AllUnitCombinations() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> yards = feet.convertTo(LengthUnit.YARDS);
//
////                 assertEquals(
////                                 new Quantity<>(0.33,
////                                                 LengthUnit.YARDS),
////                                 yards);
////         }
//
////         // =========================================================
////         // Addition Combination Tests
////         // =========================================================
//
////         @Test
////         void testGenericQuantity_Addition_AllUnitCombinations() {
//
////                 Quantity<WeightUnit> pound = new Quantity<>(1.0,
////                                 WeightUnit.POUND);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(453.592,
////                                 WeightUnit.GRAM);
//
////                 Quantity<WeightUnit> result = pound.add(
////                                 gram,
////                                 WeightUnit.POUND);
//
////                 assertEquals(
////                                 new Quantity<>(2.0,
////                                                 WeightUnit.POUND),
////                                 result);
////         }
//
////         // =========================================================
////         // Backward Compatibility Test
////         // =========================================================
//
////         @Test
////         void testBackwardCompatibility_AllUC1Through9Tests() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0,
////                                 LengthUnit.INCHES);
//
////                 assertEquals(feet, inches);
////         }
//
////         // =========================================================
////         // QuantityMeasurementApp Tests
////         // =========================================================
//
////         @Test
////         void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 assertTrue(
////                                 QuantityMeasurementApp
////                                                 .demonstrateEquality(
////                                                                 kg,
////                                                                 gram));
////         }
//
////         @Test
////         void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> result = QuantityMeasurementApp
////                                 .demonstrateConversion(
////                                                 kg,
////                                                 WeightUnit.GRAM);
//
////                 assertEquals(
////                                 new Quantity<>(1000.0,
////                                                 WeightUnit.GRAM),
////                                 result);
////         }
//
////         @Test
////         void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 Quantity<WeightUnit> result = QuantityMeasurementApp
////                                 .demonstrateAddition(
////                                                 kg,
////                                                 gram,
////                                                 WeightUnit.KILOGRAM);
//
////                 assertEquals(
////                                 new Quantity<>(2.0,
////                                                 WeightUnit.KILOGRAM),
////                                 result);
////         }
//
////         // =========================================================
////         // Wildcard Tests
////         // =========================================================
//
////         @Test
////         void testTypeWildcard_FlexibleSignatures() {
//
////                 Quantity<?> quantity = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Scalability Tests
////         // =========================================================
//
////         @Test
////         void testScalability_NewUnitEnumIntegration() {
//
////                 Quantity<LengthUnit> quantity = new Quantity<>(2.0,
////                                 LengthUnit.YARDS);
//
////                 assertNotNull(quantity);
////         }
//
////         @Test
////         void testScalability_MultipleNewCategories() {
//
////                 Quantity<WeightUnit> quantity = new Quantity<>(5.0,
////                                 WeightUnit.GRAM);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Generic Bound Test
////         // =========================================================
//
////         @Test
////         void testGenericBoundedTypeParameter_Enforcement() {
//
////                 Quantity<LengthUnit> quantity = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // hashCode Tests
////         // =========================================================
//
////         @Test
////         void testHashCode_GenericQuantity_Consistency() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 assertEquals(
////                                 kg.hashCode(),
////                                 gram.hashCode());
////         }
//
////         // =========================================================
////         // equals Contract Tests
////         // =========================================================
//
////         @Test
////         void testEquals_GenericQuantity_ContractPreservation() {
//
////                 Quantity<WeightUnit> a = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> b = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 Quantity<WeightUnit> c = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 assertEquals(a, b);
////                 assertEquals(b, c);
////                 assertEquals(a, c);
////         }
//
////         // =========================================================
////         // Enum Behavior Tests
////         // =========================================================
//
////         @Test
////         void testEnumAsUnitCarrier_BehaviorEncapsulation() {
//
////                 double feet = LengthUnit.FEET
////                                 .convertToBaseUnit(1.0);
//
////                 // FEET is base unit now
////                 assertEquals(1.0, feet);
////         }
//
////         // =========================================================
////         // Runtime Safety Tests
////         // =========================================================
//
////         @Test
////         void testTypeErasure_RuntimeSafety() {
//
////                 Quantity<?> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<?> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 assertNotEquals(feet, kg);
////         }
//
////         // =========================================================
////         // Composition Tests
////         // =========================================================
//
////         @Test
////         void testCompositionOverInheritance_Flexibility() {
//
////                 Quantity<WeightUnit> quantity = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // DRY Validation
////         // =========================================================
//
////         @Test
////         void testCodeReduction_DRYValidation() {
//
////                 Quantity<LengthUnit> quantity = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Maintainability Tests
////         // =========================================================
//
////         @Test
////         void testMaintainability_SingleSourceOfTruth() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0,
////                                 LengthUnit.INCHES);
//
////                 assertEquals(feet, inches);
////         }
//
////         // =========================================================
////         // Architectural Readiness Tests
////         // =========================================================
//
////         @Test
////         void testArchitecturalReadiness_MultipleNewCategories() {
//
////                 Quantity<WeightUnit> quantity = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Performance Tests
////         // =========================================================
//
////         @Test
////         void testPerformance_GenericOverhead() {
//
////                 Quantity<LengthUnit> quantity = new Quantity<>(10.0,
////                                 LengthUnit.FEET);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Documentation Tests
////         // =========================================================
//
////         @Test
////         void testDocumentation_PatternClarity() {
//
////                 Quantity<LengthUnit> quantity = new Quantity<>(5.0,
////                                 LengthUnit.YARDS);
//
////                 assertNotNull(quantity);
////         }
//
////         // =========================================================
////         // Interface Segregation Tests
////         // =========================================================
//
////         @Test
////         void testInterfaceSegregation_MinimalContract() {
//
////                 assertEquals(
////                                 "FEET",
////                                 LengthUnit.FEET.getUnitName());
////         }
//
////         // =========================================================
////         // Immutability Tests
////         // =========================================================
//
////         @Test
////         void testImmutability_GenericQuantity() {
//
////                 Quantity<WeightUnit> quantity = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> result = quantity.convertTo(WeightUnit.GRAM);
//
////                 assertNotSame(quantity, result);
////         }
//
////         @Test
////         void testGenericQuantity_Conversion_RoundTrip() {
//
////                 Quantity<LengthUnit> original = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> converted = original.convertTo(LengthUnit.INCHES);
//
////                 Quantity<LengthUnit> roundTrip = converted.convertTo(LengthUnit.FEET);
//
////                 assertEquals(original, roundTrip);
////         }
//
////         @Test
////         void testGenericQuantity_Addition_WithExplicitTargetUnit() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0,
////                                 LengthUnit.INCHES);
//
////                 Quantity<LengthUnit> result = feet.add(
////                                 inches,
////                                 LengthUnit.YARDS);
//
////                 assertEquals(
////                                 0.67,
////                                 result.getValue(),
////                                 1e-2);
////         }
//
////         @Test
////         void testGenericQuantity_Equality_SymmetricProperty() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 assertTrue(kg.equals(gram));
////                 assertTrue(gram.equals(kg));
////         }
//
////         @Test
////         void testGenericQuantity_Addition_Commutativity() {
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0,
////                                 WeightUnit.GRAM);
//
////                 Quantity<WeightUnit> result1 = kg.add(gram,
////                                 WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> result2 = gram.add(kg,
////                                 WeightUnit.GRAM);
//
////                 assertEquals(2.0,
////                                 result1.getValue(),
////                                 1e-6);
//
////                 assertEquals(2000.0,
////                                 result2.getValue(),
////                                 1e-6);
////         }
//
////         @Test
////         void testGenericQuantity_ZeroValueEquality() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(0.0,
////                                 LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(0.0,
////                                 LengthUnit.INCHES);
//
////                 assertEquals(feet, inches);
////         }
////         // =========================
////         // VOLUME EQUALITY TESTS
////         // =========================
//
////         @Test
////         public void testEquality_LitreToLitre_SameValue() {
//
////                 Quantity<VolumeUnit> litre1 = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> litre2 = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 assertTrue(litre1.equals(litre2));
////         }
//
////         @Test
////         public void testEquality_LitreToLitre_DifferentValue() {
//
////                 Quantity<VolumeUnit> litre1 = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);
//
////                 assertFalse(litre1.equals(litre2));
////         }
//
////         @Test
////         public void testEquality_LitreToMillilitre_EquivalentValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(litre.equals(milliLitre));
////         }
//
////         @Test
////         public void testEquality_MillilitreToLitre_EquivalentValue() {
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 assertTrue(milliLitre.equals(litre));
////         }
//
////         @Test
////         public void testEquality_LitreToGallon_EquivalentValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
//
////                 assertTrue(litre.equals(gallon));
////         }
//
////         @Test
////         public void testEquality_GallonToLitre_EquivalentValue() {
//
////                 Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
//
////                 assertTrue(gallon.equals(litre));
////         }
//
////         @Test
////         public void testEquality_VolumeVsLength_Incompatible() {
//
////                 Quantity<?> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<?> length = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 assertFalse(volume.equals(length));
////         }
//
////         @Test
////         public void testEquality_VolumeVsWeight_Incompatible() {
//
////                 Quantity<?> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<?> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
//
////                 assertFalse(volume.equals(weight));
////         }
//
////         @Test
////         public void testEquality_NullComparison() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 assertFalse(litre.equals(null));
////         }
//
////         @Test
////         public void testEquality_SameReference() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 assertTrue(litre.equals(litre));
////         }
//
////         @Test
////         public void testEquality_NullUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(1.0, null));
////         }
//
////         @Test
////         public void testEquality_TransitiveProperty() {
//
////                 Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> c = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 assertTrue(a.equals(b));
////                 assertTrue(b.equals(c));
////                 assertTrue(a.equals(c));
////         }
//
////         @Test
////         public void testEquality_ZeroValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(0.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(litre.equals(milliLitre));
////         }
//
////         @Test
////         public void testEquality_NegativeVolume() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(-1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(litre.equals(milliLitre));
////         }
//
////         @Test
////         public void testEquality_LargeVolumeValue() {
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1000.0, VolumeUnit.LITRE);
//
////                 assertTrue(milliLitre.equals(litre));
////         }
//
////         @Test
////         public void testEquality_SmallVolumeValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(0.001, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(litre.equals(milliLitre));
////         }
//
////         // =========================
////         // VOLUME CONVERSION TESTS
////         // =========================
//
////         @Test
////         public void testConversion_LitreToMillilitre() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
//
////                 assertEquals(1000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_MillilitreToLitre() {
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = milliLitre.convertTo(VolumeUnit.LITRE);
//
////                 assertEquals(1.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_GallonToLitre() {
//
////                 Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
//
////                 Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
//
////                 assertEquals(3.78541, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_LitreToGallon() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.GALLON);
//
////                 assertEquals(1.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_MillilitreToGallon() {
//
////                 Quantity<VolumeUnit> milliLitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = milliLitre.convertTo(VolumeUnit.GALLON);
//
////                 assertEquals(0.264172, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_SameUnit() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.LITRE);
//
////                 assertEquals(5.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_ZeroValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(0.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
//
////                 assertEquals(0.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_NegativeValue() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(-1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
//
////                 assertEquals(-1000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testConversion_RoundTrip() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.5, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE)
////                                 .convertTo(VolumeUnit.LITRE);
//
////                 assertEquals(1.5, result.getValue(), 0.01);
////         }
//
////         // =========================
////         // VOLUME ADDITION TESTS
////         // =========================
//
////         @Test
////         public void testAddition_SameUnit_LitrePlusLitre() {
//
////                 Quantity<VolumeUnit> litre1 = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre1.add(litre2);
//
////                 assertEquals(3.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_SameUnit_MillilitrePlusMillilitre() {
//
////                 Quantity<VolumeUnit> ml1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> ml2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = ml1.add(ml2);
//
////                 assertEquals(1000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_CrossUnit_LitrePlusMillilitre() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = litre.add(ml);
//
////                 assertEquals(2.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_CrossUnit_MillilitrePlusLitre() {
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = ml.add(litre);
//
////                 assertEquals(2000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_CrossUnit_GallonPlusLitre() {
//
////                 Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = gallon.add(litre);
//
////                 assertEquals(2.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_ExplicitTargetUnit_Litre() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);
//
////                 assertEquals(2.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_ExplicitTargetUnit_Millilitre() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.MILLILITRE);
//
////                 assertEquals(2000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_ExplicitTargetUnit_Gallon() {
//
////                 Quantity<VolumeUnit> litre1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> litre2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre1.add(litre2, VolumeUnit.GALLON);
//
////                 assertEquals(2.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_Commutativity() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(
////                                 litre.add(ml).equals(
////                                                 ml.add(litre)));
////         }
//
////         @Test
////         public void testAddition_WithZero_volume() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = litre.add(zero);
//
////                 assertEquals(5.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_NegativeValues() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> negative = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
//
////                 Quantity<VolumeUnit> result = litre.add(negative);
//
////                 assertEquals(3.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testAddition_LargeValues() {
//
////                 Quantity<VolumeUnit> litre1 = new Quantity<>(1e6, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> litre2 = new Quantity<>(1e6, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> result = litre1.add(litre2);
//
////                 assertEquals(2e6, result.getValue(), 0.01);
////         }
//
////         // =========================
////         // VOLUME UNIT ENUM TESTS
////         // =========================
//
////         @Test
////         public void testVolumeUnitEnum_LitreConstant() {
//
////                 assertEquals(
////                                 1.0,
////                                 VolumeUnit.LITRE.getConversionFactor(),
////                                 0.01);
////         }
//
////         @Test
////         public void testVolumeUnitEnum_MillilitreConstant() {
//
////                 assertEquals(
////                                 0.001,
////                                 VolumeUnit.MILLILITRE.getConversionFactor(),
////                                 0.001);
////         }
//
////         @Test
////         public void testVolumeUnitEnum_GallonConstant() {
//
////                 assertEquals(
////                                 3.78541,
////                                 VolumeUnit.GALLON.getConversionFactor(),
////                                 0.01);
////         }
//
////         // =========================
////         // BASE UNIT TESTS
////         // =========================
//
////         @Test
////         public void testConvertToBaseUnit_LitreToLitre() {
//
////                 assertEquals(
////                                 5.0,
////                                 VolumeUnit.LITRE.convertToBaseUnit(5.0),
////                                 0.01);
////         }
//
////         @Test
////         public void testConvertToBaseUnit_MillilitreToLitre() {
//
////                 assertEquals(
////                                 1.0,
////                                 VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0),
////                                 0.01);
////         }
//
////         @Test
////         public void testConvertToBaseUnit_GallonToLitre() {
//
////                 assertEquals(
////                                 3.78541,
////                                 VolumeUnit.GALLON.convertToBaseUnit(1.0),
////                                 0.01);
////         }
//
////         @Test
////         public void testConvertFromBaseUnit_LitreToLitre() {
//
////                 assertEquals(
////                                 2.0,
////                                 VolumeUnit.LITRE.convertFromBaseUnit(2.0),
////                                 0.01);
////         }
//
////         @Test
////         public void testConvertFromBaseUnit_LitreToMillilitre() {
//
////                 assertEquals(
////                                 1000.0,
////                                 VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0),
////                                 0.01);
////         }
//
////         @Test
////         public void testConvertFromBaseUnit_LitreToGallon() {
//
////                 assertEquals(
////                                 1.0,
////                                 VolumeUnit.GALLON.convertFromBaseUnit(3.78541),
////                                 0.01);
////         }
//
////         // =========================
////         // GENERIC / SCALABILITY TESTS
////         // =========================
//
////         @Test
////         public void testBackwardCompatibility_AllUC1Through10Tests() {
//
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(feet.equals(inches));
////                 assertTrue(kg.equals(gram));
////                 assertTrue(litre.equals(ml));
////         }
//
////         @Test
////         public void testGenericQuantity_VolumeOperations_Consistency() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 assertTrue(litre.equals(ml));
//
////                 assertEquals(
////                                 1000.0,
////                                 litre.convertTo(VolumeUnit.MILLILITRE).getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testScalability_VolumeIntegration() {
//
////                 Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
//
////                 assertTrue(litre.equals(gallon));
////         }
//
////         @Test
////         public void testSubtraction_SameUnit_FeetMinusFeet() {
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(new Quantity<>(5.0, LengthUnit.FEET));
//
////                 assertEquals(5.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_CrossUnit_FeetMinusInches() {
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
//
////                 assertEquals(9.5, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_ResultingInNegative() {
////                 Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
////                                 .subtract(new Quantity<>(10.0, LengthUnit.FEET));
//
////                 assertEquals(-5.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_ResultingInZero() {
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(new Quantity<>(120.0, LengthUnit.INCHES));
//
////                 assertEquals(0.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_WithZeroOperand() {
////                 Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
////                                 .subtract(new Quantity<>(0.0, LengthUnit.INCHES));
//
////                 assertEquals(5.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testDivision_SameUnit_FeetDividedByFeet() {
////                 double result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .divide(new Quantity<>(2.0, LengthUnit.FEET));
//
////                 assertEquals(5.0, result, 0.01);
////         }
//
////         @Test
////         public void testDivision_CrossUnit_FeetDividedByInches() {
////                 double result = new Quantity<>(24.0, LengthUnit.INCHES)
////                                 .divide(new Quantity<>(2.0, LengthUnit.FEET));
//
////                 assertEquals(1.0, result, 0.01);
////         }
//
////         @Test
////         public void testDivision_ByZero() {
////                 assertThrows(
////                                 ArithmeticException.class,
////                                 () -> new Quantity<>(10.0, LengthUnit.FEET)
////                                                 .divide(new Quantity<>(0.0, LengthUnit.FEET)));
////         }
//
////         @Test
////         public void testSubtraction_NullOperand() {
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(10.0, LengthUnit.FEET)
////                                                 .subtract(null));
////         }
//
////         @Test
////         public void testDivision_NullOperand() {
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(10.0, LengthUnit.FEET)
////                                                 .divide(null));
////         }
//
////         @Test
////         public void testSubtraction_SameUnit_LitreMinusLitre() {
////                 Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE)
////                                 .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
//
////                 assertEquals(7.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testDivision_SameUnit_LitreDividedByLitre() {
////                 double result = new Quantity<>(10.0, VolumeUnit.LITRE)
////                                 .divide(new Quantity<>(5.0, VolumeUnit.LITRE));
//
////                 assertEquals(2.0, result, 0.01);
////         }
//
////         @Test
////         public void testSubtraction_ExplicitTargetUnit_Millilitre() {
////                 Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
////                                 .subtract(
////                                                 new Quantity<>(2.0, VolumeUnit.LITRE),
////                                                 VolumeUnit.MILLILITRE);
//
////                 assertEquals(3000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testDivision_CrossUnit_KilogramDividedByGram() {
////                 double result = new Quantity<>(2.0, WeightUnit.KILOGRAM)
////                                 .divide(new Quantity<>(2000.0, WeightUnit.GRAM));
//
////                 assertEquals(1.0, result, 0.01);
////         }
//
////         @Test
////         public void testSubtraction_WithLargeValues() {
////                 Quantity<WeightUnit> result = new Quantity<>(1000000.0, WeightUnit.KILOGRAM)
////                                 .subtract(
////                                                 new Quantity<>(500000.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(500000.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_ExplicitTargetUnit_Feet() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(6.0, LengthUnit.INCHES),
////                                                 LengthUnit.FEET);
//
////                 assertEquals(9.5, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_ExplicitTargetUnit_Inches() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(6.0, LengthUnit.INCHES),
////                                                 LengthUnit.INCHES);
//
////                 assertEquals(114.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_WithNegativeValues() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(-2.0, LengthUnit.FEET));
//
////                 assertEquals(7.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtraction_NonCommutative() {
//
////                 Quantity<LengthUnit> result1 = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 Quantity<LengthUnit> result2 = new Quantity<>(5.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(10.0, LengthUnit.FEET));
//
////                 assertNotEquals(
////                                 result1.getValue(),
////                                 result2.getValue());
////         }
//
////         @Test
////         public void testSubtraction_WithLargeValue() {
//
////                 Quantity<WeightUnit> result = new Quantity<>(1000000.0, WeightUnit.KILOGRAM)
////                                 .subtract(
////                                                 new Quantity<>(500000.0,
////                                                                 WeightUnit.KILOGRAM));
//
////                 assertEquals(
////                                 500000.0,
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testSubtraction_WithSmallValues() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(0.001, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(0.0005,
////                                                                 LengthUnit.FEET));
//
////                 assertEquals(
////                                 0.0005,
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testSubtraction_NullTargetUnit() {
//
////                 assertThrows(
////                                 IllegalArgumentException.class,
////                                 () -> new Quantity<>(10.0,
////                                                 LengthUnit.FEET)
////                                                 .subtract(
////                                                                 new Quantity<>(5.0,
////                                                                                 LengthUnit.FEET),
////                                                                 null));
////         }
//
////         @Test
////         public void testSubtraction_ChainedOperations() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(2.0,
////                                                                 LengthUnit.FEET))
////                                 .subtract(
////                                                 new Quantity<>(1.0,
////                                                                 LengthUnit.FEET));
//
////                 assertEquals(
////                                 7.0,
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testDivision_RatioGreaterThanOne() {
//
////                 double result = new Quantity<>(10.0,
////                                 LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(2.0,
////                                                                 LengthUnit.FEET));
//
////                 assertEquals(
////                                 5.0,
////                                 result,
////                                 0.01);
////         }
//
////         @Test
////         public void testDivision_RatioLessThanOne() {
//
////                 double result = new Quantity<>(5.0,
////                                 LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(10.0,
////                                                                 LengthUnit.FEET));
//
////                 assertEquals(
////                                 0.5,
////                                 result,
////                                 0.01);
////         }
//
////         @Test
////         public void testDivision_RatioEqualToOne() {
//
////                 double result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(10.0, LengthUnit.FEET));
//
////                 assertEquals(1.0, result, 0.01);
////         }
//
////         @Test
////         public void testDivision_NonCommutative() {
//
////                 double result1 = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 double result2 = new Quantity<>(5.0, LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(10.0, LengthUnit.FEET));
//
////                 assertNotEquals(result1, result2);
////         }
//
////         @Test
////         public void testDivision_WithLargeRatio() {
//
////                 double result = new Quantity<>(1000000.0, WeightUnit.KILOGRAM)
////                                 .divide(
////                                                 new Quantity<>(1.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(1000000.0, result, 0.01);
////         }
//
////         @Test
////         public void testDivision_WithSmallRatio() {
//
////                 double result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
////                                 .divide(
////                                                 new Quantity<>(1000000.0, WeightUnit.KILOGRAM));
//
////                 assertEquals(0.000001, result, 1e-6);
////         }
//
////         @Test
////         public void testDivision_CrossCategory() {
//
////                 Quantity<?> length = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<?> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
//
////                 assertFalse(length.equals(weight));
////         }
//
////         @Test
////         public void testDivision_AllMeasurementCategories() {
//
////                 double lengthResult = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 double weightResult = new Quantity<>(10.0, WeightUnit.KILOGRAM)
////                                 .divide(
////                                                 new Quantity<>(5.0, WeightUnit.KILOGRAM));
//
////                 double volumeResult = new Quantity<>(10.0, VolumeUnit.LITRE)
////                                 .divide(
////                                                 new Quantity<>(5.0, VolumeUnit.LITRE));
//
////                 assertEquals(2.0, lengthResult, 0.01);
////                 assertEquals(2.0, weightResult, 0.01);
////                 assertEquals(2.0, volumeResult, 0.01);
////         }
//
////         @Test
////         public void testDivision_Associativity() {
//
////                 double left = (new Quantity<>(12.0, LengthUnit.FEET)
////                                 .divide(new Quantity<>(3.0, LengthUnit.FEET)))
////                                 / 2.0;
//
////                 double right = 12.0 /
////                                 (new Quantity<>(3.0, LengthUnit.FEET)
////                                                 .divide(new Quantity<>(2.0, LengthUnit.FEET)));
//
////                 assertNotEquals(left, right);
////         }
//
////         @Test
////         public void testSubtraction_CrossCategory() {
//
////                 Quantity<?> length = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<?> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
//
////                 assertFalse(length.equals(weight));
////         }
//
////         @Test
////         public void testSubtraction_AllMeasurementCategories() {
//
////                 Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 Quantity<WeightUnit> weight = new Quantity<>(10.0, WeightUnit.KILOGRAM)
////                                 .subtract(
////                                                 new Quantity<>(5.0, WeightUnit.KILOGRAM));
//
////                 Quantity<VolumeUnit> volume = new Quantity<>(10.0, VolumeUnit.LITRE)
////                                 .subtract(
////                                                 new Quantity<>(5.0, VolumeUnit.LITRE));
//
////                 assertEquals(5.0, length.getValue(), 0.01);
////                 assertEquals(5.0, weight.getValue(), 0.01);
////                 assertEquals(5.0, volume.getValue(), 0.01);
////         }
//
////         @Test
////         public void testSubtractionAndDivision_Integration() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(2.0, LengthUnit.FEET));
//
////                 double ratio = result.divide(
////                                 new Quantity<>(4.0, LengthUnit.FEET));
//
////                 assertEquals(2.0, ratio, 0.01);
////         }
//
////         @Test
////         public void testSubtractionAddition_Inverse() {
//
////                 Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> added = original.add(
////                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 Quantity<LengthUnit> result = added.subtract(
////                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 original.getValue(),
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testSubtraction_Immutability() {
//
////                 Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 original.subtract(
////                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 10.0,
////                                 original.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testDivision_Immutability() {
//
////                 Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 original.divide(
////                                 new Quantity<>(5.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 10.0,
////                                 original.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testSubtraction_PrecisionAndRounding() {
//
////                 Quantity<LengthUnit> result = new Quantity<>(1.23, LengthUnit.FEET)
////                                 .subtract(
////                                                 new Quantity<>(0.11, LengthUnit.FEET));
//
////                 assertEquals(
////                                 1.12,
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         public void testDivision_PrecisionHandling() {
//
////                 double result = new Quantity<>(1.0, LengthUnit.FEET)
////                                 .divide(
////                                                 new Quantity<>(3.0, LengthUnit.FEET));
//
////                 assertEquals(
////                                 0.333,
////                                 result,
////                                 0.001);
////         }
//
////         @Test
////         void testValidation_NullOperand_ConsistentAcrossOperations() {
////                 Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> q.add(null));
//
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> q.subtract(null));
//
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> q.divide(null));
////         }
//
////         @Test
////         void testValidation_NullTargetUnit_AddSubtractReject() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
//
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> q1.add(q2, null));
//
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> q1.subtract(q2, null));
////         }
//
////         @Test
////         void testAdd_UC12_BehaviorPreserved() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Quantity<>(2.0, LengthUnit.FEET),
////                                 q1.add(q2));
////         }
//
////         @Test
////         void testSubtract_UC12_BehaviorPreserved() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
//
////                 assertEquals(
////                                 new Quantity<>(9.5, LengthUnit.FEET),
////                                 q1.subtract(q2));
////         }
//
////         @Test
////         void testDivide_UC12_BehaviorPreserved() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
//
////                 assertEquals(
////                                 1.0,
////                                 q1.divide(q2),
////                                 0.01);
////         }
//
////         @Test
////         void testRounding_AddSubtract_TwoDecimalPlaces() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(1.11, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(0.22, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> result = q1.add(q2);
//
////                 assertEquals(
////                                 1.33,
////                                 result.getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testRounding_Divide_NoRounding() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
//
////                 assertEquals(
////                                 3.3333,
////                                 q1.divide(q2),
////                                 0.001);
////         }
//
////         @Test
////         void testImplicitTargetUnit_AddSubtract() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 assertEquals(
////                                 LengthUnit.FEET,
////                                 q1.add(q2).getUnit());
//
////                 assertEquals(
////                                 LengthUnit.FEET,
////                                 q1.subtract(q2).getUnit());
////         }
//
////         @Test
////         void testExplicitTargetUnit_AddSubtract_Overrides() {
////                 Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 assertEquals(
////                                 LengthUnit.INCHES,
////                                 q1.add(q2, LengthUnit.INCHES)
////                                                 .getUnit());
////         }
//
////         @Test
////         void testImmutability_AfterAdd_ViaCentralizedHelper() {
//
////                 Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 q1.add(q2);
//
////                 assertEquals(1.0, q1.getValue());
////                 assertEquals(12.0, q2.getValue());
////         }
//
////         @Test
////         void testImmutability_AfterSubtract_ViaCentralizedHelper() {
//
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
//
////                 q1.subtract(q2);
//
////                 assertEquals(10.0, q1.getValue());
////                 assertEquals(5.0, q2.getValue());
////         }
//
////         @Test
////         void testImmutability_AfterDivide_ViaCentralizedHelper() {
//
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
//
////                 q1.divide(q2);
//
////                 assertEquals(10.0, q1.getValue());
////                 assertEquals(2.0, q2.getValue());
////         }
//
////         @Test
////         void testAllOperations_AcrossAllCategories() {
//
////                 Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.FEET);
//
////                 assertEquals(15.0,
////                                 length1.add(length2).getValue());
//
////                 Quantity<WeightUnit> weight1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
//
////                 Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);
//
////                 assertEquals(
////                                 3.0,
////                                 weight1.add(weight2).getValue(),
////                                 0.01);
//
////                 Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
//
////                 Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//
////                 assertEquals(
////                                 2.0,
////                                 volume1.add(volume2).getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testErrorMessage_Consistency_Across_Operations() {
//
////                 Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 assertEquals(
////                                 "Quantity cannot be null",
////                                 assertThrows(
////                                                 IllegalArgumentException.class,
////                                                 () -> q.add(null)).getMessage());
////         }
//
////         @Test
////         void testArithmetic_Chain_Operations() {
//
////                 Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> q4 = new Quantity<>(7.0, LengthUnit.FEET);
//
////                 double result = q1.add(q2)
////                                 .subtract(q3)
////                                 .divide(q4);
//
////                 assertEquals(
////                                 11.0 / 7.0,
////                                 result,
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureEquality_CelsiusToCelsius_SameValue() {
////                 assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
////                                 .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
////         }
//
////         @Test
////         void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
////                 assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
////                                 .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
////         }
//
////         @Test
////         void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
////                 assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
////                                 .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
////         }
//
////         @Test
////         void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
////                 assertTrue(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                 .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT)));
////         }
//
////         @Test
////         void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
////                 assertTrue(new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
////                                 .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)));
////         }
//
////         @Test
////         void testTemperatureEquality_SymmetricProperty() {
////                 Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
////                 Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
//
////                 assertTrue(c.equals(f));
////                 assertTrue(f.equals(c));
////         }
//
////         @Test
////         void testTemperatureEquality_ReflexiveProperty() {
////                 Quantity<TemperatureUnit> temp = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
//
////                 assertTrue(temp.equals(temp));
////         }
//
////         @Test
////         void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
////                 assertEquals(122.0,
////                                 new Quantity<>(50.0, TemperatureUnit.CELSIUS)
////                                                 .convertTo(TemperatureUnit.FAHRENHEIT)
////                                                 .getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
////                 assertEquals(50.0,
////                                 new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT)
////                                                 .convertTo(TemperatureUnit.CELSIUS)
////                                                 .getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_RoundTrip_PreservesValue() {
////                 Quantity<TemperatureUnit> original = new Quantity<>(75.0, TemperatureUnit.CELSIUS);
//
////                 Quantity<TemperatureUnit> converted = original.convertTo(TemperatureUnit.FAHRENHEIT)
////                                 .convertTo(TemperatureUnit.CELSIUS);
//
////                 assertEquals(original.getValue(),
////                                 converted.getValue(), 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_SameUnit() {
////                 Quantity<TemperatureUnit> result = new Quantity<>(50.0, TemperatureUnit.CELSIUS)
////                                 .convertTo(TemperatureUnit.CELSIUS);
//
////                 assertEquals(50.0, result.getValue(), 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_ZeroValue() {
////                 assertEquals(32.0,
////                                 new Quantity<>(0.0, TemperatureUnit.CELSIUS)
////                                                 .convertTo(TemperatureUnit.FAHRENHEIT)
////                                                 .getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_NegativeValues() {
////                 assertEquals(-40.0,
////                                 new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
////                                                 .convertTo(TemperatureUnit.FAHRENHEIT)
////                                                 .getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureConversion_LargeValues() {
////                 assertEquals(1832.0,
////                                 new Quantity<>(1000.0, TemperatureUnit.CELSIUS)
////                                                 .convertTo(TemperatureUnit.FAHRENHEIT)
////                                                 .getValue(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureUnsupportedOperation_Add() {
////                 assertThrows(UnsupportedOperationException.class,
////                                 () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
////         }
//
////         @Test
////         void testTemperatureUnsupportedOperation_Subtract() {
////                 assertThrows(UnsupportedOperationException.class,
////                                 () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
////         }
//
////         @Test
////         void testTemperatureUnsupportedOperation_Divide() {
////                 assertThrows(UnsupportedOperationException.class,
////                                 () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
////         }
//
////         @Test
////         void testTemperatureUnsupportedOperation_ErrorMessage() {
////                 Exception ex = assertThrows(
////                                 UnsupportedOperationException.class,
////                                 () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
//
////                 assertTrue(ex.getMessage().contains("Temperature"));
////         }
//
////         @Test
////         void testTemperatureVsLengthIncompatibility() {
////                 assertFalse(
////                                 new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .equals(new Quantity<>(100.0, LengthUnit.FEET)));
////         }
//
////         @Test
////         void testTemperatureVsWeightIncompatibility() {
////                 assertFalse(
////                                 new Quantity<>(50.0, TemperatureUnit.CELSIUS)
////                                                 .equals(new Quantity<>(50.0, WeightUnit.KILOGRAM)));
////         }
//
////         @Test
////         void testTemperatureVsVolumeIncompatibility() {
////                 assertFalse(
////                                 new Quantity<>(25.0, TemperatureUnit.CELSIUS)
////                                                 .equals(new Quantity<>(25.0, VolumeUnit.LITRE)));
////         }
//
////         @Test
////         void testOperationSupportMethods_TemperatureUnitAddition() {
////                 assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
////         }
//
////         @Test
////         void testOperationSupportMethods_TemperatureUnitDivision() {
////                 assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
////         }
//
////         @Test
////         void testOperationSupportMethods_LengthUnitAddition() {
////                 assertTrue(LengthUnit.FEET.supportsArithmetic());
////         }
//
////         @Test
////         void testOperationSupportMethods_WeightUnitDivision() {
////                 assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
////         }
//
////         @Test
////         void testIMeasurableInterface_Evolution_BackwardCompatible() {
////                 Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
//
////                 Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
//
////                 assertTrue(feet.equals(inches));
////         }
//
////         @Test
////         void testTemperatureUnit_NonLinearConversion() {
////                 double fahrenheit = new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                 .convertTo(TemperatureUnit.FAHRENHEIT)
////                                 .getValue();
//
////                 assertEquals(212.0, fahrenheit, 0.01);
////         }
//
////         @Test
////         void testTemperatureUnit_AllConstants() {
////                 assertNotNull(TemperatureUnit.CELSIUS);
////                 assertNotNull(TemperatureUnit.FAHRENHEIT);
////         }
//
////         @Test
////         void testTemperatureUnit_NameMethod() {
////                 assertEquals("CELSIUS",
////                                 TemperatureUnit.CELSIUS.getUnitName());
////         }
//
////         @Test
////         void testTemperatureUnit_ConversionFactor() {
////                 assertEquals(1.0,
////                                 TemperatureUnit.CELSIUS.getConversionFactor(),
////                                 0.01);
////         }
//
////         @Test
////         void testTemperatureNullUnitValidation() {
////                 assertThrows(IllegalArgumentException.class,
////                                 () -> new Quantity<>(100.0, null));
////         }
//
////         @Test
////         void testTemperatureNullOperandValidation_InComparison() {
////                 assertFalse(
////                                 new Quantity<>(100.0, TemperatureUnit.CELSIUS)
////                                                 .equals(null));
////         }
//
////         @Test
////         void testTemperatureDifferentValuesInequality() {
////                 assertFalse(
////                                 new Quantity<>(50.0, TemperatureUnit.CELSIUS)
////                                                 .equals(new Quantity<>(100.0,
////                                                                 TemperatureUnit.CELSIUS)));
////         }
//
////         @Test
////         void testTemperatureConversionPrecision_Epsilon() {
////                 assertTrue(
////                                 new Quantity<>(0.0, TemperatureUnit.CELSIUS)
////                                                 .equals(new Quantity<>(32.0,
////                                                                 TemperatureUnit.FAHRENHEIT)));
////         }
//
////        @Test
//// void testTemperatureConversionEdgeCase_VerySmallDifference() {
////     assertTrue(
////         new Quantity<>(0.0, TemperatureUnit.CELSIUS)
////                 .equals(
////                     new Quantity<>(0.001,
////                             TemperatureUnit.CELSIUS)));
//// }
//
////         @Test
////         void testTemperatureEnumImplementsIMeasurable() {
////                 assertTrue(
////                                 TemperatureUnit.CELSIUS instanceof IMeasurable);
////         }
//
////         @Test
////         void testTemperatureDefaultMethodInheritance() {
////                 assertTrue(LengthUnit.FEET.supportsArithmetic());
////                 assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
////         }
//
////         @Test
////         void testTemperatureCrossUnitAdditionAttempt() {
////                 assertThrows(UnsupportedOperationException.class,
////                                 () -> new Quantity<>(0.0,
////                                                 TemperatureUnit.CELSIUS)
////                                                 .add(new Quantity<>(32.0,
////                                                                 TemperatureUnit.FAHRENHEIT)));
////         }
//
////         @Test
////         void testTemperatureValidateOperationSupport_MethodBehavior() {
////                 assertThrows(UnsupportedOperationException.class,
////                                 () -> TemperatureUnit.CELSIUS
////                                                 .validateOperationSupport("ADD"));
////         }
//
////         @Test
////         void testTemperatureIntegrationWithGenericQuantity() {
////                 Quantity<TemperatureUnit> temp = new Quantity<>(25.0,
////                                 TemperatureUnit.CELSIUS);
//
////                 assertEquals(25.0,
////                                 temp.getValue(), 0.01);
////         }
//        @Test
//    void testQuantityDTO_Constructor() {
//        QuantityDTO dto =
//                new QuantityDTO(
//                        10,
//                        "FEET",
//                        "LENGTH");
//
//        assertEquals(
//                10,
//                dto.getValue());
//    }
//
//    @Test
//    void testQuantityDTO_ErrorConstructor() {
//        QuantityDTO dto =
//                new QuantityDTO(
//                        "Some Error");
//
//        assertTrue(
//                dto.hasError());
//    }
//
//    @Test
//    void testQuantityEntity_SuccessConstruction() {
//
//        QuantityDTO q1 =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO q2 =
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH");
//
//        QuantityMeasurementEntity entity =
//                new QuantityMeasurementEntity(
//                        q1,
//                        q2,
//                        "COMPARE",
//                        true);
//
//        assertFalse(
//                entity.isError());
//    }
//
//    @Test
//    void testRepository_Singleton() {
//
//        assertSame(
//                QuantityMeasurementCacheRepository.getInstance(),
//                QuantityMeasurementCacheRepository.getInstance());
//    }
//
//    @Test
//    void testController_NullService_Prevention() {
//
//        assertThrows(
//                IllegalArgumentException.class,
//
//                () -> new QuantityMeasurementController(
//                        null));
//    }
//
//    @Test
//    void testService_CompareEquality_SameUnit_Success() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO q1 =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO q2 =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH");
//
//        assertTrue(
//                service.compare(
//                        q1,
//                        q2));
//    }
//
//    @Test
//    void testService_Convert_Length() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO source =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO target =
//                new QuantityDTO(
//                        0,
//                        "INCHES",
//                        "LENGTH");
//
//        QuantityDTO result =
//                service.convert(
//                        source,
//                        target);
//
//        assertEquals(
//                12,
//                result.getValue(),
//                0.01);
//    }
//
//    @Test
//    void testService_Add_Length() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO feet =
//                new QuantityDTO(
//                        1,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO inches =
//                new QuantityDTO(
//                        12,
//                        "INCHES",
//                        "LENGTH");
//
//        QuantityDTO result =
//                service.add(
//                        feet,
//                        inches);
//
//        assertEquals(
//                2,
//                result.getValue(),
//                0.01);
//    }
//
//    @Test
//    void testService_Subtract_Length() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO feet =
//                new QuantityDTO(
//                        10,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO inches =
//                new QuantityDTO(
//                        6,
//                        "INCHES",
//                        "LENGTH");
//
//        QuantityDTO result =
//                service.subtract(
//                        feet,
//                        inches);
//
//        assertEquals(
//                9.5,
//                result.getValue(),
//                0.01);
//    }
//
//    @Test
//    void testService_Divide_Length() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO q1 =
//                new QuantityDTO(
//                        10,
//                        "FEET",
//                        "LENGTH");
//
//        QuantityDTO q2 =
//                new QuantityDTO(
//                        2,
//                        "FEET",
//                        "LENGTH");
//
//        assertEquals(
//                5.0,
//                service.divide(
//                        q1,
//                        q2),
//                0.01);
//    }
//
//    @Test
//    void testService_TemperatureAdd_Exception() {
//
//        IQuantityMeasurementService service =
//                new QuantityMeasurementServiceImpl(
//                        QuantityMeasurementCacheRepository.getInstance());
//
//        QuantityDTO celsius =
//                new QuantityDTO(
//                        0,
//                        "CELSIUS",
//                        "TEMPERATURE");
//
//        QuantityDTO fahrenheit =
//                new QuantityDTO(
//                        32,
//                        "FAHRENHEIT",
//                        "TEMPERATURE");
//
//        assertThrows(
//                UnsupportedOperationException.class,
//
//                () -> service.add(
//                        celsius,
//                        fahrenheit));
//    }
//
//    @Test
//    void testIntegration_LengthComparison() {
//
//        QuantityMeasurementController controller =
//                new QuantityMeasurementController(
//
//                        new QuantityMeasurementServiceImpl(
//
//                                QuantityMeasurementCacheRepository.getInstance()));
//
//        assertTrue(
//
//                controller.performCompare(
//
//                        new QuantityDTO(
//                                1,
//                                "FEET",
//                                "LENGTH"),
//
//                        new QuantityDTO(
//                                12,
//                                "INCHES",
//                                "LENGTH")));
//    }
//    @Test
//void testService_CompareEquality_DifferentUnit_Success() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    assertTrue(
//            service.compare(
//                    new QuantityDTO(1, "FEET", "LENGTH"),
//                    new QuantityDTO(12, "INCHES", "LENGTH")));
//}
//
//@Test
//void testService_Convert_Weight() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.convert(
//                    new QuantityDTO(1, "KILOGRAM", "WEIGHT"),
//                    new QuantityDTO(0, "GRAM", "WEIGHT"));
//
//    assertEquals(
//            1000,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Convert_Volume() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.convert(
//                    new QuantityDTO(1, "LITRE", "VOLUME"),
//                    new QuantityDTO(0, "MILLILITRE", "VOLUME"));
//
//    assertEquals(
//            1000,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Convert_Temperature() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.convert(
//                    new QuantityDTO(0, "CELSIUS", "TEMPERATURE"),
//                    new QuantityDTO(0, "KELVIN", "TEMPERATURE"));
//
//    assertEquals(
//            273.15,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Add_Weight() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.add(
//                    new QuantityDTO(1, "KILOGRAM", "WEIGHT"),
//                    new QuantityDTO(1000, "GRAM", "WEIGHT"));
//
//    assertEquals(
//            2.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Add_Volume() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.add(
//                    new QuantityDTO(1, "LITRE", "VOLUME"),
//                    new QuantityDTO(1000, "MILLILITRE", "VOLUME"));
//
//    assertEquals(
//            2.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Subtract_Weight() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.subtract(
//                    new QuantityDTO(10, "KILOGRAM", "WEIGHT"),
//                    new QuantityDTO(5000, "GRAM", "WEIGHT"));
//
//    assertEquals(
//            5.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Subtract_Volume() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    QuantityDTO result =
//            service.subtract(
//                    new QuantityDTO(5, "LITRE", "VOLUME"),
//                    new QuantityDTO(500, "MILLILITRE", "VOLUME"));
//
//    assertEquals(
//            4.5,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testService_Divide_Weight() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    double result =
//            service.divide(
//                    new QuantityDTO(10, "KILOGRAM", "WEIGHT"),
//                    new QuantityDTO(5, "KILOGRAM", "WEIGHT"));
//
//    assertEquals(
//            2.0,
//            result,
//            0.01);
//}
//
//@Test
//void testService_Divide_Volume() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    double result =
//            service.divide(
//                    new QuantityDTO(10, "LITRE", "VOLUME"),
//                    new QuantityDTO(5, "LITRE", "VOLUME"));
//
//    assertEquals(
//            2.0,
//            result,
//            0.01);
//}
//@Test
//void testController_PerformCompare() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    assertTrue(
//            controller.performCompare(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(12,"INCHES","LENGTH")));
//}
//
//@Test
//void testController_PerformConvert() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performConvert(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(0,"INCHES","LENGTH"));
//
//    assertEquals(
//            12.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testController_PerformAdd() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performAdd(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(12,"INCHES","LENGTH"));
//
//    assertEquals(
//            2.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testController_PerformAddWithTargetUnit() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performAdd(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(12,"INCHES","LENGTH"),
//                    new QuantityDTO(0,"INCHES","LENGTH"));
//
//    assertEquals(
//            24.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testController_PerformSubtract() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performSubtract(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(6,"INCHES","LENGTH"));
//
//    assertEquals(
//            9.5,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testController_PerformSubtractWithTargetUnit() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performSubtract(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(6,"INCHES","LENGTH"),
//                    new QuantityDTO(0,"INCHES","LENGTH"));
//
//    assertEquals(
//            114.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testController_PerformDivide() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    assertEquals(
//            5.0,
//            controller.performDivide(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(2,"FEET","LENGTH")),
//            0.01);
//}
//
//@Test
//void testRepository_Save() {
//
//    QuantityMeasurementCacheRepository repo =
//            QuantityMeasurementCacheRepository.getInstance();
//
//    int before =
//            repo.findAll().size();
//
//    repo.save(
//            new QuantityMeasurementEntity(
//                    null,
//                    null,
//                    "TEST",
//                    true));
//
//    assertEquals(
//            before + 1,
//            repo.findAll().size());
//}
//
//@Test
//void testRepository_FindAll() {
//
//    QuantityMeasurementCacheRepository repo =
//            QuantityMeasurementCacheRepository.getInstance();
//
//    assertNotNull(
//            repo.findAll());
//}
//
//@Test
//void testIntegration_LengthConversion() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performConvert(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(0,"INCHES","LENGTH"));
//
//    assertEquals(
//            12.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testIntegration_LengthAddition() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performAdd(
//                    new QuantityDTO(1,"FEET","LENGTH"),
//                    new QuantityDTO(12,"INCHES","LENGTH"));
//
//    assertEquals(
//            2.0,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testIntegration_LengthSubtraction() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    QuantityDTO result =
//            controller.performSubtract(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(6,"INCHES","LENGTH"));
//
//    assertEquals(
//            9.5,
//            result.getValue(),
//            0.01);
//}
//
//@Test
//void testIntegration_LengthDivision() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    assertEquals(
//            5.0,
//            controller.performDivide(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(2,"FEET","LENGTH")),
//            0.01);
//}
//
//@Test
//void testIntegration_TemperatureUnsupportedOperation() {
//
//    QuantityMeasurementController controller =
//            new QuantityMeasurementController(
//                    new QuantityMeasurementServiceImpl(
//                            QuantityMeasurementCacheRepository.getInstance()));
//
//    assertThrows(
//            UnsupportedOperationException.class,
//
//            () -> controller.performAdd(
//                    new QuantityDTO(
//                            0,
//                            "CELSIUS",
//                            "TEMPERATURE"),
//
//                    new QuantityDTO(
//                            32,
//                            "FAHRENHEIT",
//                            "TEMPERATURE")));
//}
//
//@Test
//void testService_Divide_ByZero_Error() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    assertThrows(
//            ArithmeticException.class,
//
//            () -> service.divide(
//                    new QuantityDTO(10,"FEET","LENGTH"),
//                    new QuantityDTO(0,"FEET","LENGTH")));
//}
//
//@Test
//void testService_InvalidMeasurementType_Exception() {
//
//    IQuantityMeasurementService service =
//            new QuantityMeasurementServiceImpl(
//                    QuantityMeasurementCacheRepository.getInstance());
//
//    assertThrows(
//            IllegalArgumentException.class,
//
//            () -> service.compare(
//                    new QuantityDTO(
//                            1,
//                            "ABC",
//                            "INVALID"),
//
//                    new QuantityDTO(
//                            1,
//                            "ABC",
//                            "INVALID")));
//}
//
//}
//
