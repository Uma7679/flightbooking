package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.services.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AirportServicePostgreImpl implements AirportService {
    private final AirportRepository airportRepository;
    Logger logger;

    public AirportServicePostgreImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
        this.logger = LoggerFactory.getLogger("Airport Service logger ");
    }

    /**
     * @param newAirport
     * @return
     */
    @Override
    public Airport createAirport(AirportDTO.NewAirportDTO newAirport) {
        Airport airport = newAirport.toAirport();
        logger.info(airport.getAirportId().toString());
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
