package net.sereko.incense.drivergrades;

/**
 * Created by steve on 6/6/17.
 */

public class LocationAveragerController {
    private ExponentialMovingAverage latitude, longitude, speed, altitude;

    String TAG = LocationAveragerController.class.getSimpleName();

    SLocation current;

    public LocationAveragerController(SLocation firstlocation){
        super();

        this.latitude = new ExponentialMovingAverage(0.5);
        this.longitude = new ExponentialMovingAverage(0.5);
        this.speed = new ExponentialMovingAverage(0.5);
        this.altitude = new ExponentialMovingAverage(0.5);

        updateAverage(firstlocation);
    }

    public SLocation updateAverage(SLocation location){

        if(location == null){
            return new SLocation(0, 0, 0, 0);
        }
        Double dLongitude = longitude.getNextAverage(location.longitude);
        Double dLatitude = latitude.getNextAverage(location.latitude);
        Double dSpeed = speed.getNextAverage(location.speed);
        Double dAltitude = altitude.getNextAverage(location.altitude);
        //Log.d(TAG, "long: " + dLongitude + " lat: " + dLatitude + " speed: " + dSpeed + " alt: " + + dAltitude );
        current =  new SLocation(dLongitude, dLatitude, dAltitude, dSpeed);

        return current;
    }

    public SLocation getAverage(){
        return current;
    }
}
