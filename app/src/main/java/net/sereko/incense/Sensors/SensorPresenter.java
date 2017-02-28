package net.sereko.incense.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
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
    private IScheduler scheduler;
    private IView<List<SKSensor>, SKSensor> sensorView;


    public SensorPresenter(SensorService service, IScheduler scheduler, IView<List<SKSensor>, SKSensor> view){
        super();
        this.sensorService = service;
        this.scheduler = scheduler;
        this.sensorView = view;
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

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        this.sensorView = null;
    }

    @Override
    public void setView(IView<List<SKSensor>, SKSensor> view) {
        this.sensorView = view;
    }

    private Observable<List<SKSensor>> getObservable(){
        return sensorService.getTasks().subscribeOn(scheduler.backgroundThread()).observeOn(scheduler.mainThread());
    }
}
