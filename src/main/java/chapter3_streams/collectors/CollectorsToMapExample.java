package chapter3_streams.collectors;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chapter3_streams.Gender;
import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung der Werte aus Streams (hier mit JDK 7).
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsToMapExample {

    public static void main(final String[] args) {
        final List<Person> persons = Arrays.asList(
                new Person("Michael", 44, Gender.MALE, "Zürich"),
                new Person("Merten", 39, Gender.MALE, "Aachen"),
                new Person("Babsi", 42, Gender.FEMALE, "Hamburg"),
                new Person("Mike", 26, Gender.MALE, "Kiel"));

        System.out.println("grouped by city (jdk 7)");
        System.out.println(groupByCity(persons));

        System.out.println("\ngrouped by city (jdk 8 toMap)");
        Map<String, Person> ergebnisMap = persons.stream().collect(Collectors.toMap(Person::getCity,
                person -> person));
        System.out.println(ergebnisMap);
    }

    public static Map<String, List<Person>> groupByCity(final List<Person> persons) {
        final Map<String, List<Person>> result = new HashMap<>();

        for (final Person person : persons) {
            final String key = person.getCity();

            if (!result.containsKey(key)) {
                final List<Person> personsInCity = new ArrayList<>();
                result.put(key, personsInCity);
            }
            result.get(key).add(person);
        }

        return result;
    }
}
