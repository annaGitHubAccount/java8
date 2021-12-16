package chapter7_realworld;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utility-Klasse, um Daten aus Log-Dateien zu extrahieren und auszubereiten.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class CallTimeAnalyzeUtils {
    public static Stream<String> extractRelevantLines(final Path pathToLogFile, final long thresholdInMs)
            throws IOException {
        final Stream<String> lines = Files.lines(pathToLogFile);
        final Predicate<String> hasCallTime = line -> line.contains("call_time") || line.contains("calltime");
        final Stream<String> relevantLines = lines.filter(hasCallTime);
        return relevantLines.filter(line -> extractCallTime(line) >= thresholdInMs);
    }

    public static long extractCallTime(final String line) {
        final long callTime1 = extractCallTimeValueInMs(line, "call_time:= ");
        final long callTime2 = extractCallTimeValueInMs(line, "calltime ");

        return Math.max(callTime1, callTime2);
    }

    /* private */
    static long extractCallTimeValueInMs(final String line, final String prefix) {
        final int posPrefix = line.indexOf(prefix);
        final int endPosPrefix = posPrefix + prefix.length();
        final int posPostfix = line.indexOf("ms", endPosPrefix);

        if (posPrefix >= 0 && posPostfix >= 0) {
            final String durationAsString = line.substring(endPosPrefix, posPostfix - 1);
            return Long.parseLong(durationAsString);
        }

        return -1;
    }

    public static String extractCall(final String line) {
        final String prefix = "invoked: ";
        final int posPrefix = line.indexOf(prefix);
        final int endPosPrefix = posPrefix + prefix.length();
        final int endPosName = line.indexOf("(", endPosPrefix);

        if (posPrefix >= 0 && endPosName >= 0) {
            return line.substring(endPosPrefix, endPosName);
        }

        return "";
    }

    public static Map<String, Long> analyzeCallCounts(final Path pathToLogFile) throws IOException {
        final Stream<String> relevantLines = extractRelevantLines(pathToLogFile, 500);
        final Stream<String> calls = relevantLines.map(line -> extractCall(line));

        final Map<String, Long> callCountHistogram = histogram(calls, Function.identity());

        return sortMapByValue(callCountHistogram);
    }

    public static Map<String, Long> analyzeCallTimes(final Path pathToLogFile) throws IOException {
        final Stream<String> relevantLines = extractRelevantLines(pathToLogFile, 500);

        final Map<String, Long> callTimeHistogram = relevantLines.collect(toMap(line -> extractCall(line),
                line -> 0L + extractCallTime(line),
                (time1, time2) -> time1 + time2));

        return sortMapByValue(callTimeHistogram);
    }


    // -------------------------------------------------------------------------
    // Special Collection Handling  
    // -------------------------------------------------------------------------

    public static Map<String, Long> sortMapByValue(final Map<String, Long> map) {
        class ValueComparator implements Comparator<String> {
            public int compare(final String first, final String second) {
                final int result = Long.compare(map.get(second), map.get(first));
                if (result == 0) // Der Wert 0 würde ungewünschte Ergebnisse liefern  
                {
                    return -1;
                }
                return result;
            }
        }

        final ValueComparator comparator = new ValueComparator();
        final TreeMap<String, Long> valueSortedMap = new TreeMap<>(comparator);
        valueSortedMap.putAll(map);
        return valueSortedMap;
    }

    public static <T> Map<T, Long> histogram(final Stream<T> stream, final Function<T, T> groupFunction) {
        return stream.collect(groupingBy(groupFunction, counting()));
    }
}
