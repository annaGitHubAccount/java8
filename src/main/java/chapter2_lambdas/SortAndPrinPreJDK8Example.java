package chapter2_lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration, dass die Sortierung nach Laenge und kommaseparierte Aufbereitung
 * vor JDK 8 recht viele Anweisungen benoetigt.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class SortAndPrinPreJDK8Example {

    public static void main(final String[] args) {
        // Sortierung mit Comparator
        final List<String> names = Arrays.asList("Andy", "Michael", "Max", "Stefan");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(final String str1, final String str2) {
                return Integer.compare(str1.length(), str2.length());
            }
        });

        // Iteration und Ausgabe
        final Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            System.out.print(it.next().length() + ", "); // 3, 4, 6, 7,
        }
    }
}
