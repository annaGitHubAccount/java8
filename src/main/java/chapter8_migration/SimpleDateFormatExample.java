package chapter8_migration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Beispielprogramm zeigt die unterschiedliche Reihenfolge der Speicherung in HashMaps bei JDK 7 bzw. JDK 8.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class SimpleDateFormatExample {
    public static void main(final String[] args) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM yyy");
        final Date march2014 = new Date(2014 - 1900, 2, 18);

        System.out.println(sdf.format(march2014));
    }
}
