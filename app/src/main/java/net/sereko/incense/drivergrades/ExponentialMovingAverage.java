package net.sereko.incense.drivergrades;

/**
 * Created by steve on 6/9/17.
 */

public class ExponentialMovingAverage {

    Double alpha;
    Double currentAverage;

    public ExponentialMovingAverage(Double alpha){
        this.alpha = alpha;
    }

    public Double getNextAverage(Double newSensorValue){
        currentAverage = getNextAverage(alpha, newSensorValue, currentAverage);
        return getNextAverage(alpha, newSensorValue, currentAverage);
    }

    public static Double getNextAverage(Double alpha, Double newSensorValue, Double currentAverage){

        if(newSensorValue == null){
            return 0d;
        }

        if(currentAverage == null){
            return newSensorValue;
        }

        return currentAverage + alpha * (newSensorValue - currentAverage); // nextAverage

    }
}

