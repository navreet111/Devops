package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(QuantityMeasurementController.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void testRestEndpointCompareQuantities()
            throws Exception {

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

        when(

                service.compare(
                        any(),
                        any()))

                .thenReturn(true);

        mockMvc.perform(

                        post("/api/v1/quantities/compare")

                                .contentType(
                                        MediaType.APPLICATION_JSON)

                                .content(

                                        objectMapper
                                                .writeValueAsString(input)))

                .andExpect(status().isOk())

                .andExpect(

                        content().contentTypeCompatibleWith(
                                MediaType.APPLICATION_JSON));
    }
}