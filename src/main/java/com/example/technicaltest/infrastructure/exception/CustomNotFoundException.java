package com.example.technicaltest.infrastructure.exception;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String messageError) {
        super(messageError);
    }
}
