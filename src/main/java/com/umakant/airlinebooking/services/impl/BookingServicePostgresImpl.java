package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.model.Booking;
import com.umakant.airlinebooking.services.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServicePostgresImpl implements BookingService {
    /**
     * @param booking
     * @return
     */
    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Booking getBookingById(UUID id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    /**
     * @param id
     * @param booking
     * @return
     */
    @Override
    public Booking updateBooking(UUID id, Booking booking) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Booking deleteBooking(UUID id) {
        return null;
    }
}
