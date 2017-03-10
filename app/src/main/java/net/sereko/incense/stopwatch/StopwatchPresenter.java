package net.sereko.incense.stopwatch;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.Log;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.View;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class StopwatchPresenter implements android.view.View.OnClickListener, IPresenter<List<SKSensor>, SKSensor> {

    private final String TAG = StopwatchPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private StopwatchService stopwatchService;
    //private Sensor sensor;
    private SKSensor skSensor;
    private SensorManager sensorManager;
    private IScheduler scheduler;
    private View<List<SKSensor>, SKSensor> sensorView;
//    private SensorAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public StopwatchPresenter(StopwatchService service, IScheduler scheduler, View<List<SKSensor>, SKSensor> view){
        super();
        this.stopwatchService = service;
        this.scheduler = scheduler;
        this.sensorView = view;
        //this.activity = activity;

        this.sensorManager = (SensorManager) view.getActivity().getSystemService(Context.SENSOR_SERVICE);

        Log.w(TAG, TAG);
        //this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Log.w(TAG, sensor.getName());

        //skSensor = new SKSensor("Accelerometer", sensor);
        //view.addItem(skSensor);
//
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                //skSensor.start();
//            }
//        });
//        t.start();

    }

    // Load SKSensors
    private Subscriber<List<SKSensor>> getSubscriber(){
        return new Subscriber<List<SKSensor>>() {
            @Override
            public void onStart(){
                super.onStart();
                sensorView.setLoading(true);
            }

            @Override
            public void onCompleted() {
                sensorView.setLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                sensorView.setLoading(false);
                sensorView.error(e);
            }

            @Override
            public void onNext(List<SKSensor> skSensors) {
                sensorView.setModel(skSensors);

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
        this.sensorView = null;
        //skSensor.finish();
    }

    @Override
    public void setView(View<List<SKSensor>, SKSensor> view) {
        this.sensorView = view;
    }


    /**
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
    private Observable<List<SKSensor>> getObservable(){
        return stopwatchService.getSKSensors()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


}
