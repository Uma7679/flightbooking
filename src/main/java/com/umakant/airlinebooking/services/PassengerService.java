package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.dto.PassengerDTO;
import com.umakant.airlinebooking.model.Passenger;

import java.util.List;
import java.util.UUID;

public interface PassengerService {
    //create
    PassengerDTO.GetPassengerResponse createPassenger(PassengerDTO.NewPassengerDTO newPassenger);

    //get
    PassengerDTO.GetPassengerResponse getPassengerById(UUID passengerId);

    //get List
    List<PassengerDTO.GetPassengerResponse> getPassengerList();

    //update
    PassengerDTO.GetPassengerResponse updatePassenger(UUID passengerId, PassengerDTO.NewPassengerDTO passenger);

    //delete
    PassengerDTO.GetPassengerResponse deletePassenger(UUID passengerId);
}
