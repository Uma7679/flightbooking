package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.PassengerDTO;
import com.umakant.airlinebooking.model.Passenger;
import com.umakant.airlinebooking.services.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    //create
    @PostMapping("/")
    public ResponseEntity<PassengerDTO.GetPassengerResponse> createPassenger(@RequestBody PassengerDTO.NewPassengerDTO newPassenger){
        return ResponseEntity.created(URI.create("/api/passenger/")).body(passengerService.createPassenger(newPassenger));
    }

    //get
    @GetMapping("/{passengerId}")
    public ResponseEntity<PassengerDTO.GetPassengerResponse> getPassenger(@PathVariable UUID passengerId){
        return ResponseEntity.ok().body(passengerService.getPassengerById(passengerId));
    }

    //update
    @PutMapping("/{passengerId}")
    public ResponseEntity<PassengerDTO.GetPassengerResponse> updatePassenger(@PathVariable UUID passengerId, @RequestBody PassengerDTO.NewPassengerDTO passenger){
        return ResponseEntity.ok().body(passengerService.updatePassenger(passengerId, passenger));
    }

    //delete
    @DeleteMapping("/{passengerId}")
    public ResponseEntity<PassengerDTO.GetPassengerResponse> deletePassenger(@PathVariable UUID passengerId){
        return ResponseEntity.ok().body(passengerService.deletePassenger(passengerId) );
    }
}
