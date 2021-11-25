package com.example.application.customexception;

public class GifNotFoundException extends Exception{

    public GifNotFoundException(String message) {
        super(message);
    }
}
