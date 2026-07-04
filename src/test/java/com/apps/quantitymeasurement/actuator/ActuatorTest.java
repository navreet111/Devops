package com.apps.quantitymeasurement.actuator;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testActuatorHealthEndpoint()
            throws Exception {

        mockMvc.perform(

                        get("/actuator/health"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.status")
                        .value("UP"));
    }

    @Test
    void testActuatorMetricsEndpoint()
            throws Exception {

        mockMvc.perform(

                        get("/actuator/metrics"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.names")
                        .exists());
    }
}