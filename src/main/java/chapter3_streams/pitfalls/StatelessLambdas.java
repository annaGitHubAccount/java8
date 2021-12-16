package chapter3_streams.pitfalls;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Beispielprogram illustriert, wie man Probleme durch zustandsbehaftete Lambdas vermeidet.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class StatelessLambdas {
    public static void main(final String[] args) {
        final IntPredicate isOdd = i -> i % 2 != 0;
        final IntPredicate inRange = createRangePredicate(4, 500_000);
        final IntStream values = IntStream.rangeClosed(1, 1_000_000); // .parallel();

        final long start = System.nanoTime();
        final List<Integer> result = performFiltering(values, isOdd, inRange);
        final long end = System.nanoTime();

        System.out.println("Result = " + result);
        System.out.println("Filtering took " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ns");
    }

    static List<Integer> performFiltering(final IntStream values,
                                          final IntPredicate predicate1,
                                          final IntPredicate predicate2) {
        return values.filter(predicate1).
                filter(predicate2).
                boxed().
                collect(Collectors.toList());
    }

    static IntPredicate createRangePredicate(final int from, final int to) {
        return i -> i >= from && i < to;
    }
}
