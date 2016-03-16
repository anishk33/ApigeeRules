package com.apigee.rules.exception;

/**
 * A run time exception that covers data being not found.
 * Examples are cases where an attempt to retrieve data from memory where the data does not exist.
 *
 * @author Anish Kurian.
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(final String message) {
        super(message);
    }

    public DataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
