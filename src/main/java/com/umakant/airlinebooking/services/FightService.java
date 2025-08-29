package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.FlightDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FightService {
    //create flight
    FlightDTO.GetFlightDTOResponse createFlight(FlightDTO.NewFlightDTO flight);

    //get flight
    FlightDTO.GetFlightDTOResponse getFlightById(UUID flightId);

    //get all flights
    List<FlightDTO.GetFlightDTOResponse> getAllFlights(Pageable pageable);

    //get flights from source to destination
    List<FlightDTO.GetFlightDTOResponse> getFlightsFromSourceToDestination(FlightDTO.NewFlightDTO getFlight, Pageable pageable);

    //find flights with stops
    List<FlightDTO.GetFlightDTOResponse> findFlightsWithStops(FlightDTO.NewFlightDTO getFlight, Pageable pageable);

    //get Connecting flights
    List<FlightDTO.GetConnectingFlightDTOResponse> getConnectingFlights(FlightDTO.FlightSearchRequestDTO searchRequest);

    //update flight
    FlightDTO.GetFlightDTOResponse updateFlight(UUID flightId, FlightDTO.NewFlightDTO flight);

    //delete flight
    FlightDTO.GetFlightDTOResponse deleteFlightById(UUID flightId);

}
