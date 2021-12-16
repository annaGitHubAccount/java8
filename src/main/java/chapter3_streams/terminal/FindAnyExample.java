package chapter3_streams.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Beispielprogramm demonstriert verschiedene Matching-Strategien.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FindAnyExample {

    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Tim", "Tom", "Micha");

       names.stream().findAny().ifPresent(System.out::println);

        // Praxisrelevantere Form mit vorangeghender Filterung
        final Predicate<String> startsWithT = str -> str.startsWith("T");
       names.stream().filter(startsWithT).
                findAny().ifPresent(System.out::println);


    }


}