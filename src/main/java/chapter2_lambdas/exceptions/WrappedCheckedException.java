package chapter2_lambdas.exceptions;

/**
 * Beispielprogramm zur Demonstration von Exception Handling in Lambdas
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class WrappedCheckedException extends RuntimeException {

    public WrappedCheckedException(final Exception cause) {
        super(cause);
    }
}
