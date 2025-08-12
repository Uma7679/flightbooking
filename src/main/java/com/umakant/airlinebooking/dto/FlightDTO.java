package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.model.Flight;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.UUID;

public class FlightDTO {
    public static class NewFlightDTO{
        public UUID sourceAirportId;
        public UUID destinationAirportId;
        public int capacity;

        public Flight toFlight(Airport sourceAirport, Airport destinationAirport){
            return new Flight(null, sourceAirport, destinationAirport, capacity);
        }
    }

    public static class GetFlightDTOResponse{
        public UUID flightId;
        public Airport sourceAirport;
        public Airport destinationAirport;
        public int capacity;

        public GetFlightDTOResponse(UUID flightId, Airport sourceAirport, Airport destinationAirport, int capacity) {
            this.flightId = flightId;
            this.sourceAirport = sourceAirport;
            this.destinationAirport = destinationAirport;
            this.capacity = capacity;
        }

        public GetFlightDTOResponse() {
        }

        public static GetFlightDTOResponse getFlightDTO(Flight flight){
            return new GetFlightDTOResponse(flight.getFlightId(), flight.getSourceAirport(), flight.getDestinationAirport(), flight.getCapacity());
        }

        public static List<GetFlightDTOResponse> getFlightDTOList(List<Flight> flightList){
            return flightList.stream().map(flight ->
                    new GetFlightDTOResponse(flight.getFlightId(), flight.getSourceAirport(), flight.getDestinationAirport(), flight.getCapacity())
            ).toList();
        }
    }
}
