package net.sereko.incense.tasks;

import android.util.Log;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.SScheduler;
import net.sereko.incense.view.IListView;
import net.sereko.incense.view.IView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 2/20/17.
 */

public class TaskPresenter implements IPresenter<Task>, android.view.View.OnClickListener {
    private final String TAG = TaskPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private TaskService taskService;
    private SScheduler SScheduler;
    private IListView<Task> view;

    TaskPresenter(TaskService service, SScheduler SScheduler, IListView<Task> view){
        super();
        this.taskService = service;
        this.view = view;

        //By using this SScheduler we can run the same presenter in various ways
        //When run in Android land we run the work asynchronously and push results to the UI thread
        //When run in the tests we run the work and publish results on the same thread
        this.SScheduler = SScheduler;
    }

    private Subscriber<List<Task>> getSubscriber() {
        return new Subscriber<List<Task>>() {
            @Override
            public void onStart(){
                super.onStart();
                view.setLoading(true);
                Log.d(TAG, "}onStart");
            }

            @Override
            public void onCompleted() {
                view.setLoading(false);
                Log.d(TAG, "}onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                view.setLoading(false);
                view.error(e);
                Log.d(TAG, "}onError");
            }

            @Override
            public void onNext(List<Task> tasks) {
                view.setModel(tasks);
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
        this.view = null;
        Log.d(TAG, "}finish()");
    }

    @Override
    public void setView(IView<Task> iView) {

    }

//    @Override
//    public void setView(IListView<Task> iListView) {
//
//    }

    @Override
    public void setView(IListView<Task> IView) {
        this.view = IView;
    }

    private Observable<List<Task>> getObservable(){
        return taskService.getTasks()
                .subscribeOn(SScheduler.backgroundThread())
                .observeOn(SScheduler.mainThread());
    }


    @Override
    public void onClick(android.view.View view) {
        int id = view.getId();

        switch (id){
            case R.id.fab:
                Task t = new Task();
                t.setName("HEllo");
                this.view.addItem(t);
                break;
            default:
                throw new RuntimeException();
        }
    }
}
