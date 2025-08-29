package com.umakant.airlinebooking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    @JoinColumn(name = "airline_user_id", nullable = false)
    private UUID airlineUserId;

    @JoinColumn(name = "flight_flight_id")
    private UUID flightId;

    @JoinColumn(name = "passenger_passenger_id")
    private List<UUID> passengerIds;

    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;


    //constructors
    //getter and setters
    public Booking(UUID bookingId, UUID airlineUserId, UUID flightId, List<UUID> passengerIds, LocalDateTime bookingDate, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.airlineUserId = airlineUserId;
        this.flightId = flightId;
        this.passengerIds = passengerIds;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public Booking() {
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getAirlineUserId() {
        return airlineUserId;
    }

    public void setAirlineUserId(UUID airlineUserId) {
        this.airlineUserId = airlineUserId;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public List<UUID> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<UUID> passengerIds) {
        this.passengerIds = passengerIds;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
