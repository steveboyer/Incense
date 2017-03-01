package net.sereko.incense.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class SensorPresenter implements SensorEventListener, View.OnClickListener, IPresenter<List<SKSensor>, SKSensor> {

    private final String TAG = SensorPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private SensorService sensorService;
    private Sensor sensor;
    private SensorManager sensorManager;
    private IScheduler scheduler;
    private IView<List<SKSensor>, SKSensor> sensorView;

    private Activity activity;

    public SensorPresenter(SensorService service, IScheduler scheduler, IView<List<SKSensor>, SKSensor> view, Activity activity){
        super();
        this.sensorService = service;
        this.scheduler = scheduler;
        this.sensorView = view;
        this.activity = activity;

        this.sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE);

        Log.w(TAG, TAG);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.w(TAG, sensor.getName());
    }

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
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.wtf(TAG, "onSensorChanged");
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        Log.wtf(TAG, Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void start() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        subscription = getObservable().subscribe(getSubscriber());
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.sensorView = null;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void setView(IView<List<SKSensor>, SKSensor> view) {
        this.sensorView = view;
    }

    private Observable<List<SKSensor>> getObservable(){
        return sensorService.getTasks()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


}
