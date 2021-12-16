package chapter6_misc.parallelarray;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

/**
 * Beispielprogramm zeigt die Berechnung einer gewuenschten Funktion fuer jeden Wert eines Arrays als eine sehr gut parallelisierbare Aktion.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class ParallelArraySetAllExample {
    public static void main(final String[] args) {
        final int[] numbers = {1, 3, 7, 15, 31, 63};

        System.out.println("Initial:    " + Arrays.toString(numbers));

        // Achtung hier wird der Index uebergeben
        // Inkonsistenz: IntUnaryOperator, IntToDoubleFunction, ...	
        final IntUnaryOperator increment = i -> {
            int value = numbers[i];
            return value + 1;
        };

        Arrays.parallelSetAll(numbers, increment);
        System.out.println("Increment:  " + Arrays.toString(numbers));

        // Alle Werte < 10 durch 2 teilen, alle anderen mit 2 Mmultiplizieren
        final IntUnaryOperator specialMapping = i -> {
            int value = numbers[i];
            return value < 10 ? value / 2 : value * 2;
        };

        Arrays.parallelSetAll(numbers, specialMapping);
        System.out.println("Converted:  " + Arrays.toString(numbers));
    }
}