package com.umakant.airlinebooking.services;

import com.umakant.airlinebooking.model.Flight;

import java.util.List;

public interface FightService {
    //create flight
    Flight createFlight(Flight flight);

    //get flight
    Flight getFlightById(int id);

    //get all flights
    List<Flight> getAllFlights();

    //update flight
    Flight updateFlight(int id, Flight flight);

    //delete flight
    Flight deleteFlightById(int id);
}
