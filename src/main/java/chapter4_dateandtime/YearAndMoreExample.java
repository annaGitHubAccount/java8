package chapter4_dateandtime;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

/**
 * Beispielprogramm zur Demonstration der neuen Klassen Year und YearMonth sowie Month.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014, 2015 by Michael Inden
 */
public class YearAndMoreExample {
    public static void main(final String[] args) {
        // MonthDay: Achtung, amerikanische Reihenfolge Monat, Tag  
        final int dayOfBirthday = 7;
        final MonthDay monthDay = MonthDay.of(Month.FEBRUARY, dayOfBirthday);

        // YearMonth: Zur Lesbarkeit besser Month statisch importieren  
        final YearMonth yearMonth = YearMonth.of(2014, Month.FEBRUARY);

        // Year 
        final Year year = Year.of(2012);

        System.out.println("MonthDay:  " + monthDay);
        System.out.println("YearMonth: " + yearMonth);
        System.out.println("Year:      " + year + " / isLeap? " + year.isLeap());
    }
}