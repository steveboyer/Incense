package net.sereko.incense.tasks;

import android.util.Log;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.View;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 2/20/17.
 */

public class TaskPresenter implements IPresenter<List<Task>, Task>, android.view.View.OnClickListener {
    private final String TAG = TaskPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private TaskService taskService;
    private IScheduler scheduler;
    private View<List<Task>,Task> taskView;

    TaskPresenter(TaskService service, IScheduler scheduler, View<List<Task>, Task> view){
        super();
        this.taskService = service;
        this.taskView = view;

        //By using this scheduler we can run the same presenter in various ways
        //When run in Android land we run the work asynchronously and push results to the UI thread
        //When run in the tests we run the work and publish results on the same thread
        this.scheduler = scheduler;
    }

    private Subscriber<List<Task>> getSubscriber() {
        return new Subscriber<List<Task>>() {
            @Override
            public void onStart(){
                super.onStart();
                taskView.setLoading(true);
                Log.d(TAG, "}onStart");
            }

            @Override
            public void onCompleted() {
                taskView.setLoading(false);
                Log.d(TAG, "}onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                taskView.setLoading(false);
                taskView.error(e);
                Log.d(TAG, "}onError");
            }

            @Override
            public void onNext(List<Task> tasks) {
                taskView.setModel(tasks);
                Log.d(TAG, "}onNext");
            }
        };
    }

    @Override
    public void start() {
        subscription = getObservable().subscribe(getSubscriber());
        Log.d(TAG, "}start()");
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.taskView = null;
        Log.d(TAG, "}finish()");
    }

    @Override
    public void setView(View<List<Task>, Task> view) {
        this.taskView = view;
        Log.d(TAG, "}setView()");
    }

    private Observable<List<Task>> getObservable(){
        Log.d(TAG, "{getObservable()");
        return taskService.getTasks()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


    @Override
    public void onClick(android.view.View view) {
        int id = view.getId();

        switch (id){
            case R.id.fab:
                Task t = new Task();
                t.setName("HEllo");
                taskView.addItem(t);
                break;
            default:
                throw new RuntimeException();
        }
    }
}
