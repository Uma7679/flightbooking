package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.Booking;
import com.umakant.airlinebooking.model.AirlineUser;

import java.util.Set;
import java.util.UUID;

public class AirlineUserDTO {
    public static class NewUser{
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public String phoneNumber;

        public AirlineUser toAirlineUser(){
            return new AirlineUser(null, firstName, lastName, email, password, phoneNumber, null, null);
        }
    }

    public static class AirlineUserResponse {
        public UUID airlineUserId;
        public String firstName;
        public String lastName;
        public String email;
        public String phoneNumber;

        public AirlineUserResponse(UUID airlineUserId, String firstName, String lastName, String email, String phoneNumber) {
            this.airlineUserId = airlineUserId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public AirlineUserResponse() {
        }

        public static AirlineUserResponse getUserResponse(AirlineUser airlineUser){
            return new AirlineUserResponse(airlineUser.getAirlineUserId(), airlineUser.getFirstName(), airlineUser.getLastName(), airlineUser.getEmail(), airlineUser.getPhoneNumber());
        }
    }
}
