package chapter3_streams.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import chapter3_streams.Gender;
import chapter3_streams.Person;

/**
 * Beispielprogramm zur Demonstration von komplexeren Bedingungen mit Praedikaten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class ComplexPredicatesExample {

    public static void main(final String[] args) {
        final List<Person> persons = createDemoData();

        // Einfache Praedikate formulieren
        final Predicate<Person> isAdult = person -> person.getAge() >= 18;
        final Predicate<Person> isMale = person -> person.getGender() == Gender.MALE;

        // Negation einzelner Praedikate
        final Predicate<Person> isYoung = isAdult.negate();
        final Predicate<Person> isFemale = isMale.negate();

        // Kombination von Praedikaten mit AND
        final Predicate<Person> boys = isMale.and(isYoung);
        final Predicate<Person> women = isFemale.and(isAdult);

        // Kombination von Praedikaten mit OR
        final Predicate<Person> boysOrWomen = boys.or(women);

        removeAll(persons, boysOrWomen);
        System.out.println(persons);
    }

    private static List<Person> createDemoData() {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Michael", 44));
        persons.add(new Person("Barbara", 41, Gender.FEMALE));
        persons.add(new Person("Lili", 17, Gender.FEMALE));
        persons.add(new Person("Tom", 8));
        persons.add(new Person("Bj�rn", 7));
        return persons;
    }

    // Externe Iteration
    private static <E> void removeAll(final List<E> list, final Predicate<? super E> condition) {

        final Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            if (condition.test(it.next())) {
                it.remove();
            }
        }
    }
}