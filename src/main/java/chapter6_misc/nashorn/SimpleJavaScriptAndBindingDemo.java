package chapter6_misc.nashorn;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Beispielprogramm verdeutlicht, wie man JavaScript-Anweisungen ausfuehren kann.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2014 by Michael Inden
 */
public class SimpleJavaScriptAndBindingDemo {
    public static void main(final String[] args) throws Exception {
        final ScriptEngineManager manager = new ScriptEngineManager();
        final ScriptEngine engine = manager.getEngineByName("js");

        // Executing  // Ist mit JDK 7 noch erlaubt
        // JDK 8: Exception in thread "main" javax.script.ScriptException: ReferenceError: "println" is not defined in <eval> at line number 1
        engine.eval("print('Hello! JavaScript executed from a Java program.')");

        // Data Binding
        engine.put("a", 2);
        engine.put("b", 7);

        final Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);

        final Object a = bindings.get("a");
        final Object b = bindings.get("b");

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // Berechnung ausfuehren
        final Object result = engine.eval("a + b");
        System.out.println("a + b = " + result);

        // Berechnung, Ergebnis wird einer JavaScript-Variablen zugewiesen und spaeter aus Java gelesen
        final String script = "var ergebnis = Math.max(a, b)";
        engine.eval(script);
        final Object result2 = engine.get("ergebnis");
        System.out.println("Math.max(a, b) = " + result2);


        // Enter formula
        final String calculation = "7 * (x * x) + (3 - x) * (x + 3) / 10";
        ;
        System.out.println("f(x) = " + calculation);

        for (int x = -10; x <= 10; x++) {
            engine.put("x", x);

            final Object calculationResult = engine.eval(calculation);
            System.out.println("x = " + x + "\t / f(x) = " + calculationResult);
        }

        // Compiling
        System.out.println("Compiling Script");

        final Compilable compEngine = (Compilable) engine;
        final CompiledScript compiled = compEngine.compile("a-b");

        System.out.println(compiled.eval(bindings));
    }
}