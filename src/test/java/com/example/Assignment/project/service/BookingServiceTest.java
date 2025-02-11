package com.example.Assignment.project.service;

import com.example.Assignment.project.Dto.Booking;
import com.example.Assignment.project.Dto.BookingModelResponse;
import com.example.Assignment.project.Dto.GymClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private GymClassService gymClassService;

    private Long classId;

    @BeforeEach
    void setup() {
        GymClass gymClass = new GymClass();
        // Set properties
        classId = gymClassService.createClass(gymClass).getId();
    }

    @Test
    void createBooking_ValidInput_Success() {
        Booking booking = new Booking();
        // Set properties
        Booking result = bookingService.createBooking(booking);
        assertNotNull(result.getId());
    }

    @Test
    void createBooking_ClassFull_ThrowsException() {
        // Fill class to capacity
        GymClass gymClass = gymClassService.getClass(classId);
        for (int i = 0; i < gymClass.getCapacity(); i++) {
            Booking booking = new Booking();
            // Set properties
            bookingService.createBooking(booking);
        }

        Booking extraBooking = new Booking();
        // Set properties
        assertThrows(IllegalArgumentException.class,
                () -> bookingService.createBooking(extraBooking));
    }

    @Test
    void searchBookings_ReturnsMatchingResults() {
        // Create test bookings
        Booking booking = new Booking();
        // Set properties
        bookingService.createBooking(booking);

        List<BookingModelResponse> results = bookingService.searchBookings(
                "John", LocalDate.now(), LocalDate.now().plusDays(30));
        assertFalse(results.isEmpty());
    }
}