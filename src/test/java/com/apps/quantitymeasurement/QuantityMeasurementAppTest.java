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
}