package net.sereko.incense.util;

/**
 * Created by steve on 2/21/17.
 */
// SScheduler helps with testing multiple
public interface SScheduler {
    rx.Scheduler mainThread();
    rx.Scheduler backgroundThread();
}
