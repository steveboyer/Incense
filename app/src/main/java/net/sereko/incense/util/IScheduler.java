package net.sereko.incense.util;

/**
 * Created by steve on 2/21/17.
 */
// IScheduler helps with testing multiple
public interface IScheduler {
    rx.Scheduler mainThread();
    rx.Scheduler backgroundThread();
}
