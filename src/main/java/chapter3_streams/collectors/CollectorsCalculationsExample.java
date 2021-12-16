package chapter3_streams.collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;

/**
 * Beispielprogramm zeigt verschiedene mathemathische Berechnungen auf Streams in collect().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsCalculationsExample {

    public static void main(final String args[]) {

        final Map<String, Collector<Integer, ?, ?>> collectorsMap = new TreeMap<>();
        collectorsMap.put("counting():       ", counting());
        collectorsMap.put("summingInt():     ", summingInt(x -> x));
        collectorsMap.put("averagingInt():   ", averagingInt(x -> x));
        collectorsMap.put("maxBy():          ", maxBy(Integer::compare));
        collectorsMap.put("minBy():          ", minBy(Integer::compare));
        collectorsMap.put("summarizingInt(): ", summarizingInt(x -> x));

        // Durchlaufe alle vorher definierten Kollektoren  
        for (final Map.Entry<String, Collector<Integer, ?, ?>> mapping : collectorsMap.entrySet()) {
            final String collectorName = mapping.getKey();
            final Collector<Integer, ?, ?> collector = mapping.getValue();

            final List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            System.out.println(collectorName + ints.stream().collect(collector));
        }
    }
}