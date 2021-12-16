package chapter3_streams.collectors;

import static java.util.stream.Collectors.groupingBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung und Kombination von Werten aus Streams (Multimap).
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsToMapExample4 {

    public static void main(final String[] args) {
        final List<Person> persons = CollectorsToMapExample2.createPersons();

        System.out.println("grouped by city (jdk 8 toMap)");
        final Collector<Person, ?, Map<String, List<Person>>> multimapMapper = Collectors.toMap(Person::getCity,
                person -> Arrays.asList(person),
                (List<Person> list1,
                 List<Person> list2) -> {
                    final List<Person> both = new ArrayList<>();
                    both.addAll(list1);
                    both.addAll(list2);
                    return both;
                });
        Map<String, List<Person>> ergebnis1 = persons.stream().collect(multimapMapper);
        System.out.println(ergebnis1);

        System.out.println("grouped by city (jdk 8 groupingBy)");
        final Collector<Person, ?, Map<String, List<Person>>> groupingBy = groupingBy(Person::getCity);
        System.out.println(persons.stream().collect(groupingBy));
    }
}
