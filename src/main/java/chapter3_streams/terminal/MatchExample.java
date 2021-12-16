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
public class MatchExample {
    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Tim", "Tom", "Micha");

        final Predicate<String> startsWithT = str -> str.toUpperCase().startsWith("T");

        final boolean allStartWithT = names.stream().allMatch(startsWithT);
        final boolean anyStartWithT = names.stream().anyMatch(startsWithT);
        final boolean noneStartWithT = names.stream().noneMatch(startsWithT);

        System.out.println("allStartWithT:  " + allStartWithT);
        System.out.println("anyStartWithT:  " + anyStartWithT);
        System.out.println("noneStartWithT: " + noneStartWithT);
    }
}