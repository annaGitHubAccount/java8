package chapter4_dateandtime;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Beispielprogramm zur Demonstration der neuen Klasse ZonedDateTime.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class ZonedDateTimeExample {
    public static void main(final String[] args) {
        // Aktuelle Zeit als ZonedDateTime-Objekt ermitteln
        final ZonedDateTime now = ZonedDateTime.now();

        // Dort die Uhrzeit aendern und in neuem Objekt speichern
        final ZonedDateTime nowButChangedTime = now.withHour(11).withMinute(44);

        // Neues Objekt mit veraendertem Datum erzeugen
        final ZonedDateTime dateAndTime = nowButChangedTime.withYear(2008).
                withMonth(9).
                withDayOfMonth(29);

        // Neues Objekt mit veraendertem Datum erzeugen und wechseln der Zeitzone
        final ZonedDateTime dateAndTime2 = nowButChangedTime.withYear(2008).
                withMonth(Month.SEPTEMBER.getValue()).
                withDayOfMonth(29).
                withZoneSameInstant(ZoneId.of("GMT"));

        System.out.println("now:          " + now);
        System.out.println("-> 11:44:     " + nowButChangedTime);
        System.out.println("-> 29.9.2008: " + dateAndTime);
        System.out.println("-> 29.9.2008: " + dateAndTime2);
    }
}