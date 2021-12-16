package chapter3_streams.intermediate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import chapter3_streams.Gender;
import chapter3_streams.Person;

/**
 * Beispielprogram illustriert das Filtern in einem Stream.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FilterExample {
    public static void main(final String[] args) {
        final List<Person> persons = new ArrayList<>();

        persons.add(new Person("Micha", 43, "Zürich"));
        persons.add(new Person("Barbara", 40, Gender.FEMALE, "Hamburg"));
        persons.add(new Person("Yannis", 5, "Hamburg"));

        // final Predicate<Person> isAdult = person -> person.getAge() >= 18;
        final Stream<Person> adults = persons.stream().filter(Person::isAdult);

        adults.forEach(System.out::println);


    }
}