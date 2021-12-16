package chapter3_streams.collectors;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import chapter3_streams.Gender;
import chapter3_streams.Person;

import static java.util.stream.Collectors.*;

/**
 * Beispielprogramm zeigt verschiedene Varianten der Gruppierung mit groupingBy().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CollectorsGroupingByExample {

    public static void main(final String[] args) {

        final List<Person> persons = CollectorsToMapExample2.createPersons();

        System.out.println("groupd by gender");
        Map<Gender, List<Person>> collect = persons.stream().collect(groupingBy(person -> person.getGender()));
        System.out.println(collect);

        System.out.println("\ngroup person by city by Anna");
        Map<String, List<Person>> groupByCity = persons.stream().collect(Collectors.groupingBy(person -> person.getCity()));
        System.out.println(groupByCity);

        System.out.println("\ngroup by own region classifier");
        final Function<Person, String> byRegion = createRegionClassifier();

        // 1 sposob:
        Map<String, List<Person>> groupByOwnRegion = persons.stream().collect(Collectors.groupingBy(byRegion));

        // 2 sposob:
        //Map<String, List<Person>> groupByOwnRegion = persons.stream().collect(groupingBy(CollectorsGroupingByExample::classifier));

        // 3 sposob:
        /*Map<String, List<Person>> groupByOwnRegion = persons.stream().collect(groupingBy(person ->
        {
            if (Arrays.asList("Kiel", "Hamburg").contains(person.getCity()))
                return "Nordlichter";
            if (person.getCity().equals("Aachen"))
                return "Öcher";
            return "Others";
        }));*/
        System.out.println(groupByOwnRegion);

        System.out.println("\ngroupd by region and calculation average age");
        Map<String, Double> groupdByRegionAndCalculationAverageAge =
                persons.stream().collect(groupingBy(byRegion, averagingInt(Person::getAge)));
        System.out.println(groupdByRegionAndCalculationAverageAge);

        System.out.println("\ngroupd by region and calculation statistics");
        Map<String, IntSummaryStatistics> groupdByRegionAndCalculationStatistics =
                persons.stream().collect(groupingBy(byRegion, summarizingInt(Person::getAge)));
        System.out.println(groupdByRegionAndCalculationStatistics);


    }

    private static Function<Person, String> createRegionClassifier() {

        final Function<Person, String> classifier = person ->
        {
            if (Arrays.asList("Kiel", "Hamburg").contains(person.getCity()))
                return "Nordlichter";
            if (person.getCity().equals("Aachen"))
                return "Öcher";
            return "Others";
        };
        return classifier;
    }

    private static String classifier(Person person) {
        if (Arrays.asList("Kiel", "Hamburg").contains(person.getCity()))
            return "Nordlichter";
        if (person.getCity().equals("Aachen"))
            return "Öcher";
        return "Others";
    }
}
