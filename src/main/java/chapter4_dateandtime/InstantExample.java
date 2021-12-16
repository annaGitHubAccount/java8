package chapter4_dateandtime;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Beispielprogramm zur Demonstration der neuen Klasse Instant.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class InstantExample {
    public static void main(final String[] args) {
        // Instants mit parse() und now() erzeugen
        final Instant silvester2013 = Instant.parse("2013-12-31T00:00:00Z");
        final Instant now = Instant.now();

        // Abfahrt 12:30 und Reisedauer 5 Stunden
        final Instant departureTime = Instant.parse("2015-02-07T12:30:00Z");
        final Instant expectedArrivalTime = departureTime.plus(5, ChronoUnit.HOURS);

        // Verspätung von 7 Minuten berechnen
        final Instant arrival = expectedArrivalTime.plus(7, ChronoUnit.MINUTES);
        final Instant arrival2 = expectedArrivalTime.plus(Duration.ofMinutes(7));

        System.out.println("now:       " + now);
        System.out.println("departure: " + departureTime);
        System.out.println("expected:  " + expectedArrivalTime);
        System.out.println("arrival:   " + arrival);
    }
}