package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.model.Airport;

import java.util.List;
import java.util.UUID;

public interface AirportService {
    //create
    Airport createAirport(Airport airport);

    //get an airport using id
    Airport getAirportById(UUID id);

    //get all airports
    List<Airport> getAllAirports();

    //update
    Airport updateAirport(UUID id, Airport airport);

    //delete
    Airport deleteAirport(UUID id);
}
