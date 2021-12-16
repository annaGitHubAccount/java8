package chapter6_misc.base64;

import java.util.Base64;

/**
 * Beispielprogramm demonstiert die Umwandlung eines Strings in eine Base64-Codierung.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class Base64Example {
    public static void main(final String[] args) {
        final byte[] bytes = "This is the Base64 Test".getBytes();

        final String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Base64 encoded = " + encoded);

        final byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("Base64 decoded = " + new String(decoded));
    }
}