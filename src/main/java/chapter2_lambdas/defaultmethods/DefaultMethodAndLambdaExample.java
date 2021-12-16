package chapter2_lambdas.defaultmethods;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielprogramm, das die neuen Defaultmethoden sort() und forEach()
 * fuer Collections und deren Zusammenspiel mit Lambdas zeigt.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class DefaultMethodAndLambdaExample {

    public static void main(final String[] args) {

        final List<String> names = Arrays.asList("Andy", "Michael", "Max", "Stefan");

        names.sort((str1, str2) -> Integer.compare(str1.length(), str2.length()));
        names.sort(Comparator.comparingInt(String::length));

        names.forEach(it -> System.out.print(it.length() + ", "));

        System.out.println();
        namenListLaengeZeigen();
    }



    private static void namenListLaengeZeigen(){

        List<String> namenList = Arrays.asList("Ania", "Agnieszka", "Krystian", "Zosia");

        namenList.sort(Comparator .comparingInt(name -> name.length()));
        namenList.forEach(name -> System.out.println(name));
    }
}