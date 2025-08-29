package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.BookingDTO;
import com.umakant.airlinebooking.model.Booking;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    //create booking
    BookingDTO.GetBookingResponse createBooking(BookingDTO.NewBookingDTO booking) throws BadRequestException;

    //get booking details by id
    BookingDTO.GetBookingResponse getBookingById(UUID bookingId);

    //get all bookings
    List<BookingDTO.GetBookingResponse> getAllBookings();

    //update booking details
    BookingDTO.GetBookingResponse updateBooking(UUID bookingId, BookingDTO.NewBookingDTO booking);

    //delete Booking
    BookingDTO.GetBookingResponse deleteBooking(UUID bookingId);
}
