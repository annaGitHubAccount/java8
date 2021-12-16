package chapter7_realworld;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Beispielprogramm demonstiert die Aufbereitung eines Histogramms.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class HistogramExample {
    public static void main(final String[] args) {
        final String[] names = {"Tim", "Mike", "Kai", "mike", "kai", "Kai", "TOM"};

        final Stream<String> input1 = Stream.of(names);
        final Map<String, Long> histogram1 = histogram(input1, Function.identity());
        System.out.println(histogram1);

        final Stream<String> input2 = Stream.of(names);
        final Map<String, Long> histogram2 = histogram(input2, String::toLowerCase);
        System.out.println(histogram2);
    }

    public static <T> Map<T, Long> histogram(final Stream<T> stream, final Function<T, T> groupFunction) {
        return stream.collect(groupingBy(groupFunction, counting()));
    }
}
