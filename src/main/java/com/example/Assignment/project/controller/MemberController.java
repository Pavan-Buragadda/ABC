package com.example.Assignment.project.controller;

import com.example.Assignment.project.Dto.Booking;
import com.example.Assignment.project.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final BookingService bookingService;

    public MemberController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

/**
 * Handles the HTTP POST request for creating a new booking.
 * 
 * @param booking the booking object with details like memberName, classId, and participationDate.
 * @return ResponseEntity containing the created Booking object if successful.
 * @throws IllegalArgumentException if the participation date is outside the class date range or the class is at full capacity.
 */

    @PostMapping

    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }
}
