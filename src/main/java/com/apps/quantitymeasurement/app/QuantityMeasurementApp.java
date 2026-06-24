package com.apps.quantitymeasurement.app;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // ================= LENGTH =================

        QuantityDTO feet =
                new QuantityDTO(
                        1.0,
                        "FEET",
                        "LENGTH");

        QuantityDTO inches =
                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "LENGTH");

        System.out.println(
                "1 FEET == 12 INCHES : "
                        + controller.performCompare(
                        feet,
                        inches));

        System.out.println(
                "1 FEET -> INCHES : "
                        + controller.performConvert(
        feet,
        new QuantityDTO(
                0,
                "INCHES",
                "LENGTH"
        )
)
);

        System.out.println(
                "1 FEET + 12 INCHES : "
                        + controller.performAdd(
                        feet,
                        inches));

        System.out.println(
                "10 FEET - 6 INCHES : "
                        + controller.performSubtract(
                        new QuantityDTO(
                                10,
                                "FEET",
                                "LENGTH"),

                        new QuantityDTO(
                                6,
                                "INCHES",
                                "LENGTH")));

        System.out.println(
                "10 FEET / 2 FEET : "
                        + controller.performDivide(
                        new QuantityDTO(
                                10,
                                "FEET",
                                "LENGTH"),

                        new QuantityDTO(
                                2,
                                "FEET",
                                "LENGTH")));

        // ================= WEIGHT =================

        QuantityDTO kilogram =
                new QuantityDTO(
                        1.0,
                        "KILOGRAM",
                        "WEIGHT");

        QuantityDTO gram =
                new QuantityDTO(
                        1000.0,
                        "GRAM",
                        "WEIGHT");

        System.out.println(
                "1 KG == 1000 GRAM : "
                        + controller.performCompare(
                        kilogram,
                        gram));

        System.out.println(
                "1 KG + 1000 GRAM : "
                        + controller.performAdd(
                        kilogram,
                        gram));

        // ================= VOLUME =================

        QuantityDTO litre =
                new QuantityDTO(
                        1.0,
                        "LITRE",
                        "VOLUME");

        QuantityDTO milliLitre =
                new QuantityDTO(
                        1000.0,
                        "MILLILITRE",
                        "VOLUME");

        System.out.println(
                "1 LITRE == 1000 ML : "
                        + controller.performCompare(
                        litre,
                        milliLitre));

        System.out.println(
                "1 LITRE + 1000 ML : "
                        + controller.performAdd(
                        litre,
                        milliLitre));

        // ================= TEMPERATURE =================

        QuantityDTO celsius =
                new QuantityDTO(
                        0.0,
                        "CELSIUS",
                        "TEMPERATURE");

        QuantityDTO fahrenheit =
                new QuantityDTO(
                        32.0,
                        "FAHRENHEIT",
                        "TEMPERATURE");

        System.out.println(
                "0 C == 32 F : "
                        + controller.performCompare(
                        celsius,
                        fahrenheit));

        System.out.println(
                "0 C -> KELVIN : "
                        + controller.performConvert(
        celsius,
        new QuantityDTO(
                0,
                "KELVIN",
                "TEMPERATURE"
        )
)
);

        try {

            controller.performAdd(
                    celsius,
                    fahrenheit);

        } catch (
                UnsupportedOperationException e) {

            System.out.println(
                    e.getMessage());
        }

        try {

            controller.performSubtract(
                    celsius,
                    fahrenheit);

        } catch (
                UnsupportedOperationException e) {

            System.out.println(
                    e.getMessage());
        }

        try {

            controller.performDivide(
                    celsius,
                    fahrenheit);

        } catch (
                UnsupportedOperationException e) {

            System.out.println(
                    e.getMessage());
        }
    }
}