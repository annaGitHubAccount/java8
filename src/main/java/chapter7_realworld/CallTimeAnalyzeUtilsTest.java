package chapter7_realworld;
/**
import org.junit.Test;

import static org.junit.Assert.*;


 * Rudimentäre Test-Klasse prüft die Klasse CallTimeAnalyzeUtils.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden

public class CallTimeAnalyzeUtilsTest {
    @Test
    public void extractCallTime() {
        final String line1 = "22.04.2015  invoked: abc()  calltime 500 ms";
        final String line2 = "22.04.2015  invoked: def(1)  call_time:= 750 ms";

        final long callTimeMs1 = CallTimeAnalyzeUtils.extractCallTime(line1);
        final long callTimeMs2 = CallTimeAnalyzeUtils.extractCallTime(line2);

        assertEquals(500, callTimeMs1);
        assertEquals(750, callTimeMs2);
    }

    @Test
    public void extractCallTime_no_calltime_given() {
        final String line = "22.04.2015  Starting external calls to system A";

        final long callTimeMs = CallTimeAnalyzeUtils.extractCallTime(line);

        assertEquals(-1, callTimeMs);
    }
}

 */