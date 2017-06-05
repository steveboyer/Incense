package net.sereko.incense.stopwatch;

import android.content.Context;
import android.hardware.SensorManager;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.presenter.IAdapterPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IAdapterView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class StopwatchPresenter implements android.view.View.OnClickListener, IAdapterPresenter<SKSensor> {

    private final String TAG = StopwatchPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private StopwatchService stopwatchService;
    //private Sensor sensor;
    private SKSensor skSensor;
    private SensorManager sensorManager;
    private IScheduler IScheduler;
    private IAdapterView<SKSensor> view;
//    private DecisionAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public StopwatchPresenter(StopwatchService service, IScheduler SScheduler, IAdapterView<SKSensor> IView){
        super();
        this.stopwatchService = service;
        this.IScheduler = SScheduler;
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


//    @Override
//    public void setView(IView<SKSensor> iView) {
//
//    }


    @Override
    public void setView(IAdapterView<SKSensor> view) {
        this.view = view;
    }


    /**
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
    private Observable<List<SKSensor>> getObservable(){
        return stopwatchService.getSKSensors()
                .subscribeOn(IScheduler.backgroundThread())
                .observeOn(IScheduler.mainThread());
    }


}
