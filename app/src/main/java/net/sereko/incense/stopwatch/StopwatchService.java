package net.sereko.incense.stopwatch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/27/17.
 */

public class StopwatchService {

    private static final String TAG = StopwatchService.class.getSimpleName();

    public SensorManager sensorManager;
    private View<List<SKSensor>, SKSensor> view;
    private SensorListener sensorListener;

    private class SensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.wtf(TAG, "onSensorChanged");


           float x, y, z;

            ArrayList<SKSensor> sensors = new ArrayList<>();
            int count = view.getItemCount();
            Log.wtf(TAG, "Count: " + count);
            for(int i = 0 ; i < count ; i++){
                SKSensor sensor = view.getItem(i);

                if(sensor == null){
                    Log.wtf(TAG, "SKSensor null");
                    return;
                }

                if(sensor.getSensor() == null){
                    Log.wtf(TAG, "Sensor null");
                    return;
                }

                if(sensor.getSensor().equals(sensorEvent.sensor)){
                    int type = sensor.getSensor().getType();

                    switch(type){
                        case Sensor.TYPE_ACCELEROMETER:
                            x = sensorEvent.values[0];
                            y = sensorEvent.values[1];
                            z = sensorEvent.values[2];
                            sensor.setValues(x, y, z);
                            break;
                        case Sensor.TYPE_AMBIENT_TEMPERATURE:
                            x = sensorEvent.values[0];
                            sensor.setValue(x);
                        default:
                            Log.wtf(TAG, "type: " + type);
                            throw  new RuntimeException();
                    }



                    //view.addItem(sensor);
                    //List<SKSensor> sensors = new ArrayList<>();
                    //sensors.add(sensor);

                }
                sensors.add(sensor);
            }
            view.setModel(sensors);
            //Log.wtf(TAG, Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    public StopwatchService(View<List<SKSensor>, SKSensor> view, SensorManager sensorManager) {
        super();
        this.view = view;
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
                int count = view.getItemCount();
                ArrayList<SKSensor> skSensors = new ArrayList<>();

                if(count == 0) {

                    Sensor s = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    SKSensor accel = new SKSensor("ACCEL", s);
                    skSensors.add(accel);

                    SKSensor ambient_temp = new SKSensor("Ambient Temp", sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
                    skSensors.add(ambient_temp);
                    for (SKSensor skSensor : skSensors) {
                        Log.wtf(TAG, Float.toString(skSensor.getZ_val()));
                        sensorManager.registerListener(new SensorListener(),
                                skSensor.getSensor(),
                                SensorManager.SENSOR_DELAY_NORMAL);
                    }
                } else {
                    for(int i = 0; i < count; i++){
                        SKSensor sensor = view.getItem(i);

                        skSensors.add(sensor);
                    }
                }


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

    public Observable<List<SKSensor>> updateSKSensors(){
        return Observable.create(new Observable.OnSubscribe<List<SKSensor>>(){
            @Override
            public void call(Subscriber<? super List<SKSensor>> subscriber){
                int count = view.getItemCount();

                for(int i = 0 ; i < count ; i++){
                    SKSensor sensor = view.getItem(i);
                }


            }
        });
    }

    //@Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.wtf(TAG, "onSensorChanged");
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        int count = view.getItemCount();
        for(int i = 0 ; i < count ; i++){
            SKSensor sensor = view.getItem(i);
            if(sensor.getSensor().equals(sensorEvent.sensor)){
                sensor.setValues(x, y, z);
                //view.addItem(sensor);
                view.insert(sensor, i);
            }
        }
        Log.wtf(TAG, Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z));
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
