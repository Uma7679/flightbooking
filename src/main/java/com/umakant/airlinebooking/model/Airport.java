package com.umakant.airlinebooking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airport {
    @Id
    int airportId;
    String airportName;
    String latitude;
    String longitude;

    public Airport(int airportId, String airportName, String latitude, String longitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Airport() {
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
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
}
