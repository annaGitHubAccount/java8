package chapter3_streams.parallel;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Beispielprogram illustriert, welche Effekte auftreten koennen, wenn man finAny() in Kombination mit parallelen Streams nutzt.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class ParallelFindAnyExample {
    public static void main(final String[] args) {

        final IntPredicate isEven = i -> i % 2 == 0;
        IntStream.range(0, 10_000).filter(isEven).parallel().findAny().ifPresent(System.out::println);
    }
}
