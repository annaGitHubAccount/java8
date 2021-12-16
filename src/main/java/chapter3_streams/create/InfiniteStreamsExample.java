package chapter3_streams.create;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Beispielprogram zeigt die Berechnung von unendlichen Streams auf zwei verschiedene Arten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class InfiniteStreamsExample {

    public static void main(final String[] args) {
        final IntStream iteratingValues = IntStream.iterate(0, x -> x + 1);

        final AtomicInteger ai = new AtomicInteger(0);
        final Stream<Integer> generatedValues = Stream.generate(ai::incrementAndGet);

        final int[] firstTen = iteratingValues.limit(10).toArray();
        final Object[] secondTen = generatedValues.limit(10).toArray();

        System.out.println(Arrays.toString(firstTen));
        System.out.println(Arrays.toString(secondTen));
        System.out.println("Element type: " + secondTen[0].getClass().getTypeName());
    }
}