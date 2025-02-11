package com.example.Assignment.project.controller;

import com.example.Assignment.project.Dto.GymClass;
import com.example.Assignment.project.service.GymClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private GymClassService gymClassService;

    private Long classId;

    @BeforeEach
    void setup() {
        GymClass gymClass = new GymClass();
        gymClass.setName("Test Class");
        gymClass.setStartDate(LocalDate.now());
        gymClass.setEndDate(LocalDate.now().plusDays(30));
        gymClass.setStartTime(LocalTime.of(10, 0));
        gymClass.setDurationMinutes(60);
        gymClass.setCapacity(10);
        // Set properties
        classId = gymClassService.createClass(gymClass).getId();
    }

    @Test
    void createBooking_ValidInput_ReturnsCreated() throws Exception {
        String json = String.format("""
            {
                "memberName": "John",
                "classId": %d,
                "participationDate": "2024-02-15"
            }""", classId);

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
