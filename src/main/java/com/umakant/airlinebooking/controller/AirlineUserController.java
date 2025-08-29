package com.umakant.airlinebooking.controller;


import com.umakant.airlinebooking.dto.AirlineUserDTO;
import com.umakant.airlinebooking.model.AirlineUser;
import com.umakant.airlinebooking.services.AirlineUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class AirlineUserController {
    private final AirlineUserService airlineUserService;

    public AirlineUserController(AirlineUserService airlineUserService) {
        this.airlineUserService = airlineUserService;
    }

    //create User
    @PostMapping("/")
    public ResponseEntity<AirlineUserDTO.AirlineUserResponse> createUser(@RequestBody AirlineUserDTO.NewUser newAirlineUser){
        AirlineUserDTO.AirlineUserResponse airlineUser = airlineUserService.createUser(newAirlineUser);
        return ResponseEntity.created(URI.create("/api/user/")).body(airlineUser);
    }

    //get user
    @GetMapping("/{airlineUserId}")
    public ResponseEntity<AirlineUserDTO.AirlineUserResponse> getUser(@PathVariable UUID airlineUserId){
        return ResponseEntity.ok().body(airlineUserService.getUser(airlineUserId));
    }

    //update user
    @PutMapping("/{airlineUserId}")
    public ResponseEntity<AirlineUserDTO.AirlineUserResponse> updateUser(@PathVariable UUID airlineUserId, @RequestBody AirlineUserDTO.NewUser updatedAirlineUser){
        return ResponseEntity.ok().body(airlineUserService.updateUser(airlineUserId, updatedAirlineUser));
    }

    //delete user
    @DeleteMapping("/{airlineUserId}")
    public ResponseEntity<AirlineUserDTO.AirlineUserResponse> deleteUser(@PathVariable UUID airlineUserId){
        return ResponseEntity.ok().body(airlineUserService.deleteUser(airlineUserId));
    }
}
