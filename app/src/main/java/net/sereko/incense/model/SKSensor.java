package net.sereko.incense.model;

import android.hardware.Sensor;

/**
 * Created by steve on 1/25/17.
 */

public class SKSensor {



    private String name;
    public float value;
    public float x_val;
    public float y_val;
    public float z_val;
    private Sensor sensor;

    private final String TAG = SKSensor.class.getSimpleName();

    public SKSensor(String name, Sensor sensor){
        this.name = name;
        this.sensor = sensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSensor(Sensor sensor){
        this.sensor = sensor;
    }



    public String getString (float f){
        return Float.toString(f);
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }

    public float getX_val() {
        return x_val;
    }

    public void setX_val(float x_val) {
        this.x_val = x_val;
    }

    public float getY_val() {
        return y_val;
    }

    public void setY_val(float y_val) {
        this.y_val = y_val;
    }

    public float getZ_val() {
        return z_val;
    }

    public void setZ_val(float z_val) {
        this.z_val = z_val;
    }

    public void setValues(float x_val, float y_val, float z_val){
        this.x_val = x_val;
        this.y_val = y_val;
        this.z_val = z_val;
    }


    public Sensor getSensor(){
        return this.sensor;
    }

    public class Truple<T> {
        T x;
        T y;
        T z;

        Truple(T x, T y, T z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
