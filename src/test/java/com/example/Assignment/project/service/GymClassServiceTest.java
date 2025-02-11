package com.example.Assignment.project.service;

import com.example.Assignment.project.Dto.GymClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GymClassServiceTest {
    @Autowired
    private GymClassService service;

    @Test
    void createClass_ValidInput_Success() {
        GymClass gymClass = new GymClass();
        // Set properties
        GymClass result = service.createClass(gymClass);
        assertNotNull(result.getId());
    }

    @Test
    void createClass_EndDateBeforeStartDate_ThrowsException() {
        GymClass gymClass = new GymClass();
        gymClass.setStartDate(LocalDate.now());
        gymClass.setEndDate(LocalDate.now().minusDays(1));

        assertThrows(IllegalArgumentException.class,
                () -> service.createClass(gymClass));
    }
}
