package net.sereko.incense.util;

import rx.Scheduler;

/**
 * Created by steve on 2/21/17.
 */
// Scheduler helps with testing multiple
public interface IScheduler {
    Scheduler mainThread();
    Scheduler backgroundThread();
}
