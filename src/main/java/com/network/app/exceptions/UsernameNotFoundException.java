package com.network.app.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    private static final long serialVersionId = 2;

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
