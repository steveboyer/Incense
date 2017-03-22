package net.sereko.incense.util;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by steve on 2/21/17.
 */

public class AppScheduler implements SScheduler {
    @Override
    public rx.Scheduler mainThread(){
        return AndroidSchedulers.mainThread();
    }

    @Override
    public rx.Scheduler backgroundThread(){
        return Schedulers.io();
    }
}
