package chapter6_misc.optional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * Beispielprogramm zur Demonstration der Klasse Optional<T> beim Berechnen von Minimum und Maximum.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FirstOptionalExample {
    public static void main(final String[] args) {
        final Integer[] sampleValues = {1, 3, 5, 7, 11, 13, 17, 19};
        final Integer[] noValues = {};

        final Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        final Optional<Integer> max = Arrays.stream(sampleValues).max(naturalOrder);
        final Optional<Integer> min = Arrays.stream(noValues).min(naturalOrder);

        // Minimum und Maximum ausgeben
        System.out.println("max:        " + max);
        System.out.println("min:        " + min);

        // pruefe, ob es einen Wert gibt
        System.out.println("isPresent?: " + min.isPresent());

        // Zugriff auf den Wert
        final Integer maxValue = max.get();
        System.out.println("maxValue:   " + maxValue);

        // Konstruktionsmethoden
        final Optional<Integer> optionalFromValue = Optional.of(4711);
        final Optional<Double> optionalFromNull = Optional.ofNullable(null);
        System.out.println("from Value: " + optionalFromValue);
        System.out.println("from null:  " + optionalFromNull);
    }
}