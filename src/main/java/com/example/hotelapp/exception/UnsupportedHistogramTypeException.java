package com.example.hotelapp.exception;

public class UnsupportedHistogramTypeException extends RuntimeException {

    public UnsupportedHistogramTypeException(String type) {
        super("Unsupported histogram type: " + type);
    }
}
