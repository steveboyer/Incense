package net.sereko.incense.drivergrades;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by steve on 6/5/17.
 */

public class SensorListener implements SensorEventListener {
    DriverPresenter presenter;
    //SensorAveragerController averager;
    float lastAccel[] = new float[3];
    float accelFilter[] = new float[3];

    float lastGrav[] = new float[3];
    float gravFilter[] = new float[3];
    private static final boolean ADAPTIVE_ACCEL_FILTER = false;
    private static final boolean ADAPTIVE_GRAV_FILTER = false;

    public SensorListener(DriverPresenter presenter){
        super();
        this.presenter = presenter;
        //this.averager = new SensorAveragerController();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values;
        switch(event.sensor.getType()){
            case Sensor.TYPE_GRAVITY:
                values = event.values;
                presenter.onGravityChanged(new Vector3D(values));
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                values = event.values;
                presenter.onAccelChanged(new Vector3D(values));
                break;
            default:
                throw new RuntimeException();
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Required by interface, not using
    }

    //
    public float norm(float x, float y, float z){
        return (float)Math.sqrt((Math.pow((double)x, 2d) + Math.pow((double)y, 2d) + Math.pow((double)z, 2d)));
    }

    public float clamp(float v, float min, float max){
        if(v > max){
            return max;
        } else if(v < min){
            return min;
        } else {
            return v;
        }
    }

}
