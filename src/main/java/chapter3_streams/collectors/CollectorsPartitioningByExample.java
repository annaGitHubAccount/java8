package chapter3_streams.collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chapter3_streams.Gender;
import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Partionierung mit partitioningBy().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsPartitioningByExample {

    public static void main(final String[] args) {
        final List<Person> persons = CollectorsToMapExample2.createPersons();

        final Predicate<Person> isFemale = person -> person.getGender() == Gender.FEMALE;

        System.out.println("Males and females in map");
        Map<Boolean, List<Person>> ergebnis1 = persons.stream().collect(partitioningBy(isFemale));
        System.out.println(ergebnis1);

        System.out.println("\nmap to just names");
        Map<Boolean, List<String>> ergebnis2 = persons.stream().collect(partitioningBy(isFemale,
                mapping(Person::getName, Collectors.toList())));
        System.out.println(ergebnis2);

        System.out.println("\nmap to unique cities");
        Map<Boolean, Set<String>> ergebnis3 = persons.stream().collect(partitioningBy(isFemale,
                mapping(Person::getCity, Collectors.toSet())));
        System.out.println(ergebnis3);

        System.out.println("\nmap to unique cities sorted");
        Map<Boolean, TreeSet<String>> ergebnis4 = persons.stream().collect(partitioningBy(isFemale,
                mapping(Person::getCity, Collectors.toCollection(TreeSet::new))));
        System.out.println(ergebnis4);
    }
}
