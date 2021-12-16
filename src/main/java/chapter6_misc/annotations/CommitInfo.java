package chapter6_misc.annotations;

import java.lang.annotation.Repeatable;

/**
 * Beispielprogramm zur Wiederholbarkeit von Annotations.
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
@Repeatable(CommitInfoContainer.class)
public @interface CommitInfo {
    String commiter();

    String info();
}
