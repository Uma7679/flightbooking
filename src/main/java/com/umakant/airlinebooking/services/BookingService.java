package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    //create booking
    Booking createBooking(Booking booking);

    //get booking details by id
    Booking getBookingById(UUID id);

    //get all bookings
    List<Booking> getAllBookings();

    //update booking details
    Booking updateBooking(UUID id, Booking booking);

    //delete Booking
    Booking deleteBooking(UUID id);
}
