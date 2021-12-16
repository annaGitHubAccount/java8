package chapter6_misc.parallelarray;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

/**
 * Beispielprogramm zeigt die Berechnung, die Elemente des Arrays jeweils mit dem Wert des Vorgaengers verknnuepft.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class ParallelArrayPrefixExample {
    public static void main(final String[] args) {
        final int[] numbers1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("Initial: " + Arrays.toString(numbers1));

        // Berechne die Summe: sum = 1 + 2 + ... + n 
        final IntBinaryOperator sum = (x, y) -> x + y;
        Arrays.parallelPrefix(numbers1, sum);
        System.out.println("sum:     " + Arrays.toString(numbers1));

        // Berechne die Fakult�t: fak = 1 * 2 * ... * n
        final int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final IntBinaryOperator fak = (x, y) -> x * y;
        Arrays.parallelPrefix(numbers2, fak);
        System.out.println("fak:     " + Arrays.toString(numbers2));
    }
}