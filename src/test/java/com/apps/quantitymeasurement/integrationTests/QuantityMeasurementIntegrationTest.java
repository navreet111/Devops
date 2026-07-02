package com.apps.quantitymeasurement.integrationTests;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.factory.RepositoryFactory;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;

    private IQuantityMeasurementRepository repository;

    private IQuantityMeasurementService service;

    private QuantityDTO feet;

    private QuantityDTO inches;

    private QuantityDTO kilogram;

    private QuantityDTO gram;

    @BeforeEach
    void setUp() {

        repository = RepositoryFactory.getRepository();

        repository.deleteAll();

        service =
                new QuantityMeasurementServiceImpl(
                        repository);

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

    @AfterEach
    void tearDown() {

        repository.deleteAll();

        repository.releaseResources();
    }
    // =====================================================
    // End-to-End Comparison
    // =====================================================

    @Test
    void testEndToEndComparison() {

        boolean result =
                controller.performCompare(
                        feet,
                        inches);

        assertTrue(result);

        assertEquals(
                1,
                repository.getTotalCount());
    }

    // =====================================================
    // End-to-End Conversion
    // =====================================================

    @Test
    void testEndToEndConversion() {

        QuantityDTO result =

                controller.performConvert(

                        feet,

                        new QuantityDTO(
                                0,
                                "INCHES",
                                "LENGTH"));

        assertEquals(
                12.0,
                result.getValue(),
                0.0001);

        assertEquals(
                "INCHES",
                result.getUnit());

        assertEquals(
                1,
                repository.getTotalCount());
    }

    // =====================================================
    // End-to-End Addition
    // =====================================================

    @Test
    void testEndToEndAddition() {

        QuantityDTO result =

                controller.performAdd(
                        kilogram,
                        gram);

        assertEquals(
                2.0,
                result.getValue(),
                0.0001);

        assertEquals(
                "KILOGRAM",
                result.getUnit());

        assertEquals(
                1,
                repository.getTotalCount());
    }
    // =====================================================
    // End-to-End Subtraction
    // =====================================================

    @Test
    void testEndToEndSubtraction() {

        QuantityDTO result =

                controller.performSubtract(

                        new QuantityDTO(
                                10,
                                "FEET",
                                "LENGTH"),

                        new QuantityDTO(
                                6,
                                "INCHES",
                                "LENGTH"));

        assertEquals(
                9.5,
                result.getValue(),
                0.0001);

        assertEquals(
                "FEET",
                result.getUnit());

        assertEquals(
                1,
                repository.getTotalCount());
    }

    // =====================================================
    // End-to-End Division
    // =====================================================

    @Test
    void testEndToEndDivision() {

        double result =

                controller.performDivide(

                        new QuantityDTO(
                                10,
                                "FEET",
                                "LENGTH"),

                        new QuantityDTO(
                                2,
                                "FEET",
                                "LENGTH"));

        assertEquals(
                5.0,
                result,
                0.0001);

        assertEquals(
                1,
                repository.getTotalCount());
    }

    // =====================================================
    // Verify Repository Persistence
    // =====================================================

    @Test
    void testRepositoryPersistence() {

        controller.performCompare(
                feet,
                inches);

        controller.performAdd(
                kilogram,
                gram);

        assertEquals(
                2,
                repository.getTotalCount());

        assertEquals(
                2,
                repository.findAll().size());
    }

    // =====================================================
    // Multiple Operations
    // =====================================================

    @Test
    void testMultipleOperations() {

        controller.performCompare(
                feet,
                inches);

        controller.performConvert(
                feet,
                new QuantityDTO(
                        0,
                        "INCHES",
                        "LENGTH"));

        controller.performAdd(
                kilogram,
                gram);

        assertEquals(
                3,
                repository.getTotalCount());
    }
    // =====================================================
    // Repository Initially Empty
    // =====================================================

    @Test
    void testRepositoryInitiallyEmpty() {

        assertEquals(
                0,
                repository.getTotalCount());

        assertTrue(
                repository.findAll()
                        .isEmpty());
    }

    // =====================================================
    // Delete All Records
    // =====================================================

    @Test
    void testDeleteAllRecords() {

        controller.performCompare(
                feet,
                inches);

        controller.performAdd(
                kilogram,
                gram);

        assertEquals(
                2,
                repository.getTotalCount());

        repository.deleteAll();

        assertEquals(
                0,
                repository.getTotalCount());

        assertTrue(
                repository.findAll()
                        .isEmpty());
    }

    // =====================================================
    // Repository Release Resources
    // =====================================================

//    @Test
//    void testReleaseResources() {
//
//        assertDoesNotThrow(
//
//                () -> repository.releaseResources());
//    }

    // =====================================================
    // End-to-End Workflow
    // =====================================================

    @Test
    void testCompleteWorkflow() {

        assertTrue(
                controller.performCompare(
                        feet,
                        inches));

        controller.performConvert(
                feet,
                new QuantityDTO(
                        0,
                        "INCHES",
                        "LENGTH"));

        controller.performAdd(
                kilogram,
                gram);

        controller.performSubtract(

                new QuantityDTO(
                        10,
                        "FEET",
                        "LENGTH"),

                new QuantityDTO(
                        6,
                        "INCHES",
                        "LENGTH"));

        assertEquals(
                4,
                repository.getTotalCount());
    }
}