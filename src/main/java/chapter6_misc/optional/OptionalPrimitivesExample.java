package chapter6_misc.optional;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * Beispielprogramm zur Demonstration der Klassen OptionalInt und OptionalDouble bei der Verarbeitung primitiver Werte.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class OptionalPrimitivesExample {
    public static void main(final String[] args) {
        final int[] sampleValues = {1, 3, 5, 7, 11, 13, 17, 19};

        final OptionalInt max = Arrays.stream(sampleValues).max();
        final OptionalInt min = Arrays.stream(sampleValues).min();
        final OptionalDouble avg = Arrays.stream(sampleValues).average();

        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("avg: " + avg);
    }
}