package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.services.AirportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    //create airport
    @PostMapping("/")
    public Airport createAirport(@RequestBody AirportDTO.NewAirportDTO newAirport){
        return airportService.createAirport(newAirport);
    }

    //get airport by id
    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable UUID id){
        return airportService.getAirportById(id);
    }

    @GetMapping("/")
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    //Update airport
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable UUID id, Airport airport){
        return airportService.updateAirport(id, airport);
    }

    //Delete airport
    @DeleteMapping("/{id}")
    public Airport deleteAirport(@PathVariable UUID id){
        return airportService.deleteAirport(id);
    }
}
