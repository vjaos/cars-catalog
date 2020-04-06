package com.example.exception;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String number) {
        super(String.format("Car with number '%s' already exists", number));
    }
}
