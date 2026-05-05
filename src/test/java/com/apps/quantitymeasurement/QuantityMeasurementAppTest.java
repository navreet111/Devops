package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    @Test
    public void testSameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    public void testDifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    public void testNull() {
        Feet f1 = new Feet(1.0);
        assertFalse(f1.equals(null));
    }

    //  SAME REFERENCE (this == obj)
    @Test
    public void testSameReference() {
        Feet f1 = new Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    //  DIFFERENT CLASS
    @Test
    public void testDifferentClass() {
        Feet f1 = new Feet(1.0);
        String str = "1.0";
        assertFalse(f1.equals(str));
    }
 

    //For inches
    @Test
    public void testInchesEquality_SameValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);
        assertFalse(i1.equals(i2));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(1.0);
        assertFalse(i1.equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        Inches i1 = new Inches(1.0);
        Feet f1 = new Feet(1.0);
        assertFalse(i1.equals(f1));
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches i1 = new Inches(1.0);
        assertTrue(i1.equals(i1));
    }
}