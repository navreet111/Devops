package com.apps.quantitymeasurement.integrationTests;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuantityMeasurementIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIntegrationTest_MultipleOperations() {

        QuantityInputDTO input =
                new QuantityInputDTO();

        input.setThisQuantityDTO(

                new QuantityDTO(
                        1.0,
                        "FEET",
                        "LENGTH"));

        input.setThatQuantityDTO(

                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "LENGTH"));

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        HttpEntity<QuantityInputDTO> request =
                new HttpEntity<>(
                        input,
                        headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(

                        "http://localhost:"
                                + port
                                + "/api/v1/quantities/compare",

                        request,

                        String.class);

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode());
    }

    @Test
    void testApplicationStarts() {

        assertTrue(
                port > 0);
    }
}