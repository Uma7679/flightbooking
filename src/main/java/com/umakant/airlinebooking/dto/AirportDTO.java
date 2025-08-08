package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.Airport;

import java.util.UUID;

public class AirportDTO {
    public static class NewAirportDTO{
        String airportName;
        String latitude;
        String longitude;

        public Airport toAirport(){
            return new Airport(1L , airportName, latitude, longitude);
        }
    }
}
