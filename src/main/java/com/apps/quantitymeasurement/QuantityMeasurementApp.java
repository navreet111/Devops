package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

        // Equality
        public static <U extends IMeasurable> boolean demonstrateEquality(
                        Quantity<U> quantity1,
                        Quantity<U> quantity2) {

                return quantity1.equals(quantity2);
        }

        // Conversion
        public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
                        Quantity<U> quantity,
                        U targetUnit) {

                return quantity.convertTo(targetUnit);
        }

        // Addition
        public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
                        Quantity<U> quantity1,
                        Quantity<U> quantity2) {

                return quantity1.add(quantity2);
        }

        // Addition with target unit
        public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
                        Quantity<U> quantity1,
                        Quantity<U> quantity2,
                        U targetUnit) {

                return quantity1.add(quantity2, targetUnit);
        }

        public static void main(String[] args) {

                // LENGTH OPERATIONS

                Quantity<LengthUnit> lengthFeet = new Quantity<>(1.0, LengthUnit.FEET);
                Quantity<LengthUnit> lengthInches = new Quantity<>(12.0, LengthUnit.INCHES);
                System.out.println("Length Equality: " + demonstrateEquality(lengthFeet, lengthInches));
                System.out.println("Length Conversion: " + demonstrateConversion(lengthFeet, LengthUnit.INCHES));
                System.out.println(
                                "Length Addition: " + demonstrateAddition(lengthFeet, lengthInches, LengthUnit.FEET));

                // WEIGHT OPERATIONS

                Quantity<WeightUnit> weightKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
                Quantity<WeightUnit> weightGram = new Quantity<>(1000.0, WeightUnit.GRAM);
                System.out.println("Weight Equality: " + demonstrateEquality(weightKg, weightGram));
                System.out.println("Weight Conversion: " + demonstrateConversion(weightKg, WeightUnit.GRAM));
                System.out.println(
                                "Weight Addition: " + demonstrateAddition(weightKg, weightGram, WeightUnit.KILOGRAM));

                // CROSS CATEGORY CHECK

                Quantity<?> length = new Quantity<>(1.0, LengthUnit.FEET);
                Quantity<?> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
                System.out.println("Cross Category Equality: " + length.equals(weight));
        
                Quantity<VolumeUnit> litre =new Quantity<>(1.0, VolumeUnit.LITRE);
                Quantity<VolumeUnit> milliLitre =new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
                Quantity<VolumeUnit> gallon =new Quantity<>(1.0, VolumeUnit.GALLON);
                System.out.println(litre.equals(milliLitre));
                System.out.println(litre.convertTo(VolumeUnit.MILLILITRE));
                System.out.println(litre.add(milliLitre,VolumeUnit.LITRE));
        
}
}