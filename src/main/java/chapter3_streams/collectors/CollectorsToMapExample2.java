package chapter3_streams.collectors;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import chapter3_streams.Gender;
import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung und Kombination von Werten aus Streams.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsToMapExample2 {
    public static void main(final String[] args) {

        final List<Person> persons = createPersons();

        System.out.println("grouped by city (jdk 7)");
        Map<String, List<Person>> java7Ergebnis = groupByCity(persons);
        System.out.println(java7Ergebnis);

        //System.out.println("grouped by city (jdk 8 toMap)");
        // wyskakuje Exception in thread "main" java.lang.IllegalStateException: Duplicate key Aachen.
        // bo mapa zawiera Map<String, Person> czyli 1 osobe, a w mapie 1 osoba moze byc tylko pod 1 unikalnym kluczem:
        /*final Collector<Person, ?, Map<String, Person>> mapper = Collectors.toMap(Person::getCity, person -> person);
        Map<String, Person> java8Ergebnis = persons.stream().collect(mapper);
        System.out.println(java8Ergebnis);*/

        // dlatego lepiej uzyc groupingBy() - zmieniam liste osob na mape: miasto + liste osob w miescie:
        System.out.println("groupingByJava8 - create a Map from a List:");

        Map<String, List<Person>> groupingByJava8 = persons.stream().collect(Collectors.groupingBy(person -> person.getCity()));
        System.out.println(groupingByJava8);
    }

    static List<Person> createPersons() {
        final List<Person> persons = Arrays.asList(
                new Person("Michael", 44, Gender.MALE, "Zürich"),
                new Person("Merten", 39, Gender.MALE, "Aachen"),
                new Person("Babsi", 42, Gender.FEMALE, "Hamburg"),
                new Person("Lili", 22, Gender.FEMALE, "Aachen"),
                new Person("Tim", 44, Gender.MALE, "Kiel"),
                new Person("Mike", 26, Gender.MALE, "Kiel"));
        return persons;
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
