package chapter3_streams.collectors;

import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Beispielprogramm zeigt die Aufbereitung eines Streams als Collection mit toCollection().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsToCollectionExample {

    public static void main(final String[] args) {
        final Supplier<TreeSet<String>> collectionFactory = TreeSet::new;
        final Collector<String, ?, TreeSet<String>> sortingCollector = Collectors.toCollection(collectionFactory);

        final Stream<String> letters = Stream.of("a", "b", "X", "B", "A");
        final TreeSet<String> sortedLetters = letters.collect(sortingCollector);
        System.out.println(sortedLetters);
    }
}

