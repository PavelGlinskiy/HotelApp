package com.example.hotelapp.exception;

public class AmenityAlreadyExistsException extends RuntimeException {

    public AmenityAlreadyExistsException(String amenity) {
        super("Amenity already exists: " + amenity);
    }
}
