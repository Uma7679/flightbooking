package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.AirlineUserDTO;
import com.umakant.airlinebooking.model.AirlineUser;

import java.util.UUID;

public interface AirlineUserService {
    //create
    AirlineUserDTO.AirlineUserResponse createUser(AirlineUserDTO.NewUser newAirlineUser);

    //get
    AirlineUserDTO.AirlineUserResponse getUser(UUID airlineUserId);

    //update
    AirlineUserDTO.AirlineUserResponse updateUser(UUID airlineUserId, AirlineUserDTO.NewUser updatedAirlineUser);

    //delete
    AirlineUserDTO.AirlineUserResponse deleteUser(UUID airlineUserId);
}
