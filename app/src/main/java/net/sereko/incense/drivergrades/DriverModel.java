package net.sereko.incense.drivergrades;

/**
 * Created by steve on 6/4/17.
 */

public class DriverModel {
    private double latitude, longitude, speed, altitude;
    private Vector3D accel;



    private Vector3D accelAvg;
    private Vector3D grav, gravAvg;

    public DriverModel(){
        this.latitude = 0d;
        this.longitude = 0d;
        this.speed = 0d;
        this.altitude = 0d;

        this.accel = new Vector3D(0d, 0d, 0d);
        this.grav = new Vector3D(0d, 0d, 0d);
        this.accelAvg = new Vector3D(0d, 0d, 0d);
        this.gravAvg = new Vector3D(0d, 0d, 0d);
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

    public Vector3D getAccel() {
        return accel;
    }

    public Vector3D getGrav() {
        return grav;
    }

    public void setGrav(Vector3D grav){
        this.grav = grav;
    }

    public void setAccel(Vector3D accel){
        this.accel = accel;
    }

    public Vector3D getAccelAvg() {
        return accelAvg;
    }

    public void setAccelAvg(Vector3D accelAvg) {
        this.accelAvg = accelAvg;
    }

    public Vector3D getGravAvg() {
        return gravAvg;
    }

    public void setGravAvg(Vector3D gravAvg) {
        this.gravAvg = gravAvg;
    }

}
