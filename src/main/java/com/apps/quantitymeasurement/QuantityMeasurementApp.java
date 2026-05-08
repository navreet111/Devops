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
    public static void main(String[] args) {

    //    demonstrateFeetEquality();
    //    demonstrateInchesEquality();
       Length.demonstrateLengthEquality();
        Length l1 =
            new Length(1.0,
            Length.LengthUnit.YARDS);

    Length l2 =
            new Length(36.0,
            Length.LengthUnit.INCHES);

    System.out.println(
            "1 Yard == 36 Inches : "
            + l1.equals(l2)
    );

    Length l3 =
            new Length(3.0,
            Length.LengthUnit.FEET);

    Length l4 =
            new Length(1.0,
            Length.LengthUnit.YARDS);

    System.out.println(
            "3 Feet == 1 Yard : "
            + l3.equals(l4)
    );

    Length l5 =
            new Length(1.0,
            Length.LengthUnit.CENTIMETERS);

    Length l6 =
            new Length(0.393701,
            Length.LengthUnit.INCHES);

    System.out.println(
            "1 CM == 0.393701 Inches : "
            + l5.equals(l6)
    );
    }
}