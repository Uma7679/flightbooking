package com.umakant.airlinebooking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.UUID;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID passengerId;

    @ManyToOne
    @JoinColumn(name = "airline_user_user_id", nullable = false)
    @NotNull
    @Cascade(CascadeType.ALL)
    private AirlineUser airlineUser;

    @NotNull
    @Column(nullable = false)
    private String passengerName;
    private int age;
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "passport_number")
    private String passportNumber;


    public Passenger(UUID passengerId, AirlineUser airlineUser, String passengerName, int age, String email, String phoneNumber, String passportNumber) {
        this.passengerId = passengerId;
        this.airlineUser = airlineUser;
        this.passengerName = passengerName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public Passenger() {
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(UUID passengerId) {
        this.passengerId = passengerId;
    }

    public AirlineUser getAirlineUser() {
        return airlineUser;
    }

    public void setAirlineUser(AirlineUser airlineUser) {
        this.airlineUser = airlineUser;
    }

    public @NotNull String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(@NotNull String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
