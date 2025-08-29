package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingDTO {
    public static class NewBookingDTO {
        public UUID airlineUserId;
        public UUID flightId;
        public List<UUID> passengerIds;
        public BookingStatus bookingStatus;

        public Booking toBooking() {
            return new Booking(null, airlineUserId, flightId, passengerIds, null, bookingStatus);
        }
    }

    public static class GetBookingResponse {
        public UUID bookingId;
        public UUID airlineUserId;
        public UUID flightId;
        public List<UUID> passengerIds;
        public LocalDateTime bookingDate;
        public BookingStatus bookingStatus;

        public GetBookingResponse(UUID bookingId, UUID airlineUserId, UUID flightId, List<UUID> passengerIds, LocalDateTime bookingDate, BookingStatus bookingStatus) {
            this.bookingId = bookingId;
            this.airlineUserId = airlineUserId;
            this.flightId = flightId;
            this.passengerIds = passengerIds;
            this.bookingDate = bookingDate;
            this.bookingStatus = bookingStatus;
        }

        public GetBookingResponse() {
        }

        public static GetBookingResponse toBookingResponse(Booking booking) {
            return new GetBookingResponse(booking.getBookingId(), booking.getAirlineUserId(), booking.getFlightId(), booking.getPassengerIds(), booking.getBookingDate(), booking.getBookingStatus());
        }

        public static List<GetBookingResponse> toBookingResponseList(List<Booking> bookingList) {
            return bookingList.stream().map(booking ->
                    new GetBookingResponse(booking.getBookingId(), booking.getAirlineUserId(), booking.getFlightId(), booking.getPassengerIds(), booking.getBookingDate(), booking.getBookingStatus())
            ).toList();
        }
    }
}
