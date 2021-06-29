package com.smartdev.ufoss.exception;

public class HandleException extends RuntimeException {
    public HandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandleException(String message) {
        super(message);
    }
}
