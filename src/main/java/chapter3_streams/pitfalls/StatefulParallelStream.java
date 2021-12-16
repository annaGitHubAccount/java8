package chapter3_streams.pitfalls;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Beispielprogram illustriert, welche Probleme durch zustandsbehaftete Lambdas in Streams auftreten koennen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class StatefulParallelStream {
    public static void main(final String[] args) {
        final Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 22, 33, 44, 55, 111, 222, 333, 444, 555};

        pagedElementsStateful_Sync(Stream.of(values), 5, 2);
        pagedElementsStateful_Sync(Stream.of(values).parallel(), 5, 2);

        pagedElementsStateful_Copy(Stream.of(values), 5, 2);
        pagedElementsStateful_Copy(Stream.of(values).parallel(), 5, 2);

        pagedElementsStateless(Stream.of(values), 5, 2);
        pagedElementsStateless(Stream.of(values).parallel(), 5, 2);
    }

    private static <T> void pagedElementsStateful_Sync(final Stream<T> stream,
                                                       final int count,
                                                       final int pageNr) {
        final List<T> result = new ArrayList<>();

        final int skipCount = pageNr * count;
        final AtomicLong al = new AtomicLong(skipCount);

        stream.map(item -> {

            // Local variable skipCount defined in an enclosing scope must be final or effectively final
            //if (skipCount--)
            if (al.decrementAndGet() < 0) {
                synchronized (result) {
                    if (result.size() < count) {
                        result.add(item);
                    }
                }
            }
            return item;
        }).count(); // count() noetig => wegen Terminal Operation => sonst kein Processing

        System.out.println("sync:  " + mode(stream) + ": " + result);
    }

    private static String mode(final Stream<?> stream) {
        return stream.isParallel() ? "par" : "seq";
    }

    private static <T> void pagedElementsStateful_Copy(final Stream<T> stream,
                                                       final int count,
                                                       final int pageNr) {
        final List<T> result = new CopyOnWriteArrayList<>();

        final int skipCount = pageNr * count;
        final AtomicLong al = new AtomicLong(skipCount);

        stream.map(item ->
        {
            if (al.decrementAndGet() < 0) {
                if (result.size() < count) {
                    result.add(item);
                }
            }
            return item;
        }).count(); // count() n�tig => wegen Terminal Operation => sonst kein Processing

        System.out.println("copy:  " + mode(stream) + ": " + result);
    }

    private static void pagedElementsStateless(final Stream<Integer> stream,
                                               final int count,
                                               final int pageNr) {
        final int skipCount = pageNr * count;
        final List<Integer> result = stream.skip(skipCount).limit(count).collect(toList());

        System.out.println("limit: " + mode(stream) + ": " + result);
    }
}