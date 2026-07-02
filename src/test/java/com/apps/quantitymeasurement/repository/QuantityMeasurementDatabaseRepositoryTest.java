package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    private QuantityDTO feet;
    private QuantityDTO inches;

    private QuantityDTO kilogram;
    private QuantityDTO gram;

    private QuantityMeasurementEntity compareEntity;
    private QuantityMeasurementEntity addEntity;

    @BeforeEach
    void setUp() {

        repository =
                QuantityMeasurementDatabaseRepository
                        .getInstance();

        repository.deleteAll();

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

        compareEntity =
                new QuantityMeasurementEntity(
                        feet,
                        inches,
                        "COMPARE",
                        Boolean.TRUE);

        addEntity =
                new QuantityMeasurementEntity(
                        kilogram,
                        gram,
                        "ADD",
                        new QuantityDTO(
                                2.0,
                                "KILOGRAM",
                                "WEIGHT"));
    }

    @AfterEach
    void tearDown() {

        try {

            repository.deleteAll();

        }

        catch (Exception ignored) {

        }
    }

    // Test methods will be added here in Part 5A-2

// =====================================================
// Save Entity
// =====================================================

    @Test
    void testSaveEntity() {

        repository.save(compareEntity);

        assertEquals(
                1,
                repository.getTotalCount());

        List<QuantityMeasurementEntity> list =
                repository.findAll();

        assertFalse(
                list.isEmpty());

        assertEquals(
                "COMPARE",
                list.get(0).getOperation());

        assertEquals(
                true,
                list.get(0).getResult());
    }

// =====================================================
// Find All
// =====================================================

    @Test
    void testFindAll() {

        repository.save(compareEntity);

        repository.save(addEntity);

        List<QuantityMeasurementEntity> list =
                repository.findAll();

        assertEquals(
                2,
                list.size());

        assertEquals(
                "COMPARE",
                list.get(0).getOperation());

        assertEquals(
                true,
                list.get(0).getResult());

        assertEquals(
                "ADD",
                list.get(1).getOperation());

        assertTrue(
                list.get(1)
                        .getResult()
                        .toString()
                        .contains("KILOGRAM"));

        assertTrue(
                list.get(1)
                        .getResult()
                        .toString()
                        .contains("2.0"));
    }

// =====================================================
// Total Count
// =====================================================

    @Test
    void testGetTotalCount() {

        assertEquals(
                0,
                repository.getTotalCount());

        repository.save(compareEntity);

        assertEquals(
                1,
                repository.getTotalCount());

        repository.save(addEntity);

        assertEquals(
                2,
                repository.getTotalCount());
    }
// =====================================================
// Find By Operation
// =====================================================

    @Test
    void testGetMeasurementsByOperation() {

        repository.save(compareEntity);

        repository.save(addEntity);

        List<QuantityMeasurementEntity> result =

                repository.getMeasurementsByOperation(
                        "COMPARE");

        assertEquals(
                1,
                result.size());

        assertEquals(
                "COMPARE",
                result.get(0).getOperation());
    }

// =====================================================
// Find By Measurement Type
// =====================================================

    @Test
    void testGetMeasurementsByType() {

        repository.save(compareEntity);

        repository.save(addEntity);

        List<QuantityMeasurementEntity> result =

                repository.getMeasurementsByType(
                        "LENGTH");

        assertEquals(
                1,
                result.size());

        assertEquals(
                "LENGTH",

                result.get(0)
                        .getThisQuantity()
                        .getMeasurementType());
    }

// =====================================================
// Delete All
// =====================================================

    @Test
    void testDeleteAll() {

        repository.save(compareEntity);

        repository.save(addEntity);

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
// Release Resources
// =====================================================

//    @Test
//    void testReleaseResources() {
//
//        assertDoesNotThrow(
//
//                () -> repository.releaseResources());
//    }

// =====================================================
// Repository Initially Empty
// =====================================================

    @Test
    void testRepositoryInitiallyEmpty() {

        assertTrue(

                repository.findAll()
                        .isEmpty());

        assertEquals(

                0,

                repository.getTotalCount());
    }

// =====================================================
// Save Multiple Records
// =====================================================

    @Test
    void testSaveMultipleEntities() {

        for (int i = 0; i < 10; i++) {

            repository.save(
                    compareEntity);
        }

        assertEquals(

                10,

                repository.getTotalCount());

        assertEquals(

                10,

                repository.findAll().size());
    }
}