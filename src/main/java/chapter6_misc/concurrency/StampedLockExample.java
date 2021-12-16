package chapter6_misc.concurrency;

import java.util.concurrent.locks.StampedLock;

/**
 * Beispielprogram illustriert feingranulares Locking mithilfe der Klasse StampedLock
 *
 * @author Michael Inden
 * <p>
 * Copyright 2015 by Michael Inden
 */
public class StampedLockExample {
    private int sharedValue = 0;

    private final StampedLock lock = new StampedLock();

    public int getSharedValue() {
        long stamp = lock.tryOptimisticRead();

        final int tmp = sharedValue;

        if (lock.validate(stamp)) {
            return tmp;
        } else {
            stamp = lock.readLock();
            try {
                return sharedValue;
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }

    public void setSharedValue(final int newValue) {
        long stamp = lock.writeLock();
        try {
            this.sharedValue = newValue;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
