package chapter8_migration;

import java.text.NumberFormat;

/**
 * Beispielprogramm zeigt Unterschiede zwischen JDK 7 und JDK 8 bei Rundungen und NumberFormat.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class NumberFormatExample {
    public static void main(final String[] args) {
        final NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);

        System.out.println(format.format(1.65));
        System.out.println(format.format(6.75));
        System.out.println(format.format(83.65));
    }
}

