package net.sereko.incense.model;

/**
 * Created by steve on 1/25/17.
 */

public class SKSensor {

    private String name;
    private String value;

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


}
