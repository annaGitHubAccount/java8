package chapter6_misc.math;

/**
 * Beispielprogramm demonstriert Überläufe bei Berechnungen.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class MathOverflowExample {
    public static void main(final String[] args) {
        final int reallyBig = Integer.MAX_VALUE - 7;
        final int negativeSurprise = reallyBig + 100;
        System.out.println(negativeSurprise);

        // JDK 8
        final int result = Math.addExact(reallyBig, 100);
        System.out.println(result);
    }
}
