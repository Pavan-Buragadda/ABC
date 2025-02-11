package com.example.Assignment.project.controller;

import com.example.Assignment.project.Dto.BookingModelResponse;
import com.example.Assignment.project.Dto.GymClass;
import com.example.Assignment.project.service.BookingService;
import com.example.Assignment.project.service.GymClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private GymClassService gymClassService;

    public void GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }
    @Autowired
    private BookingService bookingService;

    public OwnerController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    /**
     * Create a new gym class.
     * @param gymClass The gym class to be created.
     * @return The created gym class.
     */
    @PostMapping
    public ResponseEntity<GymClass> createClass(@Valid @RequestBody GymClass gymClass) {
        return ResponseEntity.ok(gymClassService.createClass(gymClass));
    }
    /**
     * Search for bookings based on the given criteria.
     * 
     * @param memberName Optional. The name of the member to filter bookings by.
     * @param startDate Optional. The start date to filter bookings from (inclusive).
     * @param endDate Optional. The end date to filter bookings to (inclusive).
     * @return A ResponseEntity containing a list of BookingModelResponse objects 
     *         that match the search criteria.
     */

    @GetMapping
    public ResponseEntity<List<BookingModelResponse>> searchBookings(
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(bookingService.searchBookings(memberName, startDate, endDate));
    }

}
