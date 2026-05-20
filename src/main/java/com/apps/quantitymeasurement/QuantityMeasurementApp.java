package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void demonstrateLengthEquality(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2
    ) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        System.out.println(
                value1 + " " + unit1 +
                " and " +
                value2 + " " + unit2 +
                " are equal: " +
                length1.equals(length2)
        );
    }

    // Method to demonstrate comparison feature
    public static void demonstrateLengthComparison(
            double value1,
            LengthUnit unit1,
            double value2,
            LengthUnit unit2
    ) {

        demonstrateLengthEquality(
                value1,
                unit1,
                value2,
                unit2
        );
    }

    // Generic method to demonstrate conversion
    public static Length demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit
    ) {

        Length originalLength =
                new Length(value, fromUnit);

        Length convertedLength =
                originalLength.convertTo(toUnit);

        System.out.println(
                "Converted " +
                value + " " + fromUnit +
                " to " +
                convertedLength.getValue() +
                " " +
                convertedLength.getUnit()
        );

        return convertedLength;
    }

    // Overloaded method
    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit
    ) {

        Length convertedLength =
                length.convertTo(toUnit);

        System.out.println(
                "Converted " +
                length +
                " to " +
                convertedLength
        );

        return convertedLength;
    }

    // UC6
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2
    ) {

        Length result =
                length1.add(length2);

        System.out.println(
                "Addition Result : " + result
        );

        return result;
    }

    // UC7
    public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            LengthUnit targetUnit
    ) {

        Length result =
                length1.add(length2, targetUnit);

        System.out.println(
                "Addition Result : " + result
        );

        return result;
    }
    

    public static void main(String[] args) {

        // Equality

        demonstrateLengthEquality(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.YARDS,
                36.0,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                1.0,
                LengthUnit.CENTIMETERS,
                0.393701,
                LengthUnit.INCHES
        );

        demonstrateLengthEquality(
                30.48,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.FEET
        );

        // Comparison

        demonstrateLengthComparison(
                1.0,
                LengthUnit.FEET,
                12.0,
                LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1.0,
                LengthUnit.YARDS,
                3.0,
                LengthUnit.FEET
        );

        demonstrateLengthComparison(
                1.0,
                LengthUnit.YARDS,
                36.0,
                LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                30.48,
                LengthUnit.CENTIMETERS,
                1.0,
                LengthUnit.FEET
        );

        // Conversion

        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        // Overloaded conversion

        Length yard =
                new Length(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                yard,
                LengthUnit.INCHES
        );

        // UC6 Addition

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(2.0, LengthUnit.FEET)
        );

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.FEET)
        );

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET)
        );

        demonstrateLengthAddition(
                new Length(36.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS)
        );

        demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET)
        );

        // UC7 Addition with target unit

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );

        demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );
    }
}