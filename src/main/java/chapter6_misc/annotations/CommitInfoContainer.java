package chapter6_misc.annotations;

/**
 * Beispielprogramm zur Wiederholbarkeit von Annotations.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public @interface CommitInfoContainer {
    CommitInfo[] value();
}
