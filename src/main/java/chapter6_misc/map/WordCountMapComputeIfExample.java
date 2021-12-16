package chapter6_misc.map;

import static chapter6_misc.map.DemoData.createDemoData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Beispielprogramm demonstriert verschiedene Methoden, um die Haeufigkeiten von Woertern in einem Text zu ermitteln, mit compute..If()
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class WordCountMapComputeIfExample {
    public static void main(final String[] args) {
        final List<String> wordList = createDemoData();

        final Map<String, Integer> wordCounts = new TreeMap<>();

        for (final String word : wordList) {
            wordCounts.computeIfPresent(word, (str, val) -> val + 1);
            wordCounts.computeIfAbsent(word, (val) -> 1);
            //wordCounts.putIfAbsent(word, 1);		
        }

        System.out.println(wordCounts);
    }
}