package chapter4_dateandtime;

import java.time.Clock;
import java.time.LocalTime;

/**
 * Beispielprogramm zur Demonstration einer eigenen Implementierung der Klasse Clock.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class SpecialClockExample {
    public static void main(final String[] args) throws InterruptedException {
        final Clock defaultClock = Clock.systemDefaultZone();
        final SpecialClock fastClock = new SpecialClock(defaultClock, 100.0);
        final SpecialClock slowClock = new SpecialClock(defaultClock, 0.1);

        for (int i = 0; i < 20; i++) {
            printTime(i, fastClock, slowClock);
            waitForOneSecond();
        }
    }

    private static void printTime(final int counter, final SpecialClock fastClock, final SpecialClock slowClock) {
        System.out.println(counter + ": " + LocalTime.now() +
                " / fast: " + LocalTime.now(fastClock) +
                " / slow: " + LocalTime.now(slowClock));
    }

    private static void waitForOneSecond() throws InterruptedException {
        Thread.sleep(1_000);
    }
}