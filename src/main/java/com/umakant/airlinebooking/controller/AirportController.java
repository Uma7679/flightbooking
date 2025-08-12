package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.services.AirportService;
import jakarta.validation.Valid;
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
    public Airport createAirport(@Valid @RequestBody AirportDTO.NewAirportDTO newAirport){
        return airportService.createAirport(newAirport);
    }

    //get airport by id
    @GetMapping("/{airportId}")
    public AirportDTO.GetAirportDTOResponse getAirport(@PathVariable UUID airportId){
        return airportService.getAirportById(airportId);
    }

    @GetMapping("/")
    public List<AirportDTO.GetAirportDTOResponse> getAllAirports(){
        return airportService.getAllAirports();
    }

    //Update airport
    @PutMapping("/{airportId}")
    public AirportDTO.GetAirportDTOResponse updateAirport(@PathVariable UUID airportId, @Valid @RequestBody AirportDTO.NewAirportDTO updatedAirport){
        return airportService.updateAirport(airportId, updatedAirport);
    }

    //Delete airport
    @DeleteMapping("/{airportId}")
    public AirportDTO.GetAirportDTOResponse deleteAirport(@PathVariable UUID airportId){
        return airportService.deleteAirport(airportId);
    }
}
