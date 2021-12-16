package chapter2_lambdas.lambdas;

/**
 * Beispielprogramm zur Demonstration der Unterschiede zwischen Lambdas und inneren Klassen beim Zugriff auf
 * Variablen usw.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class LambdaVsInnerClassExample {

    private String outerAttribute = "fromOutside";

    public static void main(final String[] args) {
        new LambdaVsInnerClassExample().executeMethodAndLambda();
    }

    private void executeMethodAndLambda() {
        int effectivelyFinal = 4711;

        final Runnable asNormalMethod = new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
                // Nicht finale Variable war bis JDK 7 nicht referenzierbar
                System.out.println("effectivelyFinal = " + effectivelyFinal);
                // Spezielle Syntax zum Zugriff auf Attribute der auusseren Klasse
                System.out.println("outerAttribute = " + LambdaVsInnerClassExample.this.outerAttribute);
            }

            public String anotherMethod() {
                return "Anonymous Runnable";
            }
        };

        // Man kann keine weiteren Methoden in Lambdas definieren
        final Runnable asLambda = () ->
        {
            System.out.println(this);
            // Nicht finale Variable war bis JDK 7 nicht referenzierbar
            System.out.println("effectivelyFinal = " + effectivelyFinal);
            System.out.println("outerAttribute = " + this.outerAttribute);
        };

        asNormalMethod.run();
        asLambda.run();
    }
}