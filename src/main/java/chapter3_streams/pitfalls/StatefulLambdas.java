package chapter3_streams.pitfalls;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Beispielprogram illustriert, welche Probleme durch zustandsbehaftete Lambdas auftreten koennen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class StatefulLambdas {
    public static void main(final String[] args) {
        final IntPredicate isOdd = i -> i % 2 != 0;
        final IntPredicate inRange = createRangePredicate(4, 10);
        final IntStream values = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        final long start = System.nanoTime();
        final List<Integer> result = performFiltering(values, isOdd, inRange);
        final long end = System.nanoTime();

        System.out.println("Result = " + result);
        System.out.println("Filtering took " + (end - start) + " ns");
    }

    static List<Integer> performFiltering(final IntStream values,
                                          final IntPredicate predicate1,
                                          final IntPredicate predicate2) {
        final List<Integer> filteredContent = new ArrayList<>();

        values.forEach(i ->
        {
            if (predicate1.test(i) && predicate2.test(i)) {
                filteredContent.add(i);
            }
        });

        return filteredContent;
    }

    static IntPredicate createRangePredicate(final int from, final int to) {
        return i -> i >= from && i < to;
    }
}
