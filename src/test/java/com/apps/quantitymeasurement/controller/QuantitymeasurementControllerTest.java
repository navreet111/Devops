package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuantityMeasurementControllerTest {

    private IQuantityMeasurementService service;

    private QuantityMeasurementController controller;

    private static final double EPSILON =
            0.0001;

    private QuantityDTO feet;
    private QuantityDTO inches;

    private QuantityDTO kilogram;
    private QuantityDTO gram;

    @BeforeEach
    void setUp() {

        service =
                mock(
                        IQuantityMeasurementService.class);

        controller =
                new QuantityMeasurementController(
                        service);

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
    }
    // =====================================================
    // Constructor
    // =====================================================

    @Test
    void testConstructorWithNullService() {

        assertThrows(

                IllegalArgumentException.class,

                () -> new QuantityMeasurementController(
                        null));
    }

    // =====================================================
    // Compare
    // =====================================================

    @Test
    void testPerformCompare() {

        when(
                service.compare(
                        feet,
                        inches))
                .thenReturn(true);

        assertTrue(

                controller.performCompare(
                        feet,
                        inches));

        verify(service)
                .compare(
                        feet,
                        inches);
    }

    // =====================================================
    // Convert
    // =====================================================

    @Test
    void testPerformConvert() {

        QuantityDTO expected =

                new QuantityDTO(
                        12,
                        "INCHES",
                        "LENGTH");

        QuantityDTO target =

                new QuantityDTO(
                        0,
                        "INCHES",
                        "LENGTH");

        when(
                service.convert(
                        feet,
                        target))
                .thenReturn(expected);

        QuantityDTO result =

                controller.performConvert(
                        feet,
                        target);

        assertEquals(
                12,
                result.getValue(),
                EPSILON);

        verify(service)
                .convert(
                        feet,
                        target);
    }

    // =====================================================
    // Addition
    // =====================================================

    @Test
    void testPerformAdd() {

        QuantityDTO expected =

                new QuantityDTO(
                        2,
                        "KILOGRAM",
                        "WEIGHT");

        when(
                service.add(
                        kilogram,
                        gram))
                .thenReturn(expected);

        QuantityDTO result =

                controller.performAdd(
                        kilogram,
                        gram);

        assertEquals(
                2,
                result.getValue(),
                EPSILON);

        verify(service)
                .add(
                        kilogram,
                        gram);
    }
    // =====================================================
    // Addition With Target Unit
    // =====================================================

    @Test
    void testPerformAddWithTargetUnit() {

        QuantityDTO target =
                new QuantityDTO(
                        0,
                        "GRAM",
                        "WEIGHT");

        QuantityDTO expected =
                new QuantityDTO(
                        2000,
                        "GRAM",
                        "WEIGHT");

        when(
                service.add(
                        kilogram,
                        gram,
                        target))
                .thenReturn(expected);

        QuantityDTO result =
                controller.performAdd(
                        kilogram,
                        gram,
                        target);

        assertEquals(
                2000,
                result.getValue(),
                EPSILON);

        verify(service)
                .add(
                        kilogram,
                        gram,
                        target);
    }

    // =====================================================
    // Subtraction
    // =====================================================

    @Test
    void testPerformSubtract() {

        QuantityDTO expected =
                new QuantityDTO(
                        9.5,
                        "FEET",
                        "LENGTH");

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

        when(
                service.subtract(
                        quantity1,
                        quantity2))
                .thenReturn(expected);

        QuantityDTO result =
                controller.performSubtract(
                        quantity1,
                        quantity2);

        assertEquals(
                9.5,
                result.getValue(),
                EPSILON);

        verify(service)
                .subtract(
                        quantity1,
                        quantity2);
    }

    // =====================================================
    // Subtraction With Target Unit
    // =====================================================

    @Test
    void testPerformSubtractWithTargetUnit() {

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

        QuantityDTO expected =
                new QuantityDTO(
                        500,
                        "GRAM",
                        "WEIGHT");

        when(
                service.subtract(
                        quantity1,
                        quantity2,
                        target))
                .thenReturn(expected);

        QuantityDTO result =
                controller.performSubtract(
                        quantity1,
                        quantity2,
                        target);

        assertEquals(
                500,
                result.getValue(),
                EPSILON);

        verify(service)
                .subtract(
                        quantity1,
                        quantity2,
                        target);
    }

    // =====================================================
    // Divide
    // =====================================================

    @Test
    void testPerformDivide() {

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

        when(
                service.divide(
                        quantity1,
                        quantity2))
                .thenReturn(5.0);

        double result =
                controller.performDivide(
                        quantity1,
                        quantity2);

        assertEquals(
                5.0,
                result,
                EPSILON);

        verify(service)
                .divide(
                        quantity1,
                        quantity2);
    }

    // =====================================================
    // Display Result
    // =====================================================

    @Test
    void testDisplayResult() {

        assertDoesNotThrow(

                () -> controller.displayResult(
                        "Hello World"));
    }
}