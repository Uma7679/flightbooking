package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.Airport;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.UUID;

public class AirportDTO {
    public static class NewAirportDTO {
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters")
        public String airportName;

        @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
        @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
        public Double latitude;

        @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
        @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
        public Double longitude;

        public Airport toAirport() {
            return new Airport(null, airportName, latitude, longitude);
        }
    }

    public static class GetAirportDTOResponse {
        public UUID airportId;
        public String airportName;
        public Double latitude;
        public Double longitude;

        public GetAirportDTOResponse(UUID airportId, String airportName, Double latitude, Double longitude) {
            this.airportId = airportId;
            this.airportName = airportName;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public GetAirportDTOResponse() {
        }

        public static GetAirportDTOResponse getAirportDTO(Airport airport) {
            return new GetAirportDTOResponse(airport.getAirportId(), airport.getAirportName(), airport.getLatitude(), airport.getLongitude());
        }

        public static List<GetAirportDTOResponse> getAirportDTOList(List<Airport> airportList) {
            return airportList.stream().map(airport ->
                    new GetAirportDTOResponse(airport.getAirportId(), airport.getAirportName() + " -IN", airport.getLatitude(), airport.getLongitude())
            ).toList();
        }
    }
}
