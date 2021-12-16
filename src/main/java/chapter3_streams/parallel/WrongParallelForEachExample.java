package chapter3_streams.parallel;

import java.util.Arrays;
import java.util.List;

/**
 * Beispielprogram illustriert, welche Fehler auftreten koennen, wenn man forEach()
 * in Kombination mit parallelen Streams nutzt.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class WrongParallelForEachExample {
    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Stefan", "Ralph", "Andi", "Mike");

        names.parallelStream().sorted().forEach(System.out::println);
    }
}