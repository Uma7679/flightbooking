package com.umakant.airlinebooking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID flightId;

    @ManyToOne
    @JoinColumn(name = "source_airport_airport_id", nullable = false)
    @NotNull
    private Airport sourceAirport;

    @ManyToMany
    private List<Airport> stops = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "destination_airport_airport_id", nullable = false)
    @NotNull
    private Airport destinationAirport;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int capacity;

    public Flight(UUID flightId, Airport sourceAirport, List<Airport> stops, Airport destinationAirport, LocalDateTime arrivalTime, LocalDateTime departureTime, int capacity) {
        this.flightId = flightId;
        this.sourceAirport = sourceAirport;
        this.stops = stops;
        this.destinationAirport = destinationAirport;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
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

    public @NotNull Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(@NotNull Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public @NotNull Airport getDestinationAirport() {
        return destinationAirport;
    }

    public List<Airport> getStops() {
        return stops;
    }

    public void setStops(List<Airport> stops) {
        this.stops = stops;
    }

    public void setDestinationAirport(@NotNull Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
