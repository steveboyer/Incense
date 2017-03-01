package net.sereko.incense.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by steve on 1/25/17.
 */

public class SKSensor {

    private String name;
    private String value;
    private Sensor sensor;

    public SKSensor(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSensor(){

    }

}
