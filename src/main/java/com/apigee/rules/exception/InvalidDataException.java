package com.apigee.rules.exception;

/**
 * A run time exception that covers invalid data for processing.
 * Examples are cases where the given data does not meet the expected input.
 *
 * @author Anish Kurian.
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException() {
        super();
    }

    public InvalidDataException(final String message) {
        super(message);
    }

    public InvalidDataException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
