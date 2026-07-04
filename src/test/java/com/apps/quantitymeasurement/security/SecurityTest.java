package com.apps.quantitymeasurement.security;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRESTEndpointSecurity_Unauthorized()
            throws Exception {

        mockMvc.perform(

                        get("/api/v1/quantities/count"))

                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRESTEndpointSecurity_WithAuthentication()
            throws Exception {

        // Future Implementation
    }
}