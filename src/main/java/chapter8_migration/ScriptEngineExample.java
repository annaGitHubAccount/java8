package chapter8_migration;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * Beispielprogramm gibt Informationen zur verwendeten JavaScript-Engine aus.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class ScriptEngineExample {
    public static void main(final String args[]) {
        final ScriptEngineManager manager = new ScriptEngineManager();

        for (final ScriptEngineFactory factory : manager.getEngineFactories()) {
            System.out.println(factory.getEngineName());
            System.out.println(factory.getEngineVersion());
            System.out.println(factory.getLanguageName());
            System.out.println(factory.getLanguageVersion());
        }
    }
}
