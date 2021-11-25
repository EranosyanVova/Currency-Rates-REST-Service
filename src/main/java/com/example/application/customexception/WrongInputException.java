package com.example.application.customexception;

public class WrongInputException extends Exception {

    public WrongInputException(String message) {
        super(message);
    }
}
