package com.umakant.airlinebooking.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID flightId;

    @ManyToOne
    @JoinColumn(name = "source_airport_airport_id")
    @NotNull
    Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_airport_id")
    @NotNull
    Airport destinationAirport;
    int capacity;

    public Flight(UUID flightId, Airport sourceAirport, Airport destinationAirport, int capacity) {
        this.flightId = flightId;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.capacity = capacity;
    }

    public Flight() {
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
