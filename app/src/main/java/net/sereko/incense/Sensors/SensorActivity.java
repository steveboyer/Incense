package net.sereko.incense.Sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Created by steve on 2/15/17.
 */

public class SensorActivity extends Activity {
    private Sensor mLight;

    private SensorAdapter adapter;
    private SensorManager mSensorManager;
    private Sensor mSensor;


    private final String TAG = getClass().getSimpleName();


    public void readSensor(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }
}
