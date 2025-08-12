package com.umakant.airlinebooking.repositories;

import com.umakant.airlinebooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    Flight getFlightByFlightId(UUID flightId);
    boolean existsByFlightId(UUID flightId);
}
