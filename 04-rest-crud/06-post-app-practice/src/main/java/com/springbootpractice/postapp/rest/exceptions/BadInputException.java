package com.springbootpractice.postapp.rest.exceptions;

public class BadInputException extends RuntimeException {
    public BadInputException(String message) {
        super(message);
    }
}
