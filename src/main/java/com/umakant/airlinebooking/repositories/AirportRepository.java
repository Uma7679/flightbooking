package com.umakant.airlinebooking.repositories;


import com.umakant.airlinebooking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {
    Airport getAirportByAirportId(UUID airportId);
//    Airport updateAirportByAirportId(UUID airportId, Airport airport);
    boolean existsByAirportId(UUID airportId);
}
