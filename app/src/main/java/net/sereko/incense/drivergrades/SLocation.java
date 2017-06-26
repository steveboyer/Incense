package net.sereko.incense.drivergrades;

/**
 * Created by steve on 6/5/17.
 */

public class SLocation extends SSensorData {

    public Double longitude; // degrees
    public Double latitude; // degrees
    public Double altitude; // meters
    public Double speed;  // m/s

    public SLocation(double longitude, double latitude, double altitude, double speed){
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
    }

}
