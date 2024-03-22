package com.network.app.exceptions;

public class UserIdNotFoundException extends RuntimeException {

    private static final long serialVersionId = 1;

    public UserIdNotFoundException(String message) {
        super(message);
    }

    public UserIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
