package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.FlightDTO;
import com.umakant.airlinebooking.services.FightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private final FightService flightService;
    private Logger logger = LoggerFactory.getLogger(FlightController.class);

    public FlightController(FightService fightService) {
        this.flightService = fightService;
    }

    //create
    @PostMapping("/")
    public ResponseEntity<FlightDTO.GetFlightDTOResponse> createFlight(@RequestBody FlightDTO.NewFlightDTO newFlight){
        return ResponseEntity.created(URI.create("/api/flight/")).body(flightService.createFlight(newFlight));
    }

    //get flight by id
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO.GetFlightDTOResponse> getFlight(@PathVariable UUID flightId){
        return ResponseEntity.ok().body(flightService.getFlightById(flightId));
    }

    //get all flights -> change to pagination
    @GetMapping("/")
    public ResponseEntity<List<FlightDTO.GetFlightDTOResponse>> getAllFlights(
            @RequestBody FlightDTO.FlightSearchRequestDTO requestDTO
    ){
        FlightDTO.NewFlightDTO getFlight = requestDTO.getFlightSearch();
        Sort sort = requestDTO.isAscending()
                ? Sort.by(requestDTO.getSortBy()).ascending()
                : Sort.by(requestDTO.getSortBy()).descending();

        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);

        return ResponseEntity.ok().body(flightService.getAllFlights(pageable));
    }

    //get flight from source to destination
    @PostMapping("/searchFlights")
    public ResponseEntity<List<FlightDTO.GetFlightDTOResponse>> getFlightsFromSourceToDestination(
            @RequestBody FlightDTO.FlightSearchRequestDTO requestDTO
            ){
        FlightDTO.NewFlightDTO getFlight = requestDTO.getFlightSearch();
        Sort sort = requestDTO.isAscending()
                ? Sort.by(requestDTO.getSortBy()).ascending()
                : Sort.by(requestDTO.getSortBy()).descending();

        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);
        return ResponseEntity.ok().body(flightService.getFlightsFromSourceToDestination(getFlight, pageable));
    }

    //find flight with stops
    @PostMapping("/findFlights")
    public ResponseEntity<List<FlightDTO.GetFlightDTOResponse>> findFlightsFromSourceToDestination(
            @RequestBody FlightDTO.FlightSearchRequestDTO requestDTO
    ){
        FlightDTO.NewFlightDTO getFlight = requestDTO.getFlightSearch();
        Sort sort = requestDTO.isAscending()
                ? Sort.by(requestDTO.getSortBy()).ascending()
                : Sort.by(requestDTO.getSortBy()).descending();

        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);
        return ResponseEntity.ok().body(flightService.getFlightsFromSourceToDestination(getFlight, pageable));
    }

    //get connecting flights
    @PostMapping("/getFlights")
    public ResponseEntity<List<FlightDTO.GetConnectingFlightDTOResponse>> getConnectingFlights(
            @RequestBody FlightDTO.FlightSearchRequestDTO searchRequest
    ){
        return ResponseEntity.ok().body(flightService.getConnectingFlights(searchRequest));
    }


    //update
    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDTO.GetFlightDTOResponse> updateFlight(@PathVariable UUID flightId, FlightDTO.NewFlightDTO flight){
        return ResponseEntity.ok().body(flightService.updateFlight(flightId, flight));
    }

    //delete
    @DeleteMapping("/{flightId}")
    public ResponseEntity<FlightDTO.GetFlightDTOResponse> deleteFlight(@PathVariable UUID flightId){
        return ResponseEntity.ok().body(flightService.deleteFlightById(flightId));
    }
}
