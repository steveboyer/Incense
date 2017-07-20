package net.sereko.incense.drivergrades;

/**
 * Created by steve on 6/4/17.
 */

public class DriverModel {
    private double latitude, longitude, speed, altitude;
    private Vector3D acceleration;
    private Vector3D gravity;

    public DriverModel(){
        this.latitude = 0d;
        this.longitude = 0d;
        this.speed = 0d;
        this.altitude = 0d;

        this.acceleration = new Vector3D(0d, 0d, 0d);
        this.gravity = new Vector3D(0d, 0d, 0d);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Vector3D getAcceleration() {
        return acceleration;
    }

    public Vector3D getGravity() {
        return gravity;
    }

    public void setGravity(Vector3D gravity){
        this.gravity = gravity;
    }

    public void setAcceleration(Vector3D acceleration){
        this.acceleration = acceleration;
    }

}
