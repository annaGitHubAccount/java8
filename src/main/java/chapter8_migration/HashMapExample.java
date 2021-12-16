package chapter8_migration;

import java.util.HashMap;
import java.util.Map;

/**
 * Beispielprogramm zeigt die unterschiedliche Reihenfolge der Speicherung in HashMaps bei JDK 7 bzw. JDK 8.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class HashMapExample {
    public static void main(String[] args) {
        final Map<String, String> map = new HashMap<>();
        map.put("first", "first");
        map.put("second", "second");

        System.out.println(map);
    }
}
