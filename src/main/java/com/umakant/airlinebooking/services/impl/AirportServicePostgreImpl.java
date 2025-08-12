package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.services.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
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
        return airportRepository.save(airport);
    }

    /**
     * @param airportId
     * @return
     */
    @Override
    public AirportDTO.GetAirportDTOResponse getAirportById(UUID airportId) {
        Airport airport = airportRepository.getAirportByAirportId(airportId);
        return AirportDTO.GetAirportDTOResponse.getAirportDTO(airport);
    }

    /**
     * @return
     */
    @Override
    public List<AirportDTO.GetAirportDTOResponse> getAllAirports() {
        List<Airport> airportList = airportRepository.findAll();
        return AirportDTO.GetAirportDTOResponse.getAirportDTOList(airportList);
    }

    /**
     * @param airportId
     * @param airport
     * @return
     */
    @Override
    public AirportDTO.GetAirportDTOResponse updateAirport(UUID airportId, AirportDTO.NewAirportDTO airport) {
        Airport updatedAirport = airport.toAirport();
        updatedAirport.setAirportId(airportId);
        if(airportRepository.existsByAirportId(airportId)){
            return AirportDTO.GetAirportDTOResponse.getAirportDTO(airportRepository.save(updatedAirport));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Airport not found!!");
    }

    /**
     * @param airportId
     * @return
     */
    @Override
    public AirportDTO.GetAirportDTOResponse deleteAirport(UUID airportId) {
        if(airportRepository.existsByAirportId(airportId)){
            Airport airport = airportRepository.getAirportByAirportId(airportId);
            airportRepository.deleteById(airportId);
            return AirportDTO.GetAirportDTOResponse.getAirportDTO(airport);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Airport not found!!");
    }
}
