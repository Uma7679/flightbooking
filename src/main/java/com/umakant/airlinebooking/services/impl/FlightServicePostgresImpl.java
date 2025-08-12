package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.dto.FlightDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.model.Flight;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.repositories.FlightRepository;
import com.umakant.airlinebooking.services.FightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class FlightServicePostgresImpl implements FightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    Logger logger;

    public FlightServicePostgresImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.logger = LoggerFactory.getLogger("Flight details are ");
    }

    /**
     * @param newFlight
     * @return
     */
    @Override
    public Flight createFlight(FlightDTO.NewFlightDTO newFlight) {
        Airport sourceAirport = airportRepository.getAirportByAirportId(newFlight.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(newFlight.destinationAirportId);
        Flight flight = newFlight.toFlight(sourceAirport,destinationAirport);
        return flightRepository.save(flight);
    }

    /**
     * @param flightId
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse getFlightById(UUID flightId) {
        Flight flight = flightRepository.getFlightByFlightId(flightId);
        return FlightDTO.GetFlightDTOResponse.getFlightDTO(flight);
    }

    /**
     * @return
     */
    @Override
    public List<FlightDTO.GetFlightDTOResponse> getAllFlights() {
        List<Flight> flightList = flightRepository.findAll();
        return FlightDTO.GetFlightDTOResponse.getFlightDTOList(flightList);
    }

    /**
     * @param flightId
     * @param flight
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse updateFlight(UUID flightId, FlightDTO.NewFlightDTO flight) {
        Airport sourceAirport = airportRepository.getAirportByAirportId(flight.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(flight.destinationAirportId);
        Flight updatedFlight = flight.toFlight(sourceAirport,destinationAirport);
        updatedFlight.setFlightId(flightId);
        if(flightRepository.existsByFlightId(flightId)){
            return FlightDTO.GetFlightDTOResponse.getFlightDTO(flightRepository.save(updatedFlight));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Flight not found!!");
    }

    /**
     * @param flightId
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse deleteFlightById(UUID flightId) {
        if(flightRepository.existsByFlightId(flightId)){
            Flight flight = flightRepository.getFlightByFlightId(flightId);
            flightRepository.deleteById(flightId);
            return FlightDTO.GetFlightDTOResponse.getFlightDTO(flight);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"Flight not found!!");
    }
}
