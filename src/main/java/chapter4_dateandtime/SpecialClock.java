package chapter4_dateandtime;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Realisierung einer speziellen Uhr, deren Zeit mit einem Faktor schneller oder langsamer laufen kann.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class SpecialClock extends Clock {
    private final Clock baseClock;
    private final Instant startInstant;
    private final double factor;

    public SpecialClock(final Clock baseClock, final double factor) {
        this.baseClock = baseClock;
        this.startInstant = baseClock.instant();
        this.factor = factor;
    }

    @Override
    public ZoneId getZone() {
        return baseClock.getZone();
    }

    @Override
    public Clock withZone(final ZoneId zone) {
        Objects.requireNonNull(zone, "ZoneId must not be null!");
        if (zone.equals(baseClock.getZone())) {
            return this;
        }
        return new SpecialClock(baseClock.withZone(zone), factor);
    }

    @Override
    public long millis() {
        final long millis = baseClock.millis() - startInstant.toEpochMilli();
        return (long) (millis * factor);
    }

    @Override
    public Instant instant() {
        return Instant.ofEpochMilli(millis() + startInstant.toEpochMilli());
    }

    @Override
    public String toString() {
        return "SpecialClock [baseClock=" + baseClock + ", factor=" + factor + "]";
    }
}