package com.umakant.airlinebooking.services.impl;

import com.umakant.airlinebooking.airlineExceptions.FlightNotFoundException;
import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.dto.FlightDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.model.Flight;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.repositories.FlightRepository;
import com.umakant.airlinebooking.services.FightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    public FlightDTO.GetFlightDTOResponse createFlight(FlightDTO.NewFlightDTO newFlight) {
        Airport sourceAirport = airportRepository.getAirportByAirportId(newFlight.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(newFlight.destinationAirportId);
        List<Airport> stopAirports = new ArrayList<>();
        if (newFlight.stopAirportIds != null && !newFlight.stopAirportIds.isEmpty()) {
            stopAirports = airportRepository.findAllById(newFlight.stopAirportIds);
        }
        Flight flight = newFlight.toFlight(sourceAirport, destinationAirport, stopAirports);
        return FlightDTO.GetFlightDTOResponse.getFlightDTO(flightRepository.save(flight));
    }

    /**
     * @param flightId
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse getFlightById(UUID flightId) {
        if (!flightRepository.existsByFlightId(flightId)) {
            throw new FlightNotFoundException("Flight not found with ID: " + flightId);
        }
        Flight flight = flightRepository.getFlightByFlightId(flightId);
        return FlightDTO.GetFlightDTOResponse.getFlightDTO(flight);
    }

    /**
     * @return
     */
    @Override
    public List<FlightDTO.GetFlightDTOResponse> getAllFlights(Pageable pageable) {
        Page<Flight> flightPage = flightRepository.findAll(pageable);
        List<Flight> flightList = flightPage.getContent();
        return FlightDTO.GetFlightDTOResponse.getFlightDTOList(flightList);
    }

    /**
     * @param getFlight
     * @return
     */
    @Override
    public List<FlightDTO.GetFlightDTOResponse> getFlightsFromSourceToDestination(FlightDTO.NewFlightDTO getFlight, Pageable pageable) {
        Airport sourceAirport = airportRepository.getAirportByAirportId(getFlight.sourceAirportId);
        Airport destinatioAirport = airportRepository.getAirportByAirportId(getFlight.destinationAirportId);
        Page<Flight> flightPage = flightRepository.findAllBySourceAirportAndDestinationAirport(sourceAirport, destinatioAirport, pageable);
        List<Flight> flightList = flightPage.getContent();
        return FlightDTO.GetFlightDTOResponse.getFlightDTOList(flightList);
    }

    //find flight with stops
    public List<FlightDTO.GetFlightDTOResponse> findFlightsWithStops(FlightDTO.NewFlightDTO getFlight, Pageable pageable) {
        Airport sourceAirport = airportRepository.getAirportByAirportId(getFlight.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(getFlight.destinationAirportId);
        Page<Flight> directFlights = flightRepository.findAllBySourceAirportAndDestinationAirport(sourceAirport, destinationAirport, pageable);

        Page<Flight> multiLegFlights = flightRepository.findFlightsWithIntermediateStops(sourceAirport, destinationAirport, pageable);

        List<Flight> allFlights = new ArrayList<>();
        allFlights.addAll(directFlights.getContent());
        allFlights.addAll(multiLegFlights.getContent());

        return FlightDTO.GetFlightDTOResponse.getFlightDTOList(allFlights);
    }

    //get connecting flights
    public List<FlightDTO.GetConnectingFlightDTOResponse> getConnectingFlights(FlightDTO.FlightSearchRequestDTO searchRequest) {
        FlightDTO.NewFlightDTO search = searchRequest.getFlightSearch();

        // Get pagination
        Pageable pageable = PageRequest.of(
                searchRequest.getPage(),
                searchRequest.getSize(),
                searchRequest.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC,
                searchRequest.getSortBy()
        );

        Airport sourceAirport = airportRepository.getAirportByAirportId(search.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(search.destinationAirportId);

        List<Flight> firstLegs = flightRepository.findBySourceAirport(sourceAirport);

        List<FlightDTO.GetConnectingFlightDTOResponse> connectingFlights = new ArrayList<>();

        for (Flight first : firstLegs) {
            Airport layoverAirport = first.getDestinationAirport();

            List<Flight> secondLegs = flightRepository.findBySourceAirportAndDestinationAirport(layoverAirport, destinationAirport);

            for (Flight second : secondLegs) {
                if (second.getDepartureTime().isAfter(first.getArrivalTime().plusMinutes(30)) &&
                        second.getDepartureTime().isBefore(first.getArrivalTime().plusHours(6))) {

                    connectingFlights.add(
                            new FlightDTO.GetConnectingFlightDTOResponse(
                                    FlightDTO.GetFlightDTOResponse.getFlightDTO(first),
                                    FlightDTO.GetFlightDTOResponse.getFlightDTO(second)
                            )
                    );
                }
            }
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), connectingFlights.size());

        if (start >= end) {
            return Collections.emptyList();
        }

        return connectingFlights.subList(start, end);
    }


    /**
     * @param flightId
     * @param flight
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse updateFlight(UUID flightId, FlightDTO.NewFlightDTO flight) {
        if (!flightRepository.existsByFlightId(flightId)) {
            throw new FlightNotFoundException("Flight not found with ID: " + flightId);
        }
        Airport sourceAirport = airportRepository.getAirportByAirportId(flight.sourceAirportId);
        Airport destinationAirport = airportRepository.getAirportByAirportId(flight.destinationAirportId);
        List<Airport> stopAirports = new ArrayList<>();
        if (flight.stopAirportIds != null && !flight.stopAirportIds.isEmpty()) {
            stopAirports = airportRepository.findAllById(flight.stopAirportIds);
        }
        Flight updatedFlight = flight.toFlight(sourceAirport, destinationAirport, stopAirports);
        updatedFlight.setFlightId(flightId);
        if (flightRepository.existsByFlightId(flightId)) {
            return FlightDTO.GetFlightDTOResponse.getFlightDTO(flightRepository.save(updatedFlight));
        } else throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Flight not found!!");
    }

    /**
     * @param flightId
     * @return
     */
    @Override
    public FlightDTO.GetFlightDTOResponse deleteFlightById(UUID flightId) {
        if (!flightRepository.existsByFlightId(flightId)) {
            throw new FlightNotFoundException("Flight not found with ID: " + flightId);
        }
        if (flightRepository.existsByFlightId(flightId)) {
            Flight flight = flightRepository.getFlightByFlightId(flightId);
            flightRepository.deleteById(flightId);
            return FlightDTO.GetFlightDTOResponse.getFlightDTO(flight);
        } else throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Flight not found!!");
    }


}
