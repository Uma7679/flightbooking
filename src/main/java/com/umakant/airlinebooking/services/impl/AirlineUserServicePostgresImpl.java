package com.umakant.airlinebooking.services.impl;


import com.umakant.airlinebooking.airlineExceptions.AirlineUserNotFoundException;
import com.umakant.airlinebooking.dto.AirlineUserDTO;
import com.umakant.airlinebooking.model.AirlineUser;
import com.umakant.airlinebooking.repositories.AirlineUserRepository;
import com.umakant.airlinebooking.services.AirlineUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.UUID;

@Service
public class AirlineUserServicePostgresImpl implements AirlineUserService {
    private final AirlineUserRepository airlineUserRepository;

    public AirlineUserServicePostgresImpl(AirlineUserRepository airlineUserRepository) {
        this.airlineUserRepository = airlineUserRepository;
    }

    /**
     * @param newAirlineUser
     * @return
     */
    @Override
    public AirlineUserDTO.AirlineUserResponse createUser(AirlineUserDTO.NewUser newAirlineUser) {
        AirlineUser airlineUser = newAirlineUser.toAirlineUser();
        return AirlineUserDTO.AirlineUserResponse.getUserResponse(airlineUserRepository.save(airlineUser));
    }

    /**
     * @param airlineUserId
     * @return
     */
    @Override
    public AirlineUserDTO.AirlineUserResponse getUser(UUID airlineUserId) {
        AirlineUser airlineUser = airlineUserRepository.getUserByUserId(airlineUserId);
        if(airlineUser == null){
            throw new AirlineUserNotFoundException("Airline User Not found!");
        }
        return AirlineUserDTO.AirlineUserResponse.getUserResponse(airlineUser);
    }

    /**
     * @param airlineUserId
     * @param updatedAirlineUser
     * @return
     */
    @Override
    public AirlineUserDTO.AirlineUserResponse updateUser(UUID airlineUserId, AirlineUserDTO.NewUser updatedAirlineUser) {
        AirlineUser airlineUser = updatedAirlineUser.toAirlineUser();
        airlineUser.setAirlineUserId(airlineUserId);
        if(airlineUserRepository.existsByUserId(airlineUserId)){
            return AirlineUserDTO.AirlineUserResponse.getUserResponse(airlineUserRepository.save(airlineUser));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"User not found!!");
    }

    /**
     * @param airlineUserId
     * @return
     */
    @Override
    public AirlineUserDTO.AirlineUserResponse deleteUser(UUID airlineUserId) {
        if(airlineUserRepository.existsByUserId(airlineUserId)){
            AirlineUser airlineUser = airlineUserRepository.getUserByUserId(airlineUserId);
            airlineUserRepository.deleteById(airlineUserId);
            return AirlineUserDTO.AirlineUserResponse.getUserResponse(airlineUserRepository.save(airlineUser));
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND,"User not found!!");
    }
}
