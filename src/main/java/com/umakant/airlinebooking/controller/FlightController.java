package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.FlightDTO;
import com.umakant.airlinebooking.model.Flight;
import com.umakant.airlinebooking.services.FightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private final FightService flightService;

    public FlightController(FightService fightService) {
        this.flightService = fightService;
    }

    //create
    @PostMapping("/")
    public Flight createFlight(@RequestBody FlightDTO.NewFlightDTO newFlight){
        return flightService.createFlight(newFlight);
    }

    //get flight by id
    @GetMapping("/{flightId}")
    public FlightDTO.GetFlightDTOResponse getFlight(@PathVariable UUID flightId){
        return flightService.getFlightById(flightId);
    }

    //get all flights
    @GetMapping("/")
    public List<FlightDTO.GetFlightDTOResponse> getAllFlights(){
        return flightService.getAllFlights();
    }

    //update
    @PutMapping("/{flightId}")
    public FlightDTO.GetFlightDTOResponse updateFlight(@PathVariable UUID flightId, FlightDTO.NewFlightDTO flight){
        return flightService.updateFlight(flightId, flight);
    }

    //delete
    @DeleteMapping("/{flightId}")
    public FlightDTO.GetFlightDTOResponse deleteFlight(@PathVariable UUID flightId){
        return flightService.deleteFlightById(flightId);
    }
}
