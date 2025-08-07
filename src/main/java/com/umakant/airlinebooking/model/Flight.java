package com.umakant.airlinebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID flightId;
    String sourceAirport;
    String destinationAirport;
    int capacity;

    public Flight(UUID flightId, String sourceAirport, String destinationAirport, int capacity) {
        this.flightId = flightId;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.capacity = capacity;
    }

    public Flight() { }

    public UUID getFlightId() {
        return flightId;
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public int getCapacity() {
        return capacity;
    }
}
