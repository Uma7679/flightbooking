package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.model.Booking;
import com.umakant.airlinebooking.services.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //create booking
    @PostMapping("/")
    public Booking createBooking(Booking booking){
        return bookingService.createBooking(booking);
    }

    //get booking
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable UUID id){
        return bookingService.getBookingById(id);
    }

    //get All bookings
    @GetMapping("/")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    //update booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable UUID id, Booking booking){
        return bookingService.updateBooking(id, booking);
    }

    //delete booking
    @DeleteMapping("/{id}")
    public Booking deleteBooking(@PathVariable UUID id){
        return bookingService.deleteBooking(id);
    }
}
