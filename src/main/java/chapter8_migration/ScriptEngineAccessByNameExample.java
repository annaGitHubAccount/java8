package chapter8_migration;

import javax.script.ScriptEngineManager;

/**
 * Beispielprogramm zeigt die unterschiedliche Informationen zur verwendeten JavaScript-Engines bei JDK 7 bzw. JDK 8.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class ScriptEngineAccessByNameExample {
    public static void main(final String args[]) {
        final ScriptEngineManager manager = new ScriptEngineManager();

        //                                                            JDK 8 JDK 7
        System.out.println(manager.getEngineByName("Nashorn"));    //   +     -
        System.out.println(manager.getEngineByName("nashorn"));    //   +     -
        System.out.println(manager.getEngineByName("Rhino"));      //   -     -
        System.out.println(manager.getEngineByName("rhino"));      //   -     +
        System.out.println(manager.getEngineByName("JavaScript")); //   +     +
        System.out.println(manager.getEngineByName("javascript")); //   +     +

        System.out.println(manager.getEngineByExtension("js"));    //   +     +
    }
}

