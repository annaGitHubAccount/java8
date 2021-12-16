package chapter4_dateandtime;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Beispielprogramm zur Demonstration einiger Besonderheiten der neuen Klasse Duration.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class DurationSpecialExample {
    public static void main(final String[] args) {
        // Erzeugung  
        final Instant christmas2013 = Instant.parse("2013-12-24T00:00:00Z");
        final Instant silvester2013 = Instant.parse("2013-12-31T00:00:00Z");

        // Achtung: Duration bietet nicht ofWeeks(long) oder ofMonths(long)  
        final Instant calcSilvester_3 = christmas2013.plus(1, ChronoUnit.WEEKS);
        final Instant calcJdk8Release = silvester2013.plus(3, ChronoUnit.MONTHS).plus(Duration.ofDays(18));

        System.out.println(calcSilvester_3);
        System.out.println(calcJdk8Release);
    }
}