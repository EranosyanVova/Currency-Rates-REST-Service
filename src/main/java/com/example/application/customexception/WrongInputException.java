package com.example.application.customexception;

public class WrongInputException extends RuntimeException {

    public WrongInputException(String message) {
        super(message);
    }
}
