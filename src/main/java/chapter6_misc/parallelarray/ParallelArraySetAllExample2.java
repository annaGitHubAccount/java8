package chapter6_misc.parallelarray;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Beispielprogramm zeigt die Berechnung einer gewuenschten Funktion fuer jeden Wert eines Arrays als eine sehr gut parallelisierbare Aktion, hier fuer String-Werte.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */

public class ParallelArraySetAllExample2 {
    public static void main(final String[] args) {
        final String[] names = {"Andy", " Trim  ", null, " Trim  ", "Ralph"};

        System.out.println("Initial:    " + Arrays.toString(names));

        // Achtung hier wird der Index uebergeben
        final IntFunction<? super String> trimAndMapNullToNA = i -> {
            final String value = names[i];
            return value == null ? "-n/a-" : value.trim();
        };

        Arrays.parallelSetAll(names, trimAndMapNullToNA);
        System.out.println("Converted:  " + Arrays.toString(names));
    }
}