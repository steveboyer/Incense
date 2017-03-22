package net.sereko.incense.decisions;

import android.util.Log;

import net.sereko.incense.model.Decision;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.SScheduler;
import net.sereko.incense.view.IListView;
import net.sereko.incense.view.IView;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class DecisionPresenter implements android.view.View.OnClickListener, IPresenter<Decision> {

    private final String TAG = DecisionPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private DecisionService decisionService;
    private SScheduler SScheduler;
    private IView<Decision> decisionView;
//    private DecisionAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public DecisionPresenter(DecisionService service, SScheduler SScheduler, IView<Decision> IView){
        super();
        this.decisionService = service;
        this.SScheduler = SScheduler;
        this.decisionView = IView;
        decisionView.setLoading(true);

        Log.w(TAG, TAG);
    }

    // Load SKSensors
    private Subscriber<Decision> getSubscriber(){
        return new Subscriber<Decision>() {
            @Override
            public void onStart(){
                super.onStart();
                decisionView.setLoading(true);
            }

            @Override
            public void onCompleted() {
                decisionView.setLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                decisionView.setLoading(false);
                decisionView.error(e);
            }

            @Override
            public void onNext(Decision decision) {
                decisionView.setModel(decision);

            }
        };
    }



    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public void start() {
//        subscription = getObservable().subscribe(getSubscriber());
//        skSensor.start();
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.decisionView = null;
        //skSensor.finish();
    }

    @Override
    public void setView(IView<Decision> IView) {
        this.decisionView = IView;
    }

    @Override
    public void setView(IListView<Decision> iListView) {

    }

    /**
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
//    private Observable<Decision> getObservable(){
//        return decisionService.getDecisions()
//                .subscribeOn(SScheduler.backgroundThread())
//                .observeOn(SScheduler.mainThread());
//    }


}
