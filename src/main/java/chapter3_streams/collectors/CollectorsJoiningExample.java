package chapter3_streams.collectors;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Beispielprogramm zeigt verschiedene Verkettungen mit joining().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsJoiningExample {
    public static void main(final String[] args) {
        final String[] words = {"This", "Is", "The", "Joining", "Collector"};

        // 3 Varianten der Kombination
        System.out.println(Arrays.stream(words).collect(joining()));
        System.out.println(Arrays.stream(words).collect(joining(" | ")));
        System.out.println(Arrays.stream(words).collect(joining(", ", "[", "]")));

        final Stream<String> names = Stream.of("Andy", "Dirk", "Merten");
        final String prefix = "Many thanks to ";
        final String postfix = " for your comments!";
        String collect = names.collect(joining(", ", prefix, postfix));
        System.out.println(collect);
    }
}
