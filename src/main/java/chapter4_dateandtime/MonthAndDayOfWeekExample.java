package chapter4_dateandtime;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Beispielprogramm zur Demonstration der neuen Klassen Month und DayOfWeek.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class MonthAndDayOfWeekExample {
    public static void main(final String[] args) {
        final DayOfWeek sunday = DayOfWeek.SUNDAY;
        final Month february = Month.FEBRUARY;

        System.out.println(sunday.plus(5)); // FRIDAY
        System.out.println(february.plus(10)); // DECEMBER
    }
}