package com.example.Assignment.project.service;

import com.example.Assignment.project.Dto.Booking;
import com.example.Assignment.project.Dto.BookingModelResponse;
import com.example.Assignment.project.Dto.GymClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final ConcurrentHashMap<Long, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    private final GymClassService gymClassService;

    public BookingService(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    /**
     * Create a new booking in the system.
     *
     * @param booking the booking to create
     * @return the created booking with an ID
     * @throws IllegalArgumentException if the booking is for a date outside the class date range, or if the
     *                                  class is already at capacity for the given date
     */
    public Booking createBooking(Booking booking) {
        GymClass gymClass = gymClassService.getClass(booking.getClassId());

        if (booking.getParticipationDate().isBefore(gymClass.getStartDate()) ||
                booking.getParticipationDate().isAfter(gymClass.getEndDate())) {
            throw new IllegalArgumentException("Participation date must be within class date range");
        }

        long bookingsForClass = getBookingsForClassAndDate(booking.getClassId(), booking.getParticipationDate());
        if (bookingsForClass >= gymClass.getCapacity()) {
            throw new IllegalArgumentException("Class is at capacity for this date");
        }

        booking.setId(idGenerator.incrementAndGet());
        bookings.put(booking.getId(), booking);
        return booking;
    }

    /**
     * Return the number of bookings for a given class and date.
     *
     * @param classId the ID of the class
     * @param date the date for which to count bookings
     * @return the number of bookings for the given class and date
     */
    private long getBookingsForClassAndDate(Long classId, LocalDate date) {
        return bookings.values().stream()
                .filter(b -> b.getClassId().equals(classId) && b.getParticipationDate().equals(date))
                .count();
    }

    /**
     * Return a list of bookings that match the given search criteria.
     *
     * @param memberName the member name to search for. If null, matches all bookings.
     * @param startDate the start date of the range to search for. If null, matches all bookings.
     * @param endDate the end date of the range to search for. If null, matches all bookings.
     * @return a list of bookings that match the given search criteria
     */
    public List<BookingModelResponse> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        return bookings.values().stream()
                .filter(booking ->
                        (memberName == null || booking.getMemberName().equals(memberName)) &&
                                (startDate == null || !booking.getParticipationDate().isBefore(startDate)) &&
                                (endDate == null || !booking.getParticipationDate().isAfter(endDate)))
                .map(this::convertToBookingResponse)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Booking to a BookingModelResponse.
     * @param booking the booking to convert
     * @return a BookingModelResponse containing the same information as the given booking
     */
    private BookingModelResponse convertToBookingResponse(Booking booking) {
        GymClass gymClass = gymClassService.getClass(booking.getClassId());
        BookingModelResponse response = new BookingModelResponse();
        response.setId(booking.getId());
        response.setMemberName(booking.getMemberName());
        response.setClassName(gymClass.getName());
        response.setClassStartTime(gymClass.getStartTime());
        response.setParticipationDate(booking.getParticipationDate());
        return response;
    }
}
