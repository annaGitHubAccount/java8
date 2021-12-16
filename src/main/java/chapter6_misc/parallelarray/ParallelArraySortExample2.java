package chapter6_misc.parallelarray;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Beispielprogramm zeigt eine sequenzielle und eine parallele Sortierung fuer verschiedene Groessen von Arrays.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class ParallelArraySortExample2 {
    public static void main(final String[] args) {
        // 10.000 -> 100 Millionen
        final long[] limits = {10_000, 100_000, 1_000_000, 10_000_000};

        for (int i = 0; i < 3; i++) {
            for (long currentLimit : limits) {
                // IntStream und Zufallszahlen Generiung
                final IntStream iteratingValues = IntStream.iterate(0, x -> {
                    return (int) (100_000 * Math.random());
                });

                // Beschraenkung auf akteulles Limit
                final IntStream truncated = iteratingValues.limit(currentLimit);

                // Umwandlung in Array
                final int[] array1 = truncated.toArray();
                final int[] array2 = Arrays.copyOf(array1, array1.length);

                // Sortierung sequentiell und parallel ausfuehren und messen
                final long start1 = System.nanoTime();
                Arrays.sort(array1);
                final long end1 = System.nanoTime();

                Arrays.parallelSort(array2);
                final long end2 = System.nanoTime();

                printResult(currentLimit, start1, end1, end2);
            }
        }
    }

    private static void printResult(final long currentLimit, final long start1, final long end1, final long end2) {
        System.out.println("Current limit:         " + currentLimit);
        System.out.println("sequential sort:       " + (end1 - start1));
        System.out.println("parallel sort:         " + (end2 - end1));
        final double factor = ((double) (end2 - end1)) / (end1 - start1);
        System.out.println("parallel : sequential: " + NumberFormat.getPercentInstance().format(factor));
        System.out.println();
    }
}