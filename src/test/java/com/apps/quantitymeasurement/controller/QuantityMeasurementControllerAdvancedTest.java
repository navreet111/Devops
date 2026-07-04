package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
class QuantityMeasurementControllerAdvancedTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void testContentNegotiation_JSON()
            throws Exception {

        mockMvc.perform(

                        get("/api/v1/quantities/count")
                                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(content()
                        .contentTypeCompatibleWith(
                                MediaType.APPLICATION_JSON));
    }

    @Test
    void testRequestQueryParameterExtraction()
            throws Exception {

        mockMvc.perform(

                        get("/api/v1/quantities/convert")
                                .param("targetUnit", "FEET"))

                .andExpect(status().isOk());
    }

    @Test
    void testRequestPathVariableExtraction()
            throws Exception {

        mockMvc.perform(

                        get("/api/v1/quantities/count/COMPARE"))

                .andExpect(status().isOk());
    }

    @Test
    void testResponseSerializationObject()
            throws Exception {

        QuantityDTO dto =
                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "LENGTH");

        when(service.convert(any(), any()))
                .thenReturn(dto);

        mockMvc.perform(

                        post("/api/v1/quantities/convert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))

                .andExpect(status().isOk());
    }

    @Test
    void testHttpStatusCodes_ClientErrors()
            throws Exception {

        mockMvc.perform(

                        post("/api/v1/quantities/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}"))

                .andExpect(status().isBadRequest());
    }

    @Test
    void testHttpStatusCodes_Success()
            throws Exception {

        mockMvc.perform(

                        get("/actuator/health"))

                .andExpect(status().isOk());
    }

    @Test
    void testHttpStatusCodes_ServerErrors()
            throws Exception {

        when(service.getTotalCount())
                .thenThrow(
                        new RuntimeException(
                                "Server Error"));

        mockMvc.perform(

                        get("/api/v1/quantities/count"))

                .andExpect(status().isInternalServerError());
    }
}