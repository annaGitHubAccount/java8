package chapter3_streams.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import chapter3_streams.Person;

/**
 * Beispielprogramm zeigt ein einführendes Beispiel für Aufrufe von collect().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class FirstCollectorsExample {
    public static void main(final String[] args) {

        final Stream<Person> personsStream = Stream.of(
                new Person("Mike", 22),
                new Person("Andy", 32),
                new Person("Tim", 44),
                new Person("Yannis", 6));

        final List<String> names = personsStream.map(Person::getName)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);

        System.out.println(names);
    }
}
