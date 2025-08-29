package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.dto.BookingDTO;
import com.umakant.airlinebooking.model.AirlineUser;
import com.umakant.airlinebooking.model.Booking;
import com.umakant.airlinebooking.model.Flight;
import com.umakant.airlinebooking.model.Passenger;
import com.umakant.airlinebooking.repositories.BookingRepository;
import com.umakant.airlinebooking.repositories.FlightRepository;
import com.umakant.airlinebooking.repositories.PassengerRepository;
import com.umakant.airlinebooking.repositories.AirlineUserRepository;
import com.umakant.airlinebooking.services.BookingService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BookingServicePostgresImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final AirlineUserRepository airlineUserRepository;

    public BookingServicePostgresImpl(BookingRepository bookingRepository, FlightRepository flightRepository, PassengerRepository passengerRepository, AirlineUserRepository airlineUserRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.airlineUserRepository = airlineUserRepository;
    }

    /**
     * @param newBooking
     * @return
     */
    @Override
    public BookingDTO.GetBookingResponse createBooking(BookingDTO.NewBookingDTO newBooking) throws BadRequestException {
        UUID airlineUserId = newBooking.airlineUserId;
        Set<UUID> airlineUserIdPassengerList = passengerRepository.findAllPassengerIdsByAirlineUser_UserId(airlineUserId);
        List<UUID> passsengerList = newBooking.passengerIds;
        for(UUID passengerId : passsengerList) {
            System.out.println("Passenger Id is: " + passengerId);
            if(!airlineUserIdPassengerList.contains(passengerId)){
                throw new BadRequestException("Unkown passenger in the list");
            }
        }
        Booking booking = newBooking.toBooking();
        return BookingDTO.GetBookingResponse.toBookingResponse(bookingRepository.save(booking));
    }

    /**
     * @param bookingId
     * @return
     */
    @Override
    public BookingDTO.GetBookingResponse getBookingById(UUID bookingId) {
        Booking booking = bookingRepository.getBookingByBookingId(bookingId);
        return BookingDTO.GetBookingResponse.toBookingResponse(booking);
    }

    /**
     * @return
     */
    @Override
    public List<BookingDTO.GetBookingResponse> getAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        return BookingDTO.GetBookingResponse.toBookingResponseList(bookingList);
    }

    /**
     * @param bookingId
     * @param newBooking
     * @return
     */
    @Override
    public BookingDTO.GetBookingResponse updateBooking(UUID bookingId, BookingDTO.NewBookingDTO newBooking) {
//        AirlineUser airlineUser = airlineUserRepository.getUserByUserId(newBooking.airlineUserId);
//        Flight flight = flightRepository.getFlightByFlightId(newBooking.flightId);
//        List<Passenger> passengerList = passengerRepository.findByPassengerIdIn(newBooking.passengerIds);
        Booking updatedBooking = newBooking.toBooking();
        updatedBooking.setBookingId(bookingId);
        if(bookingRepository.existsByBookingId(bookingId)){
            return BookingDTO.GetBookingResponse.toBookingResponse(bookingRepository.save(updatedBooking));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Booking not found!!");
    }

    /**
     * @param bookingId
     * @return
     */
    @Override
    public BookingDTO.GetBookingResponse deleteBooking(UUID bookingId) {
        if(bookingRepository.existsByBookingId(bookingId)){
            Booking booking = bookingRepository.getBookingByBookingId(bookingId);
            bookingRepository.deleteById(bookingId);
            return BookingDTO.GetBookingResponse.toBookingResponse(booking);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Booking not found!!");
    }
}
