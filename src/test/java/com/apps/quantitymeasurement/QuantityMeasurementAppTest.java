package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
// import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    // @Test
    // public void testSameValue() {
    //     Feet f1 = new Feet(1.0);
    //     Feet f2 = new Feet(1.0);
    //     assertTrue(f1.equals(f2));
    // }

    // @Test
    // public void testDifferentValue() {
    //     Feet f1 = new Feet(1.0);
    //     Feet f2 = new Feet(2.0);
    //     assertFalse(f1.equals(f2));
    // }

    // @Test
    // public void testNull() {
    //     Feet f1 = new Feet(1.0);
    //     assertFalse(f1.equals(null));
    // }

    // //  SAME REFERENCE (this == obj)
    // @Test
    // public void testSameReference() {
    //     Feet f1 = new Feet(1.0);
    //     assertTrue(f1.equals(f1));
    // }

    // //  DIFFERENT CLASS
    // @Test
    // public void testDifferentClass() {
    //     Feet f1 = new Feet(1.0);
    //     String str = "1.0";
    //     assertFalse(f1.equals(str));
    // }
 

    // //For inches
    // @Test
    // public void testInchesEquality_SameValue() {
    //     Inches i1 = new Inches(1.0);
    //     Inches i2 = new Inches(1.0);
    //     assertTrue(i1.equals(i2));
    // }

    // @Test
    // public void testInchesEquality_DifferentValue() {
    //     Inches i1 = new Inches(1.0);
    //     Inches i2 = new Inches(2.0);
    //     assertFalse(i1.equals(i2));
    // }

    // @Test
    // public void testInchesEquality_NullComparison() {
    //     Inches i1 = new Inches(1.0);
    //     assertFalse(i1.equals(null));
    // }

    // @Test
    // public void testInchesEquality_DifferentClass() {
    //     Inches i1 = new Inches(1.0);
    //     Feet f1 = new Feet(1.0);
    //     assertFalse(i1.equals(f1));
    // }

    // @Test
    // public void testInchesEquality_SameReference() {
    //     Inches i1 = new Inches(1.0);
    //     assertTrue(i1.equals(i1));
    // }

     @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    //  Inches == Inches (same value)
    @Test
    public void testInchesEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    //  Feet == Inches (conversion check)
    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    //  Feet != Feet (different values)
    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    //  Inches != Inches (different values)
    @Test
    public void testInchesInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(2.0, Length.LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    //  Cross unit inequality
    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(10.0, Length.LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    //  Multiple comparison (bigger values)
    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(2.0, Length.LengthUnit.FEET);
        Length l2 = new Length(24.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }
   
   @Test
    public void testEquality_YardToYard_SameValue() {

        Length yard1 =new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 =new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {

        Length yard1 =new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 =new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        Length feet =new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {

        Length feet =new Length(3.0, Length.LengthUnit.FEET);
        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches =new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_InchesToYard_EquivalentValue() {

        Length inches =new Length(36.0, Length.LengthUnit.INCHES);
        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(inches.equals(yard));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {

        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        Length feet =new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {

        Length cm =new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length inches =new Length(0.393701, Length.LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalentValue() {

        Length cm =new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length feet =new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {

        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet =new Length(3.0, Length.LengthUnit.FEET);
        Length inches =new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_YardWithNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Length yard =new Length(1.0, null);
                    yard.equals(new Length(1.0, Length.LengthUnit.YARDS)
                    );
                }
        );
    }

    @Test
    public void testEquality_YardSameReference() {

        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    public void testEquality_YardNullComparison() {

        Length yard =new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    public void testEquality_CentimetersWithNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Length cm =new Length(1.0, null);
                    cm.equals(new Length(1.0,Length.LengthUnit.CENTIMETERS
                            )
                    );
                }
        );
    }

    @Test
    public void testEquality_CentimetersSameReference() {

        Length cm =new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    @Test
    public void testEquality_CentimetersNullComparison() {

        Length cm =new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {

        Length yards =new Length(2.0, Length.LengthUnit.YARDS);
        Length feet =new Length(6.0, Length.LengthUnit.FEET);
        Length inches =new Length(72.0, Length.LengthUnit.INCHES);
        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
    // UC5 Conversion Test Cases
@Test
void testConversion_FeetToInches() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    1.0,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            );

    assertEquals(12.0, result.getValue(), 1e-6);
}

@Test
void testConversion_InchesToFeet() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    24.0,
                    Length.LengthUnit.INCHES,
                    Length.LengthUnit.FEET
            );

    assertEquals(2.0, result.getValue(), 1e-6);
}

@Test
void testConversion_YardsToInches() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    1.0,
                    Length.LengthUnit.YARDS,
                    Length.LengthUnit.INCHES
            );

    assertEquals(36.0, result.getValue(), 1e-6);
}

@Test
void testConversion_InchesToYards() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    72.0,
                    Length.LengthUnit.INCHES,
                    Length.LengthUnit.YARDS
            );

    assertEquals(2.0, result.getValue(), 1e-6);
}

@Test
void testConversion_CentimetersToInches() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    2.54,
                    Length.LengthUnit.CENTIMETERS,
                    Length.LengthUnit.INCHES
            );

    assertEquals(1.0, result.getValue(), 1e-6);
}

@Test
void testConversion_FeetToYard() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    6.0,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.YARDS
            );

    assertEquals(2.0, result.getValue(), 1e-6);
}

@Test
void testConversion_RoundTrip_PreservesValue() {

    Length first =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    5.0,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            );

    Length second =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    first.getValue(),
                    Length.LengthUnit.INCHES,
                    Length.LengthUnit.FEET
            );

    assertEquals(5.0, second.getValue(), 1e-6);
}

@Test
void testConversion_ZeroValue() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    0.0,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            );

    assertEquals(0.0, result.getValue(), 1e-6);
}

@Test
void testConversion_NegativeValue() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    -1.0,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            );

    assertEquals(-12.0, result.getValue(), 1e-6);
}

@Test
void testConversion_InvalidUnit_Throws() {

    assertThrows(
            IllegalArgumentException.class,
            () -> QuantityMeasurementApp.demonstrateLengthConversion(
                    1.0,
                    null,
                    Length.LengthUnit.INCHES
            )
    );
}

@Test
void testConversion_NaNOrInfinite_Throws() {

    assertThrows(
            IllegalArgumentException.class,
            () -> QuantityMeasurementApp.demonstrateLengthConversion(
                    Double.NaN,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            )
    );

    assertThrows(
            IllegalArgumentException.class,
            () -> QuantityMeasurementApp.demonstrateLengthConversion(
                    Double.POSITIVE_INFINITY,
                    Length.LengthUnit.FEET,
                    Length.LengthUnit.INCHES
            )
    );
}

@Test
void testConversion_PrecisionTolerance() {

    Length result =
            QuantityMeasurementApp.demonstrateLengthConversion(
                    2.54,
                    Length.LengthUnit.CENTIMETERS,
                    Length.LengthUnit.INCHES
            );

    assertEquals(
            1.0,
            result.getValue(),
            1e-6
    );
}
}