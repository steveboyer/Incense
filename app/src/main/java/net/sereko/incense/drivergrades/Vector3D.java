package net.sereko.incense.drivergrades;

/**
 * Created by steve on 7/13/17.
 */

public class Vector3D {
    private double x, y, z;

    private double magnitude;


    public Vector3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public Vector3D(float[] values){
        this((double)values[0], (double)values[1], (double)values[2]);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getMagnitude() {
        return Math.pow(Math.pow(x,2) + Math.pow(y,2), 0.5);
    }

    /**
     * angleh: Angle of vector above+ or below- straight forward
     * anglev: Angle to left- or right+
     * anglev = angleh = 0 means no acceleration or acceleration directly along line of motion
     */
    public double getAngleH() {
        return Math.atan(y/x);
    }

    /**
     * angleh: Angle of vector above+ or below- straight forward
     * anglev: Angle to left- or right+
     * anglev = angleh = 0 means no acceleration or acceleration directly along line of motion
     */
    public double getAnglev(){
        return Math.atan(z/x);
    }
}
