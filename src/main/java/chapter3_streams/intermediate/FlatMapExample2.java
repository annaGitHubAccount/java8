package chapter3_streams.intermediate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Beispielprogram mit funktionaler Erweiterung zeigt die Abbildung von Objekten mithilfe der
 * Methode map() --hier zur Extraktion von Worten aus Zeilen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class FlatMapExample2 {
    public static void main(final String[] args) throws IOException {
        final Path exampleFile = Paths.get("src/main/resources/Example.txt");

        final List<String> contents = Files.readAllLines(exampleFile);

        // daraus einen Stream von word machen
        final Stream<String> words = contents.stream().flatMap(line -> Stream.of(line.split(" ")));

        // predicates
        final List<String> ignoreableWords = Arrays.asList("this", "these", "those");
        final Predicate<String> isIgnorableWord = word -> {
            return ignoreableWords.contains(word.trim().toLowerCase());
        };
        final Predicate<String> isSignificantWord = isIgnorableWord.negate();

        final Predicate<String> isShortWord = word -> word.length() <= 3;
        final Predicate<String> notIsShortWord = isShortWord.negate();

        final Stream<String> filteredContents = words.filter(notIsShortWord).filter(isSignificantWord);

        // Mappings
        final Function<String, String> removeSatzzeichen = str -> {
            if (str.endsWith(".") || str.endsWith(":") || str.endsWith("!")) {
                return str.substring(0, str.length() - 1);
            } else {
                return str;
            }
        };

        final Stream<String> mapped = filteredContents.map(removeSatzzeichen);
        final Stream<String> sorted = mapped.sorted(String.CASE_INSENSITIVE_ORDER);

        sorted.forEach(it -> System.out.print(it + ", "));
    }
}