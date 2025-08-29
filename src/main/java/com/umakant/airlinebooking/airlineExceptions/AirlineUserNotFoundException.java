package com.umakant.airlinebooking.airlineExceptions;

public class AirlineUserNotFoundException extends RuntimeException{
    public AirlineUserNotFoundException(String message){
        super(message);
    }
}
