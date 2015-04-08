package com.adserver.exception;

/**
 * If DAO can't find any record in the backend then they are going to throw this exception.
 */
public class NoRecordFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoRecordFoundException(final String message) {
        super(message);
    }
}
