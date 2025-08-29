package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.services.AirportService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<AirportDTO.GetAirportDTOResponse> createAirport(@Valid @RequestBody AirportDTO.NewAirportDTO newAirport){
        return ResponseEntity.created(URI.create("/api/airport/")).body(airportService.createAirport(newAirport));
    }

    //get airport by id
    @GetMapping("/{airportId}")
    public ResponseEntity<AirportDTO.GetAirportDTOResponse> getAirport(@PathVariable UUID airportId){
        return ResponseEntity.ok().body(airportService.getAirportById(airportId));
    }

    @GetMapping("/")
    public ResponseEntity<List<AirportDTO.GetAirportDTOResponse>> getAllAirports(){
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }

    //Update airport
    @PutMapping("/{airportId}")
    public ResponseEntity<AirportDTO.GetAirportDTOResponse> updateAirport(@PathVariable UUID airportId, @Valid @RequestBody AirportDTO.NewAirportDTO updatedAirport){
        return ResponseEntity.ok().body(airportService.updateAirport(airportId, updatedAirport));
    }

    //Delete airport
    @DeleteMapping("/{airportId}")
    public ResponseEntity<AirportDTO.GetAirportDTOResponse> deleteAirport(@PathVariable UUID airportId){
        return ResponseEntity.ok().body(airportService.deleteAirport(airportId));
    }
}
