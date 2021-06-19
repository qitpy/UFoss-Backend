package com.smartdev.ufoss.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super((message));
    }
}
