package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.BookingDTO;
import com.umakant.airlinebooking.model.Booking;
import com.umakant.airlinebooking.services.BookingService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<BookingDTO.GetBookingResponse> createBooking(@RequestBody BookingDTO.NewBookingDTO newBooking) throws BadRequestException {
        return ResponseEntity.created(URI.create("/api/booking/")).body(bookingService.createBooking(newBooking));
    }

    //get booking
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO.GetBookingResponse> getBooking(@PathVariable UUID bookingId){
        return ResponseEntity.ok().body(bookingService.getBookingById(bookingId));
    }

    //get All bookings
    @GetMapping("/")
    public ResponseEntity<List<BookingDTO.GetBookingResponse>> getAllBookings(){
        return ResponseEntity.ok().body(bookingService.getAllBookings());
    }

    //update booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO.GetBookingResponse> updateBooking(@PathVariable UUID bookingId, @RequestBody BookingDTO.NewBookingDTO booking){
        return ResponseEntity.ok().body(bookingService.updateBooking(bookingId, booking));
    }

    //delete booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingDTO.GetBookingResponse> deleteBooking(@PathVariable UUID bookingId){
        return ResponseEntity.ok().body(bookingService.deleteBooking(bookingId));
    }
}
