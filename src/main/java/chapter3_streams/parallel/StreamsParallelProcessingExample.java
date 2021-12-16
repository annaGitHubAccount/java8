package chapter3_streams.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import chapter3_streams.Person;

/**
 * Beispielprogamm zeigt die Reihenfolge bei der parallelen Abarbeitung von Verarbeitungsschritten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class StreamsParallelProcessingExample {
    public static void main(final String[] args) {
        final List<Person> persons = createDemoData();

        final Stream<String> allMikes = persons.stream().parallel().
                filter(person -> {
                    final boolean pass = person.isAdult();
                    System.out.println("Step 1 filter: " + person + " / isAdult(): " + pass);
                    return pass;
                }).
                map(person -> {
                    final String name = person.getName();
                    System.out.println("Step 2 map:    " + person + " -> " + name);
                    return name;
                }).
                filter(name -> {
                    final boolean pass = name.startsWith("Mi");
                    System.out.println("Step 3 filter: " + name + " / startsWith('Mi'): " + pass);
                    return pass;
                }).
                map(name -> {
                    final String upperName = name.toUpperCase();
                    System.out.println("Step 4 map:    " + name + " -> " + upperName);
                    return upperName;
                });


        // Loest die Verarbeitung aus
        System.out.println("Protokollierung jedes Schritts beim Filtern:");
        allMikes.forEach(System.out::println);
    }

    private static List<Person> createDemoData() {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Michael", 43));
        persons.add(new Person("Max", 5));
        persons.add(new Person("Moritz", 7));
        persons.add(new Person("Merten", 38));
        persons.add(new Person("Micha", 42));
        return persons;
    }
}