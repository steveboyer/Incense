package net.sereko.incense.tasks;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;
import net.sereko.incense.presenter.IListPresenter;
import net.sereko.incense.util.IScheduler;
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

public class TaskListPresenter implements IListPresenter<Task>, android.view.View.OnClickListener {
    private final String TAG = TaskListPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private TaskService taskService;
    private IScheduler scheduler;
    private IListView<Task> view;

    TaskListPresenter(TaskService service, IScheduler scheduler, IListView<Task> view){
        super();
        this.taskService = service;
        this.view = view;

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
    public void setView(IView<Task> iView) {

    }

    @Override
    public void setView(IListView<Task> IView) {
        this.view = IView;
    }

    private Observable<List<Task>> getObservable(){
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
                this.view.addItem(t);
                break;
            default:
                throw new RuntimeException();
        }
    }
}
