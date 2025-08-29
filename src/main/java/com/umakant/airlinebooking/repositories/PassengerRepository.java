package com.umakant.airlinebooking.repositories;


import com.umakant.airlinebooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, UUID> {
    Passenger getPassengerByPassengerId(UUID passengerId);
    boolean existsByPassengerId(UUID passengerId);
    List<Passenger> findByPassengerIdIn(List<UUID> passengerIdList);

    //find by airline user id
//    Set<UUID> findAllByAirlineUser_UserId(UUID userId);
    @Query("SELECT p.passengerId FROM Passenger p WHERE p.airlineUser.userId = :userId")
    Set<UUID> findAllPassengerIdsByAirlineUser_UserId(UUID userId);

}
