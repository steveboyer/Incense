package net.sereko.incense.stopwatch;

import android.content.Context;
import android.hardware.SensorManager;

import net.sereko.incense.model.SKSensor;
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
 * Created by steve on 1/25/17.
 */

public class StopwatchIPresenter implements android.view.View.OnClickListener, IPresenter<SKSensor> {

    private final String TAG = StopwatchIPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private StopwatchService stopwatchService;
    //private Sensor sensor;
    private SKSensor skSensor;
    private SensorManager sensorManager;
    private SScheduler SScheduler;
    private IListView<SKSensor> view;
//    private DecisionAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public StopwatchIPresenter(StopwatchService service, SScheduler SScheduler, IListView<SKSensor> IView){
        super();
        this.stopwatchService = service;
        this.SScheduler = SScheduler;
        this.view = view;
        //this.activity = activity;

        this.sensorManager = (SensorManager) IView.getActivity().getSystemService(Context.SENSOR_SERVICE);


    }

    // Load SKSensors
    private Subscriber<List<SKSensor>> getSubscriber(){
        return new Subscriber<List<SKSensor>>() {
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
            public void onNext(List<SKSensor> skSensors) {
                view.setModel(skSensors);

            }
        };
    }



    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public void start() {
        subscription = getObservable().subscribe(getSubscriber());
//        skSensor.start();
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.view = null;
        //skSensor.finish();
    }

    @Override
    public void setView(IView<SKSensor> iView) {

    }

//    @Override
//    public void setView(IView<SKSensor> iView) {
//
//    }

//    @Override
//    public void setView(IListView<SKSensor> iListView) {
//
//    }

    @Override
    public void setView(IListView<SKSensor> view) {
        this.view = view;
    }


    /**
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
    private Observable<List<SKSensor>> getObservable(){
        return stopwatchService.getSKSensors()
                .subscribeOn(SScheduler.backgroundThread())
                .observeOn(SScheduler.mainThread());
    }


}
