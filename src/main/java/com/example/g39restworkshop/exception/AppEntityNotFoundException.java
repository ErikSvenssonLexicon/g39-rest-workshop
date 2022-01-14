package com.example.g39restworkshop.exception;

public class AppEntityNotFoundException extends RuntimeException{
    public AppEntityNotFoundException(String message) {
        super(message);
    }
}
