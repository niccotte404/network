package com.network.app.exceptions.exceptions;

public class CloudinaryException extends RuntimeException {

    public CloudinaryException(String message) {
        super(message);
    }

    public CloudinaryException(String message, Throwable cause) {
        super(message, cause);
    }
}
