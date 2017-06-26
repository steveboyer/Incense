package net.sereko.incense.drivergrades;

import net.sereko.incense.decisions.ExponentialMovingAverage;

/**
 * Created by steve on 6/6/17.
 */

public class SensorAveragerController {

    ExponentialMovingAverage gravx, gravy, gravz, accelx, accely, accelz;

    SAccel currentAccel;

    SGravity currentGrav;

    public SensorAveragerController(){
        super();

        gravx = new ExponentialMovingAverage(0.0);
        gravy = new ExponentialMovingAverage(0.0);
        gravz = new ExponentialMovingAverage(0.0);
        accelx = new ExponentialMovingAverage(0.0);
        accely = new ExponentialMovingAverage(0.0);
        accelz = new ExponentialMovingAverage(0.0);

        updateAccel(0.0, 0.0, 0.0);

        updateGravity(0.0, 0.0, 0.0);
    }

    public SensorAveragerController(Double dGravx, Double dGravy, Double dGravz,
                                    Double dAccelx, Double dAccely, Double dAccelz){
        super();
        gravx = new ExponentialMovingAverage(0.5);
        gravy = new ExponentialMovingAverage(0.5);
        gravz = new ExponentialMovingAverage(0.5);

        accelx = new ExponentialMovingAverage(0.5);
        accely = new ExponentialMovingAverage(0.5);
        accelz = new ExponentialMovingAverage(0.5);

        updateGravity(dGravx, dGravy, dGravz);
        updateAccel(dAccelx, dAccely, dAccelz);
    }


    public SGravity updateGravity(Double dGravx, Double dGravy, Double dGravz){
        Double dNextGravX = gravx.getNextAverage(dGravx);
        Double dNextGravY = gravy.getNextAverage(dGravy);
        Double dNextGravZ = gravz.getNextAverage(dGravz);

        currentGrav = new SGravity(dNextGravX,dNextGravY, dNextGravZ);
        return currentGrav;
    }

    public SAccel updateAccel(Double dAccelx, Double dAccely, Double dAccelz){

        Double dNextAccelX = accelx.getNextAverage(dAccelx);
        Double dNextAccelY = accely.getNextAverage(dAccely);
        Double dNextAccelZ = accelz.getNextAverage(dAccelz);

        currentAccel = new SAccel(dNextAccelX, dNextAccelY, dNextAccelZ);
        return currentAccel;
    }

    public SGravity updateAverageGravity(Double dGravx, Double dGravy, Double dGravz){
        updateGravity(dGravx, dGravy, dGravz);
        return getAverageGravity();
    }

    public SAccel updateAverageAccel(Double dGravx, Double dGravy, Double dGravz){
        updateAccel(dGravx, dGravy, dGravz);
        return getAverageAccel();
    }

    public SAccel getAverageAccel(){
        return currentAccel;
    }

    public SGravity getAverageGravity(){
        return currentGrav;
    }




}
