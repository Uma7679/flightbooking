package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.dto.PassengerDTO;
import com.umakant.airlinebooking.model.AirlineUser;
import com.umakant.airlinebooking.model.Passenger;
import com.umakant.airlinebooking.repositories.AirlineUserRepository;
import com.umakant.airlinebooking.repositories.PassengerRepository;
import com.umakant.airlinebooking.services.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;

@Service
public class PassengerServicePostgresImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final AirlineUserRepository airlineUserRepository;

    public PassengerServicePostgresImpl(PassengerRepository passengerRepository, AirlineUserRepository airlineUserRepository) {
        this.passengerRepository = passengerRepository;
        this.airlineUserRepository = airlineUserRepository;
    }

    /**
     * @param newPassenger
     * @return
     */
    @Override
    public PassengerDTO.GetPassengerResponse createPassenger(PassengerDTO.NewPassengerDTO newPassenger) {
        AirlineUser airlineUser = airlineUserRepository.getUserByUserId(newPassenger.airlineUserId);
        Passenger passenger = newPassenger.toPassenger(airlineUser);
        return PassengerDTO.GetPassengerResponse.getPassengerResponse(passengerRepository.save(passenger));
    }

    /**
     * @param passengerId
     * @return
     */
    @Override
    public PassengerDTO.GetPassengerResponse getPassengerById(UUID passengerId) {
        Passenger passenger = passengerRepository.getPassengerByPassengerId(passengerId);
        return PassengerDTO.GetPassengerResponse.getPassengerResponse(passenger);
    }

    /**
     * @return
     */
    @Override
    public List<PassengerDTO.GetPassengerResponse> getPassengerList() {
        List<Passenger> passengerList = passengerRepository.findAll();
        return PassengerDTO.GetPassengerResponse.getPassengerResponseList(passengerList);
    }

    /**
     * @param passengerId,passenger
     * @return
     */
    @Override
    public PassengerDTO.GetPassengerResponse updatePassenger(UUID passengerId, PassengerDTO.NewPassengerDTO passenger) {
        AirlineUser airlineUser = airlineUserRepository.getUserByUserId(passenger.airlineUserId);
        Passenger updatedPassenger = passenger.toPassenger(airlineUser);
        updatedPassenger.setPassengerId(passengerId);
        if(passengerRepository.existsByPassengerId(passengerId)){
            return PassengerDTO.GetPassengerResponse.getPassengerResponse(passengerRepository.save(updatedPassenger));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Passenger not found!!");
    }

    /**
     * @param passengerId
     * @return
     */
    @Override
    public PassengerDTO.GetPassengerResponse deletePassenger(UUID passengerId) {
        if(passengerRepository.existsByPassengerId(passengerId)){
            Passenger passenger = passengerRepository.getPassengerByPassengerId(passengerId);
            passengerRepository.deleteById(passengerId);
            return PassengerDTO.GetPassengerResponse.getPassengerResponse(passenger);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Passenger not found!!");
    }
}
