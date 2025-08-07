package com.umakant.airlinebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID airportId;
    String airportName;
    String latitude;
    String longitude;

    public Airport(UUID airportId, String airportName, String latitude, String longitude) {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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
