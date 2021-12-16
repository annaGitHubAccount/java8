package chapter6_misc.annotations;

/**
 * Beispielprogramm zur Wiederholbarkeit von Annotations.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
@CommitInfo(commiter = "Mike", info = "info")
@CommitInfo(commiter = "Andi", info = "some minor changes")
public class RepeatableAnnotationExample {
    // ...
}