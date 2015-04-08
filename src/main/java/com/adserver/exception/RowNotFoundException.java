package com.adserver.exception;

/**
 * This is the exception which will be thrown when user tries to update/get customer configuration
 * where customer is not present or is inactive.
 */
public class RowNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RowNotFoundException(final String message) {
        super(message);
    }
}
