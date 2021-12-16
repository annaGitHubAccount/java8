package chapter2_lambdas.methodreferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Beispielprogramm, das die bessere Lesbarkeit durch Methodenreferenzen statt Lambdas zeigt
 * und verdeutlicht, dass Lambdas nicht direkt in eine Methodenreferenz gewandelt werden koennen,
 * falls neben dem Metodenaufruf noch eine weitere Aktion erfolgt, hier die Addition eines ",".
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class MethodReferenceExample2 {

    public static void main(final String[] args) {
        final List<String> names = Arrays.asList("Andy", "Michael", "Max", "Stefan");

        names.sort(MethodReferenceExample::stringLengthCompare);

        names.forEach(MethodReferenceExample2::commaSeparatedPrint);
    }

    public static Consumer<String> commaSeparatedPrint() {
        return it -> System.out.print(it.length() + ", ");
    }

    public static void commaSeparatedPrint(final String str) {
        System.out.print(str.length() + ", ");
    }
}