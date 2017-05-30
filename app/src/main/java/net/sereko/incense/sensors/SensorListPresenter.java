package net.sereko.incense.sensors;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.Log;

import net.sereko.incense.model.SKSensor;
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
 * Created by steve on 1/25/17.
 */

public class SensorListPresenter implements android.view.View.OnClickListener, IListPresenter<SKSensor> {

    private final String TAG = SensorListPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private SensorService sensorService;
    //private Sensor sensor;
    private SKSensor skSensor;
    private SensorManager sensorManager;
    private IScheduler scheduler;
    private IListView<SKSensor> view;
//    private DecisionAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public SensorListPresenter(SensorService service, IScheduler SScheduler, IListView<SKSensor> view){
        super();
        this.sensorService = service;
        this.scheduler = SScheduler;
        this.view = view;
        //this.activity = activity;

        this.sensorManager = (SensorManager) view.getActivity().getSystemService(Context.SENSOR_SERVICE);

        Log.w(TAG, TAG);
        //this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Log.w(TAG, sensor.getName());

        //skSensor = new SKSensor("Accelerometer", sensor);
        //IView.addItem(skSensor);
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

    @Override
    public void setView(IListView<SKSensor> view) {
        this.view = view;
    }


    /**
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
    private Observable<List<SKSensor>> getObservable(){
        return sensorService.getSKSensors()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


}
