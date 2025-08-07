package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.services.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AirportServicePostgreImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServicePostgreImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    /**
     * @param airport
     * @return
     */
    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Airport getAirportById(UUID id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Airport> getAllAirports() {
        return List.of();
    }

    /**
     * @param id
     * @param airport
     * @return
     */
    @Override
    public Airport updateAirport(UUID id, Airport airport) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Airport deleteAirport(UUID id) {
        return null;
    }
}
