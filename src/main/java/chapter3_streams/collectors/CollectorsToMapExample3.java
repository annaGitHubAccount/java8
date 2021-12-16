package chapter3_streams.collectors;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung und Kombination von Werten aus Streams (Konkatenation).
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsToMapExample3 {

    public static void main(final String[] args) {
        final List<Person> persons = CollectorsToMapExample2.createPersons();

        final BinaryOperator<String> mergeFunction = (name1, name2) -> name1 + " & " + name2;

        final Collector<Person, ?, Map<Integer, String>> mapAgeToNames = Collectors.toMap(Person::getAge, Person::getName, mergeFunction);

        System.out.println("age to name(s): " + persons.stream().collect(mapAgeToNames));
    }
}
