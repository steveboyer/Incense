package net.sereko.incense.Sensors;

/**
 * Created by steve on 1/25/17.
 */

public class Sensor {

    private String name;
    private String value;

    public Sensor(String name){
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
