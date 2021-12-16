package chapter6_misc.map;

import static chapter6_misc.map.DemoData.createDemoData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Beispielprogramm demonstriert verschiedene Methoden, um die Haeufigkeiten von Woertern in einem Text zu ermitteln, mit putIfAbsent() und replace().
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class WordCountPutIfAbsentReplaceExample {
    public static void main(final String[] args) {
        final List<String> wordList = createDemoData();

        final Map<String, Integer> wordCounts = new TreeMap<>();
        for (final String word : wordList) {
            // Initialen Wert vorgeben, Achtung 0, weil sp�ter Inkrement erfolgt 
            wordCounts.putIfAbsent(word, 0);
            // Wert ermitteln, wenn vorhanden  
            final Integer value = wordCounts.getOrDefault(word, 0);
            // Wert ersetzen  
            wordCounts.replace(word, value + 1);
        }

        System.out.println(wordCounts);
    }
}