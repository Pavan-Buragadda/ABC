package com.example.Assignment.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createClass_ValidInput_ReturnsCreated() throws Exception {
        String json = """
            {
                "name": "Yoga",
                "startDate": "2024-02-15",
                "endDate": "2024-03-15",
                "startTime": "10:00",
                "durationMinutes": 60,
                "capacity": 10
            }""";

        mockMvc.perform(post("/api/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void createClass_InvalidInput_ReturnsBadRequest() throws Exception {
        String json = """
            {
                "name": "",
                "capacity": 0
            }""";

        mockMvc.perform(post("/api/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchBookings_ReturnsMatchingBookings() throws Exception {
        mockMvc.perform(get("/api/owner")
                        .param("memberName", "John")
                        .param("startDate", "2024-02-15")
                        .param("endDate", "2024-03-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
