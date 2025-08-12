package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;

import java.util.List;
import java.util.UUID;

public interface AirportService {
    //create
    Airport createAirport(AirportDTO.NewAirportDTO airport);

    //get an airport using id
    AirportDTO.GetAirportDTOResponse getAirportById(UUID airportId);

    //get all airports
    List<AirportDTO.GetAirportDTOResponse> getAllAirports();

    //update
    AirportDTO.GetAirportDTOResponse updateAirport(UUID id, AirportDTO.NewAirportDTO updatedAirport);

    //delete
    AirportDTO.GetAirportDTOResponse deleteAirport(UUID id);
}
