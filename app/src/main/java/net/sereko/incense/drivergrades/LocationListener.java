package net.sereko.incense.drivergrades;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by steve on 6/5/17.
 */

public class LocationListener implements android.location.LocationListener {
    private LocationManager lm;
    private DriverPresenter presenter;
    private LocationAveragerController averager;


    @SuppressWarnings({"MissingPermission"})
    public LocationListener(DriverPresenter presenter){
        super();
        this.presenter = presenter;
        lm = (LocationManager) presenter.getContext().getSystemService(Context.LOCATION_SERVICE);
        Location lastKnown = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        averager = new LocationAveragerController(new SLocation(lastKnown.getLongitude(), lastKnown.getLatitude(), lastKnown.getAltitude(), lastKnown.getSpeed()));
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30, Criteria.ACCURACY_FINE, this);
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onLocationChanged(Location location) {
        presenter.onLocationChanged(averager.updateAverage( new SLocation(location.getLongitude(), location.getLatitude(), location.getAltitude(), location.getSpeed())));
    }


    @Override
    public void onProviderDisabled(String provider) {
        // Required, not using (yet...)
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Required, not using (yet...)
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Required, not using
    }

    public void finish(){
        lm.removeUpdates(this);
    }

}
