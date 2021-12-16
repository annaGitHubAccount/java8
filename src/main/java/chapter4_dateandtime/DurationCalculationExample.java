package chapter4_dateandtime;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Beispielprogramm zur Demonstration von Berechnungen mit neuen Klasse Duration.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class DurationCalculationExample {
    public static void main(final String[] args) {
        // Erzeugung  
        final Instant christmas2013 = Instant.parse("2013-12-24T00:00:00Z");
        final Instant silvester2013 = Instant.parse("2013-12-31T00:00:00Z");
        final Instant jdk8Release = Instant.parse("2014-03-18T00:00:00Z");

        // Vergelcihswerte errechnen  
        System.out.println(Duration.between(christmas2013, silvester2013));
        System.out.println(Duration.between(silvester2013, jdk8Release));

        // Berechnungen  
        final Instant calcSilvester_1 = christmas2013.plus(Duration.ofDays(7));
        final Instant calcSilvester_2 = christmas2013.plus(7, ChronoUnit.DAYS);

        System.out.println(calcSilvester_1);
        System.out.println(calcSilvester_2);
    }
}