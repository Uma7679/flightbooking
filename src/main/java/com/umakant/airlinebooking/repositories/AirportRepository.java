package com.umakant.airlinebooking.repositories;


import com.umakant.airlinebooking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
