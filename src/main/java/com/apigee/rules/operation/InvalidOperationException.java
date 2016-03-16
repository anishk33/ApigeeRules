package com.apigee.rules.operation;

/**
 * A run time exception that covers invalid operation attempts.
 * Examples are cases where the given {@link Operation} does not belong to valid operations.
 *
 * @author Anish Kurian.
 */
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException() {
        super();
    }

    public InvalidOperationException(final String message) {
        super(message);
    }

    public InvalidOperationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
