package net.sereko.incense.drivergrades;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import net.sereko.incense.R;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.Formatter;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class DriverActivity extends AbstractPermissionsActivity implements LocationListener, OnRequestPermissionsResultCallback, IView {

    private final String TAG = DriverActivity.class.getSimpleName();
    private final int PERMISSION_GPS = 0;
//    @Bind(R.id.loading)
//    ProgressBar loadingView;

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//
//    @Bind(R.id.fab)
//    FloatingActionButton floatingActionButton;

    @Bind(R.id.txtCurrentSpeed)
    TextView txtCurrentSpeed;

    @Bind(R.id.txtCurrentLatitude)
    TextView txtCurrentLatitude;

    @Bind(R.id.txtCurrentLongitude)
    TextView txtCurrentLongitude;

    @Bind(R.id.txtCurrentAltitude)
    TextView txtCurrentAltitude;

    @Inject
    DriverService service;

    @Inject
    IScheduler scheduler;

    private DriverPresenter presenter;
    public DriverAdapter adapter;

    // @TODO
    // Loading, floating button

    @Override
    protected String[] getDesiredPermissions() {
        return new String[0];
    }

    @Override
    protected void onPermissionDenied() {

    }

    @Override
    protected void onReady(Bundle state) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG, TAG);
        setContentView(R.layout.driver_main);
        ButterKnife.bind(this);
        // setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        scheduler = new AppScheduler();
        service = new DriverService((SensorManager) this.getSystemService(SENSOR_SERVICE));

        presenter = new DriverPresenter(service, scheduler, this);

        //presenter.setView(this);
        presenter.start();

//
//        if(savedInstanceState != null){
//            Boolean isInPermission = savedInstanceState.getBoolean(STATE_IN_PERMISSION, false);
//        }

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Criteria for picking provider (e.g. GPS)
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        c.setAltitudeRequired(true);

        String lmName = locationManager.getBestProvider(c, false);// return disabled ones too

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        Log.w(TAG, "Checking permissions...");// @TODO Add more permissions code like implementing methods from AbstractPerms
        // Check/request the permisson
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            test(locationManager, lmName);
        } else {
            Log.w(TAG, "Deciding...");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
                ActivityCompat.requestPermissions(this, perms, PERMISSION_GPS);
                // Async request!!
                Log.w(TAG, "Requesting permission...");


                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            } else { // nope
                Log.w(TAG, "Also requesting here.");
                ActivityCompat.requestPermissions(this, perms, PERMISSION_GPS);
                test(locationManager, lmName);
            }
        }
    }

    @SuppressWarnings({"MissingPermission"})
    public void test(LocationManager locationManager, String lmName){
        // Test
        Log.w(TAG, "Getting altitute");
        Location l = locationManager.getLastKnownLocation(lmName);
        Log.w(TAG, String.valueOf(l.getAltitude() * 3.2808));
        txtCurrentLatitude.setText(String.valueOf(l.getLatitude()));
        txtCurrentLongitude.setText(String.valueOf(l.getLongitude()));
        txtCurrentSpeed.setText(String.valueOf(l.getSpeed()));
        txtCurrentAltitude.setText(String.valueOf(l.getAltitude() * 3.2808));
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.updateSpeed(null);
    }

    public void finish()
    {
        super.finish();
        System.exit(0);
    }

    private void updateSpeed(CLocation location) {
        float nCurrentSpeed = 0;

        if(location != null)
        {
            location.setUseMetricunits(this.useMetricUnits());
            nCurrentSpeed = location.getSpeed();
        }

        Formatter fmt = new Formatter(new StringBuilder());
        fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed = strCurrentSpeed.replace(' ', '0');

        String strUnits = "miles/hour";
        if(this.useMetricUnits())
        {
            strUnits = "meters/second";
        }

//        TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
//        txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnits);
    }

    private boolean useMetricUnits() {
        // TODO Auto-generated method stub
//        CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
//        return chkUseMetricUnits.isChecked();
        return false;
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onLocationChanged(Location location) {
        {
            CLocation myLocation = new CLocation(location, this.useMetricUnits());
            this.updateSpeed(myLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "Disabled");
        Log.d(TAG, provider);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "Enabled");
        Log.d(TAG, provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoading(boolean isLoading) {

    }

    @Override
    public void error(Throwable t) {

    }

    public Activity getActivity(){
        return this;
    }
}
