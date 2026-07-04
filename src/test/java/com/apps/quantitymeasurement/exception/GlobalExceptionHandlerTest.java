package com.apps.quantitymeasurement.exception;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@Import(GlobalExceptionHandlerTest.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void testExceptionHandling_GlobalHandler()
            throws Exception {

        when(service.getTotalCount())

                .thenThrow(

                        new RuntimeException(
                                "Database Error"));

        mockMvc.perform(

                        get("/api/v1/quantities/count"))

                .andExpect(status().isInternalServerError())

                .andExpect(content()
                        .string(
                                org.hamcrest.Matchers.containsString(
                                        "Database Error")));
    }
}