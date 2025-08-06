package com.umakant.airlinebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Flight {
    @Id
    int flightId;
    String sourceAirport;
    String destinationAirport;
    int capacity;

    public Flight(int flightId, String sourceAirport, String destinationAirport, int capacity) {
        this.flightId = flightId;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.capacity = capacity;
    }

    public Flight() { }

    public int getFlightId() {
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
