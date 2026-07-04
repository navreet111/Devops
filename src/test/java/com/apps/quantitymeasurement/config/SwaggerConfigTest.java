package com.apps.quantitymeasurement.config;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SwaggerConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSwaggerUILoads() throws Exception {

        mockMvc.perform(

                        get("/swagger-ui/index.html"))

                .andExpect(status().isOk());
    }

    @Test
    void testOpenAPIDocumentation() throws Exception {

        mockMvc.perform(

                        get("/v3/api-docs"))

                .andExpect(status().isOk())

                .andExpect(content()
                        .contentTypeCompatibleWith(
                                "application/json"));
    }

    @Test
    void testRestDocumentationOperationDetails()
            throws Exception {

        mockMvc.perform(

                        get("/v3/api-docs"))

                .andExpect(status().isOk())

                .andExpect(content()
                        .string(
                                org.hamcrest.Matchers.containsString(
                                        "compare")))

                .andExpect(content()
                        .string(
                                org.hamcrest.Matchers.containsString(
                                        "convert")))

                .andExpect(content()
                        .string(
                                org.hamcrest.Matchers.containsString(
                                        "add")));
    }
}