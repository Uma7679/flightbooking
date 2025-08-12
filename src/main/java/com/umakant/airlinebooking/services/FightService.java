package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.FlightDTO;
import com.umakant.airlinebooking.model.Flight;

import java.util.List;
import java.util.UUID;

public interface FightService {
    //create flight
    Flight createFlight(FlightDTO.NewFlightDTO flight);

    //get flight
    FlightDTO.GetFlightDTOResponse getFlightById(UUID flightId);

    //get all flights
    List<FlightDTO.GetFlightDTOResponse> getAllFlights();

    //update flight
    FlightDTO.GetFlightDTOResponse updateFlight(UUID flightId, FlightDTO.NewFlightDTO flight);

    //delete flight
    FlightDTO.GetFlightDTOResponse deleteFlightById(UUID flightId);
}
