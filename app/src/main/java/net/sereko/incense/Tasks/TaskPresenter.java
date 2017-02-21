package net.sereko.incense.tasks;

import net.sereko.incense.model.Task;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.service.TaskService;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 2/20/17.
 */

public class TaskPresenter implements IPresenter<List<Task>> {
    private Subscription subscription = Subscriptions.empty();
    private TaskService taskService;
    private IScheduler scheduler;
    private IView<List<Task>> view;

    public TaskPresenter(TaskService service, IScheduler scheduler){
        super();
        this.taskService = service;
        this.scheduler = scheduler;
        //By using this scheduler we can run the same presenter in various ways
        //When run in Android land we run the work asynchronously and push results to the UI thread
        //When run in the tests we run the work and publish results on the same thread
    }

    private Subscriber<List<Task>> getSubscriber() {
        return new Subscriber<List<Task>>() {
            @Override
            public void onStart(){
                super.onStart();
                view.setLoading(true);
            }

            @Override
            public void onCompleted() {
                view.setLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                view.setLoading(false);
                view.error(e);
            }

            @Override
            public void onNext(List<Task> tasks) {
                view.setModel(tasks);
            }
        };
    }

    @Override
    public void start() {
        subscription = getObservable().subscribe(getSubscriber());
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.view = null;
    }

    @Override
    public void setView(IView<List<Task>> view) {
        this.view = view;
    }

    private Observable<List<Task>> getObservable(){
        return taskService.getTasks()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


}
