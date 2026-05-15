package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

//     public static class Feet {
//         private final double value;

//         public Feet(double value) {
//             this.value = value;
//         }

//         @Override
//         public boolean equals(Object obj) {

//             if (this == obj) return true;

//             if (obj == null || getClass() != obj.getClass()) return false;

//             Feet feet = (Feet) obj;

//             return Double.compare(this.value, feet.value) == 0;
//         }
//     }
//     public static class Inches {
//     private final double value;

//     public Inches(double value) {
//         this.value = value;
//     }

//     @Override
//     public boolean equals(Object obj) {

//         if (this == obj) return true;

//         if (obj == null || getClass() != obj.getClass()) return false;

//         Inches inches = (Inches) obj;

//         return Double.compare(this.value, inches.value) == 0;
//     }
// }
// public static void demonstrateFeetEquality() {
//     Feet f1 = new Feet(1.0);
//     Feet f2 = new Feet(1.0);

//     System.out.println("Feet equal: " + f1.equals(f2));
// }

// public static void demonstrateInchesEquality() {
//     Inches i1 = new Inches(1.0);
//     Inches i2 = new Inches(1.0);

//     System.out.println("Inches equal: " + i1.equals(i2));
// }
  public static void demonstrateLengthEquality(
            double value1,
            Length.LengthUnit unit1,
            double value2,
            Length.LengthUnit unit2
    ) {

        Length length1 =new Length(value1, unit1);

        Length length2 =new Length(value2, unit2);

        System.out.println(value1 + " " + unit1 +" and " +value2 + " " + unit2 +" are equal: " +length1.equals(length2));
    }

    // Method to demonstrate comparison feature
    public static void demonstrateLengthComparison(
            double value1,
            Length.LengthUnit unit1,
            double value2,
            Length.LengthUnit unit2
    ) {

        demonstrateLengthEquality(value1,unit1,value2,unit2);
    }

    // Generic method to demonstrate CONVERSION
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit
    ) {

        Length originalLength = new Length(value, fromUnit);

        Length convertedLength =originalLength.convertTo(toUnit);

        System.out.println("Converted " + value + " " + fromUnit + " to " + convertedLength.getValue() + " " +convertedLength.getUnit());
        return convertedLength;
    }

    // Method Overloading
    // Conversion using existing Length object
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit
    ) {

        Length convertedLength =length.convertTo(toUnit);

        System.out.println("Converted " +length +" to " +convertedLength);
        return convertedLength;
        
    }
     public static Length demonstrateLengthAddition(
            Length length1,
            Length length2) {

        Length result =
                length1.add(length2);

        System.out.println(
                "Addition Result : "
                        + result
        );

        return result;
}
public static Length demonstrateLengthAddition(
        Length length1,
        Length length2,
        Length.LengthUnit targetUnit
) {

    Length result =
            length1.add(length2, targetUnit);

    System.out.println(
            "Addition Result : " + result
    );

    return result;
}

    public static void main(String[] args) {

    //    demonstrateFeetEquality();
    //    demonstrateInchesEquality();
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.FEET,
                12.0,
                Length.LengthUnit.INCHES
        );

        // Yard and Feet comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.YARDS,
                3.0,
                Length.LengthUnit.FEET
        );

        // Yard and Inches comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.YARDS,
                36.0,
                Length.LengthUnit.INCHES
        );

        // Centimeter and Inches comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.CENTIMETERS,
                0.393701,
                Length.LengthUnit.INCHES
        );

        // Centimeter and Feet comparison
        demonstrateLengthEquality(
                30.48,
                Length.LengthUnit.CENTIMETERS,
                1.0,
                Length.LengthUnit.FEET
        );

        // Comparison demonstrations

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.FEET,
                12.0,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.YARDS,
                3.0,
                Length.LengthUnit.FEET
        );

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.YARDS,
                36.0,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                30.48,
                Length.LengthUnit.CENTIMETERS,
                1.0,
                Length.LengthUnit.FEET
        );

        // Conversion demonstrations

        demonstrateLengthConversion(
                1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                24.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET
        );

        demonstrateLengthConversion(
                1.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                72.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES
        );

        // Overloaded method demo

        Length yard =new Length(2.0,Length.LengthUnit.YARDS);

        demonstrateLengthConversion(yard,Length.LengthUnit.INCHES
);
        demonstrateLengthAddition(
                new Length(1.0,Length.LengthUnit.FEET),
                new Length(2.0,Length.LengthUnit.FEET));
        

        // 1 FEET + 12 INCHES = 2 FEET
        demonstrateLengthAddition(
                new Length(1.0,Length.LengthUnit.FEET),
                new Length(12.0,Length.LengthUnit.INCHES));
        

        // 12 INCHES + 1 FEET = 24 INCHES
         demonstrateLengthAddition(
                new Length(12.0,Length.LengthUnit.INCHES),
                new Length(1.0,Length.LengthUnit.FEET));
        

        // 1 YARD + 3 FEET = 2 YARDS
          demonstrateLengthAddition(
               new Length(1.0,Length.LengthUnit.YARDS),
               new Length(3.0,Length.LengthUnit.FEET));
       

        // 36 INCHES + 1 YARD = 72 INCHES
        demonstrateLengthAddition(
                new Length(36.0,Length.LengthUnit.INCHES),
                new Length(1.0,Length.LengthUnit.YARDS));
        

        // 2.54 CM + 1 INCH = ~5.08 CM
        demonstrateLengthAddition(
                new Length(2.54,Length.LengthUnit.CENTIMETERS),
                new Length(1.0,Length.LengthUnit.INCHES));
       

        // 5 FEET + 0 INCHES = 5 FEET
        demonstrateLengthAddition(
                new Length(5.0,Length.LengthUnit.FEET),
                new Length(0.0,Length.LengthUnit.INCHES));
       

        // 5 FEET + (-2 FEET) = 3 FEET
        demonstrateLengthAddition(
                new Length(5.0,Length.LengthUnit.FEET),
                new Length(-2.0,Length.LengthUnit.FEET));

                demonstrateLengthAddition(
            new Length(1.0, Length.LengthUnit.FEET),
            new Length(12.0, Length.LengthUnit.INCHES),
            Length.LengthUnit.FEET
    );

    demonstrateLengthAddition(
            new Length(1.0, Length.LengthUnit.FEET),
            new Length(12.0, Length.LengthUnit.INCHES),
            Length.LengthUnit.INCHES
    );

    demonstrateLengthAddition(
            new Length(1.0, Length.LengthUnit.FEET),
            new Length(12.0, Length.LengthUnit.INCHES),
            Length.LengthUnit.YARDS
    );

    demonstrateLengthAddition(
            new Length(2.54, Length.LengthUnit.CENTIMETERS),
            new Length(1.0, Length.LengthUnit.INCHES),
            Length.LengthUnit.CENTIMETERS
    );
       
    }
    }
