package chapter4_dateandtime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

/**
 * Beispielprogramm zur Demonstration von Zeitzonen-Offsets.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class ZoneOffsetExample {
    public static void main(final String[] args) {
        final LocalDateTime ldt = LocalDateTime.now();
        final Stream<ZoneId> zoneIds = Stream.of(ZoneId.of("Europe/Berlin"), ZoneId.of("America/Los_Angeles"),
                ZoneId.of("Australia/Adelaide"));

        zoneIds.forEach(zoneIdName ->
        {
            final ZonedDateTime zdt = ldt.atZone(zoneIdName);
            final ZoneOffset offset = zdt.getOffset();

            System.out.format("offset for '%s' is %s\n", zoneIdName, offset);
        });
    }
}
