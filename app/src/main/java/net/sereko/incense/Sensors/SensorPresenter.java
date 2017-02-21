package net.sereko.incense.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import net.sereko.incense.Activity;
import net.sereko.incense.Presenter;

/**
 * Created by steve on 1/25/17.
 */

public class SensorPresenter implements Presenter, SensorEventListener{

    private Activity activity;

    @Override
    public void attachView(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
