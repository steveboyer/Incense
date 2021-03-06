package net.sereko.incense.tasks;

import android.util.Log;

import net.sereko.incense.model.Task;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/21/17.
 */

public class TaskService {
    private static final String TAG = TaskService.class.getSimpleName();

    public TaskService(){
        super();
    }

    public Observable<List<Task>> getTasks() {
        return Observable.create(new Observable.OnSubscribe<List<Task>>() {
            @Override
            public void call(Subscriber<? super List<Task>> subscriber){
                //Simulate some latency
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }

                List<Task> tasks = new ArrayList<>();
                for(int i = 0; i < 2; i++){
                    Task t = new Task();
                    t.setName("Name " + i);
                    Log.d(TAG, t.getName());
                    tasks.add(t);
                }

                subscriber.onNext(tasks);
                subscriber.onCompleted();
            }
        });
    }

}
