package com.umakant.airlinebooking.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID airportId;

    @Column(unique = true, nullable = false)
    @NotNull
    private String airportName;

    @Column(nullable = false)
    @NotNull
    private Double latitude;

    @Column(nullable = false)
    @NotNull
    private Double longitude;

    public Airport(UUID airportId, String airportName, Double latitude, Double longitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Airport() {
    }

    public Airport(Airport airport) {
        this.airportId = airport.airportId;
        this.airportName = airport.airportName;
        this.latitude = airport.latitude;
        this.longitude = airport.longitude;
    }

    public UUID getAirportId() {
        return airportId;
    }

    public void setAirportId(UUID airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", airportName='" + airportName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
