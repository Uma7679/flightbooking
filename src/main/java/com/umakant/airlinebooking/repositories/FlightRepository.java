package com.umakant.airlinebooking.repositories;

import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.model.Flight;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    Flight getFlightByFlightId(UUID flightId);
    boolean existsByFlightId(UUID flightId);

    //search flight by source airport and destination airport
    Page<Flight> findAllBySourceAirportAndDestinationAirport(@NotNull Airport sourceAirport, @NotNull Airport destinationAirport, Pageable pageable);

    //search flights with intermediate stops
    @Query("SELECT f FROM Flight f WHERE f.sourceAirport = :sourceAirport AND f.destinationAirport = :destinationAirport AND SIZE(f.stops) > 0")
    Page<Flight> findFlightsWithIntermediateStops(@NotNull Airport sourceAirport,
                                                  @NotNull Airport destinationAirport,
                                                  Pageable pageable);
    Page<Flight> findAll(Pageable pageable);

    // 3. Flights that start from source
    List<Flight> findBySourceAirport(Airport source);
    // 4. Flights that go to destination
    List<Flight> findByDestinationAirport(Airport destination);
    List<Flight> findBySourceAirportAndDestinationAirport(Airport source, Airport destination);

}
