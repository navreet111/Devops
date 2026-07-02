package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
class QuantityMeasurementServiceImplTest {

    private IQuantityMeasurementRepository repository;

    private QuantityMeasurementServiceImpl service;

    private QuantityDTO feet;

    private QuantityDTO inches;

    private QuantityDTO kilogram;

    private QuantityDTO gram;

    private QuantityDTO litre;

    private QuantityDTO milliLitre;

    private QuantityDTO celsius;

    private QuantityDTO fahrenheit;

    private static final double EPSILON =
            0.0001;

    @BeforeEach
    void setUp() {

        repository =
                mock(
                        IQuantityMeasurementRepository.class);

        service =
                new QuantityMeasurementServiceImpl(
                        repository);

        feet =
                new QuantityDTO(
                        1.0,
                        "FEET",
                        "LENGTH");

        inches =
                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "LENGTH");

        kilogram =
                new QuantityDTO(
                        1.0,
                        "KILOGRAM",
                        "WEIGHT");

        gram =
                new QuantityDTO(
                        1000.0,
                        "GRAM",
                        "WEIGHT");

        litre =
                new QuantityDTO(
                        1.0,
                        "LITRE",
                        "VOLUME");

        milliLitre =
                new QuantityDTO(
                        1000.0,
                        "MILLILITRE",
                        "VOLUME");

        celsius =
                new QuantityDTO(
                        0.0,
                        "CELSIUS",
                        "TEMPERATURE");

        fahrenheit =
                new QuantityDTO(
                        32.0,
                        "FAHRENHEIT",
                        "TEMPERATURE");
    }
    // =====================================================
    // Compare
    // =====================================================

    @Test
    void testCompare() {

        boolean result =
                service.compare(
                        feet,
                        inches);

        assertTrue(result);

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Convert
    // =====================================================

    @Test
    void testConvert() {

        QuantityDTO result =

                service.convert(

                        feet,

                        new QuantityDTO(

                                0,

                                "INCHES",

                                "LENGTH"));

        assertEquals(

                12,

                result.getValue(),

                EPSILON);

        assertEquals(

                "INCHES",

                result.getUnit());

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Addition
    // =====================================================

    @Test
    void testAdd() {

        QuantityDTO result =

                service.add(
                        kilogram,
                        gram);

        assertEquals(

                2,

                result.getValue(),

                EPSILON);

        assertEquals(

                "KILOGRAM",

                result.getUnit());

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Addition With Target Unit
    // =====================================================

    @Test
    void testAddWithTargetUnit() {

        QuantityDTO target =

                new QuantityDTO(

                        0,

                        "GRAM",

                        "WEIGHT");

        QuantityDTO result =

                service.add(

                        kilogram,

                        gram,

                        target);

        assertEquals(

                2000,

                result.getValue(),

                EPSILON);

        assertEquals(

                "GRAM",

                result.getUnit());

        verify(repository)
                .save(any());
    }
    // =====================================================
    // Subtraction
    // =====================================================

    @Test
    void testSubtract() {

        QuantityDTO quantity1 =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH");

        QuantityDTO quantity2 =
                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH");

        QuantityDTO result =
                service.subtract(
                        quantity1,
                        quantity2);

        assertEquals(
                9.5,
                result.getValue(),
                EPSILON);

        assertEquals(
                "FEET",
                result.getUnit());

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Subtraction With Target Unit
    // =====================================================

    @Test
    void testSubtractWithTargetUnit() {

        QuantityDTO quantity1 =
                new QuantityDTO(
                        1,
                        "KILOGRAM",
                        "WEIGHT");

        QuantityDTO quantity2 =
                new QuantityDTO(
                        500,
                        "GRAM",
                        "WEIGHT");

        QuantityDTO target =
                new QuantityDTO(
                        0,
                        "GRAM",
                        "WEIGHT");

        QuantityDTO result =
                service.subtract(
                        quantity1,
                        quantity2,
                        target);

        assertEquals(
                500,
                result.getValue(),
                EPSILON);

        assertEquals(
                "GRAM",
                result.getUnit());

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Division
    // =====================================================

    @Test
    void testDivide() {

        QuantityDTO quantity1 =
                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH");

        QuantityDTO quantity2 =
                new QuantityDTO(
                        2,
                        "FEET",
                        "LENGTH");

        double result =
                service.divide(
                        quantity1,
                        quantity2);

        assertEquals(
                5.0,
                result,
                EPSILON);

        verify(repository)
                .save(any());
    }

    // =====================================================
    // Constructor
    // =====================================================

    @Test
    void testConstructorWithNullRepository() {

        assertThrows(

                IllegalArgumentException.class,

                () -> new QuantityMeasurementServiceImpl(
                        null));
    }
}