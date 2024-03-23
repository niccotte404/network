package com.network.app.exceptions.exceptions;

public class ConvertingException extends RuntimeException {

    public ConvertingException(String message) {
        super(message);
    }

    public ConvertingException(String message, Throwable cause) {
        super(message, cause);
    }
}
