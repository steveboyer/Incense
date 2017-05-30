package net.sereko.incense.drivergrades;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import net.sereko.incense.model.SKSensor;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/27/17.
 */

public class DriverService {

    private static final String TAG = DriverService.class.getSimpleName();

    public SensorManager sensorManager;
    //private IListView<SKSensor> view;
    private SensorListener sensorListener;

    private class SensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.wtf(TAG, "onSensorChanged");


           float x, y, z;

            List<SKSensor> sensors = new ArrayList<>();
           // int count = view.getItemg(x) + " " + Float.toString(y) + " " + Float.toString(z));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    public DriverService(SensorManager sensorManager) {
        super();
        this.sensorManager = sensorManager;
        //this.sensorListener = new SensorListener();
    }

    public Observable<List<SKSensor>> getSKSensors() {
        return Observable.create(new Observable.OnSubscribe<List<SKSensor>>() {
            @Override
            public void call(Subscriber<? super List<SKSensor>> subscriber){
                //Simulate some latency
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    subscriber.onError(e);
//                }
                Log.wtf(TAG, "making sensors");
                ArrayList<SKSensor> skSensors = new ArrayList<>();




//                view.get
//
//                // @TODO
//                // Load sensors from prefs/db
//
                subscriber.onNext(skSensors);


                subscriber.onCompleted();
            }
        });
    }



   

    //@Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void start(){

    }

    public void finish() {
        //sensorManager.unregisterListener(this);
    }

}
