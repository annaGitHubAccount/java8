package chapter6_misc.optional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Beispielprogramm zeigt, wie lesbar sich Alternativen mithilfe der Klasse Optional<T> beschreiben lassen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class OptionalAlternativesExample {
    public static void main(String[] args) {
        final Integer[] noValues = {};

        final Optional<Integer> min = Arrays.stream(noValues).min(Comparator.naturalOrder());

        // Fuehre Aktion aus, wenn vorhanden
        min.ifPresent(System.out::println);

        // Alternativen Wert liefern, wenn nicht vorhanden
        System.out.println(min.orElse(-1));

        // Berechne Ersatzwert, wenn nicht vorhanden
        final Supplier<Integer> randomGenerator = () -> (int) (100 * Math.random());
        System.out.println(min.orElseGet(randomGenerator));

        // Loese eine Exception aus, wenn nicht vorhanden
        min.orElseThrow(() -> new NoSuchElementException("there is no minimum"));
    }
}
