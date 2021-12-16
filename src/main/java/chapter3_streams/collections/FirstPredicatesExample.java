package chapter3_streams.collections;

import java.util.function.Predicate;

import chapter3_streams.Person;

/**
 * Beispielprogramm zur Demonstration der Formulierung einfacher Bedingungen mit Praedikaten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FirstPredicatesExample {

    public static void main(final String[] args) {
        // Praedikate formulieren
        final Predicate<String> isNull = str -> str == null;
        final Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isEmpty2 = str -> str.isEmpty();
        final Predicate<Person> isAdult = person -> person.getAge() >= 18;

        System.out.println("isNull(''):     " + isNull.test(""));
        System.out.println("isEmpty(''):    " + isEmpty.test(""));
        System.out.println("isEmpty('Pia'): " + isEmpty.test("Pia"));
        System.out.println("isAdult(Pia):   " + isAdult.test(new Person("Pia", 55)));
        System.out.println(isEmpty2.test(""));
    }
}