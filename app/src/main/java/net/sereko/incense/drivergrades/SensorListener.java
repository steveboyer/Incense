package net.sereko.incense.drivergrades;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by steve on 6/5/17.
 */

public class SensorListener implements SensorEventListener {
    DriverPresenter presenter;
    SensorAveragerController averager;

    public SensorListener(DriverPresenter presenter){
        super();
        this.presenter = presenter;
        this.averager = new SensorAveragerController();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
            presenter.onGravityChanged(averager.updateGravity((double)event.values[0], (double)event.values[1], (double)event.values[2]));
        }
        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            presenter.onAccelChanged(averager.updateAccel((double)event.values[0], (double)event.values[1], (double)event.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Required by interface, not using
    }

}
