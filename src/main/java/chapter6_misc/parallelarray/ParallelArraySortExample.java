package chapter6_misc.parallelarray;

import java.util.Arrays;

/**
 * Beispielprogram illustriert einen einfachen Aufruf des parallelen Sortierens.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class ParallelArraySortExample {
    public static void main(final String[] args) {
        final int[] numbers = {1, 3, 2, 4, 5, 7, 6, 9, 8, 10};

        System.out.println("Initial: " + Arrays.toString(numbers));
        Arrays.parallelSort(numbers);
        System.out.println("Sorted:  " + Arrays.toString(numbers));
    }
}