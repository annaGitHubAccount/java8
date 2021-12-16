package chapter2_lambdas.methodreferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielprogramm, das die bessere Lesbarkeit durch Methodenreferenzen statt Lambdas zeigt
 * und verdeutlicht, dass Lambdas immer durch eine Methodenreferenz auf eine eigen Methode
 * ersetzt werden koennen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class MethodReferenceExample {

    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Max", "Andy", "Michael", "Stefan");

        // names.sort((str1, str2) -> Integer.compare(str1.length(), str2.length()));
        names.sort(MethodReferenceExample::stringLengthCompare);

        // Methodenreferenz nachfolgend nicht direkt nutzbar
        names.forEach(it -> System.out.print(it.length() + ", "));
    }

    public static Comparator<String> stringLengthCompare() {
        return (str1, str2) -> Integer.compare(str1.length(), str2.length());
    }

    public static int stringLengthCompare(final String str1, final String str2) {
        return Integer.compare(str1.length(), str2.length());
    }
}