package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuantityMeasurementRepositoryTest {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Test
    void testH2DatabasePersistence() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        repository.save(entity);

        assertEquals(
                1,
                repository.count());
    }

    @Test
    void testDatabaseInitializationSchemaCreated() {

        assertNotNull(repository);

        assertTrue(
                repository.count() >= 0);
    }
}