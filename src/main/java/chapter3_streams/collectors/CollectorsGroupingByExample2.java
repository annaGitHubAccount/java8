package chapter3_streams.collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung mit groupingBy().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsGroupingByExample2 {

    public static void main(final String[] args) {
        final List<Person> persons = CollectorsToMapExample2.createPersons();

        final Function<Person, String> byRegion = createRegionClassifier();

        System.out.println("groupd by region -- oldest inhabitant");
        Map<String, Optional<Integer>> groupByRegion = persons.stream().collect(Collectors.groupingBy(byRegion, mapping(Person::getAge, maxBy(Integer::compareTo))));
        System.out.println(groupByRegion);

        System.out.println("\ngroupd by region and age");
        final Function<Person, String> byAge = createAgeClassifier();
        Map<String, Map<String, List<Person>>> groupdByRegionAndAge = persons.stream().collect(Collectors.groupingBy(byRegion, groupingBy(byAge)));
        System.out.println(groupdByRegionAndAge);

        System.out.println("\ngroupd by age and region");
        Map<String, Map<String, List<Person>>> groupByAgeAndRegion = persons.stream().collect(Collectors.groupingBy(byAge, groupingBy(byRegion)));
        System.out.println(groupByAgeAndRegion);
    }

    private static Function<Person, String> createAgeClassifier() {
        return person ->
        {
            final int age = person.getAge();

            if (age < 30)
                return "U30:";

            if (age < 40)
                return "U40:";
            return "40+:";
        };
    }

    private static Function<Person, String> createRegionClassifier() {
        final Function<Person, String> classifier = person -> {
            if (Arrays.asList("Kiel", "Hamburg").contains(person.getCity()))
                return "Nordlichter";
            if (person.getCity().equals("Aachen"))
                return "Öcher";
            return "Others";
        };
        return classifier;
    }
}
