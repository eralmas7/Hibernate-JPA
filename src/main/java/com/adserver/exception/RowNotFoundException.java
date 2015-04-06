package com.adserver.exception;

public class RowNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RowNotFoundException(String message) {
        super(message);
    }
}
