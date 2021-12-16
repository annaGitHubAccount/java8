package chapter4_dateandtime.temporaladjusters;

import static java.time.Month.*;

import java.time.LocalDate;

/**
 * Beispielprogramm zur Demonstration des selbstdefinierten NextPaydayAdjuster.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class NextPaydayExample {
    public static void main(final String[] args) {
        final LocalDate jan = LocalDate.of(2015, JANUARY, 31);
        final LocalDate nextPayday1 = jan.with(new NextPaydayAdjuster());

        final LocalDate feb = LocalDate.of(2015, FEBRUARY, 7);
        final LocalDate nextPayday2 = feb.with(new NextPaydayAdjuster());

        System.out.println("Next Payday Jan: " + nextPayday1);
        System.out.println("Next Payday Feb: " + nextPayday2);
    }
}

