package chapter2_lambdas.exceptions;

import java.io.IOException;
import java.util.Comparator;

/**
 * Beispielprogramm zur Demonstration von Exception Handling in Lambdas
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
@FunctionalInterface
public interface ComparatorThatThrows<T> extends Comparator<T> {
    @Override
    default int compare(final T t1, final T t2) {
        try {
            return compareThrows(t1, t2);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int compareThrows(final T t1, final T t2) throws IOException;
}