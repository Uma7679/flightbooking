package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.AirlineUser;
import com.umakant.airlinebooking.model.Passenger;

import java.util.List;
import java.util.UUID;

public class PassengerDTO {
    public static class NewPassengerDTO{
        public UUID airlineUserId;
        public String passengerName;
        public int age;
        public String email;
        public String phoneNumber;
        public String passportNumber;

        public Passenger toPassenger(AirlineUser airlineUser){
            return new Passenger(null, airlineUser, passengerName, age, email, phoneNumber, passportNumber);
        }
    }

    public static class GetPassengerResponse{
        public UUID passengerId;
        public String passengerName;
        public int age;
        public String email;
        public String phoneNumber;
        public String passportNumber;

        public GetPassengerResponse(UUID passengerId, String passengerName, int age, String email, String phoneNumber, String passportNumber) {
            this.passengerId = passengerId;
            this.passengerName = passengerName;
            this.age = age;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.passportNumber = passportNumber;
        }

        public GetPassengerResponse() {
        }

        public static PassengerDTO.GetPassengerResponse getPassengerResponse(Passenger passenger){
            return new GetPassengerResponse(passenger.getPassengerId(), passenger.getPassengerName(), passenger.getAge(), passenger.getEmail(), passenger.getPhoneNumber(), passenger.getPassportNumber());
        }

        public static List<PassengerDTO.GetPassengerResponse> getPassengerResponseList(List<Passenger> passengerList){
            return passengerList.stream().map(passenger ->
                    new GetPassengerResponse(passenger.getPassengerId(), passenger.getPassengerName(), passenger.getAge(), passenger.getEmail(), passenger.getPhoneNumber(), passenger.getPassportNumber())
            ).toList();
        }
    }
}
