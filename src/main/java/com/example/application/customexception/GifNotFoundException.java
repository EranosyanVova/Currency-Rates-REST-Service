package com.example.application.customexception;

public class GifNotFoundException extends RuntimeException {

    public GifNotFoundException(String message) {
        super(message);
    }
}
