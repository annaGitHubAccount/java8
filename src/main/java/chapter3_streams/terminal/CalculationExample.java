package chapter3_streams.terminal;

import java.awt.*;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import chapter3_streams.Person;

/**
 * Beispielprogamm zeigt, wie man verschiedene Berechnungen ausfuehren kann.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class CalculationExample {
    public static void main(final String[] args) {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ten", 10));
        persons.add(new Person("Twenty", 20));
        persons.add(new Person("Thrirty", 30));
        persons.add(new Person("Forty", 40));

        // Anzahl an Personen, deren Name mit 'T' statet  
        Predicate<? super Person> startsWithT = person -> person.getName().startsWith("T");

        final long count = persons.stream().filter(startsWithT).count();
        System.out.println("count: " + count);

        // Summe berechnen  
        final int sum = persons.stream().filter(startsWithT).mapToInt(Person::getAge).sum();
        System.out.println("sum:   " + sum);

        // Durchschnitt berechnen 
        final OptionalDouble avg = persons.stream().filter(person -> person.getName().contains("X"))
                .mapToInt(Person::getAge).average();
        System.out.println("avg:   " + avg);

        final IntStream ages = persons.stream().filter(person -> person.getName().startsWith("T")).
                mapToInt(Person::getAge);
        final IntSummaryStatistics stats = ages.summaryStatistics();

        System.out.println("stats: " + stats);
    }
}