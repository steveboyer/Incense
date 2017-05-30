package net.sereko.incense.drivergrades;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class DriverActivity extends AppCompatActivity implements IBaseGPSListener {

    private final String TAG = DriverActivity.class.getSimpleName();

//    @Bind(R.id.loading)
//    ProgressBar loadingView;
//
//    @Bind(R.id.listview)
//    ListView listView;


//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//
//    @Bind(R.id.fab)
//    FloatingActionButton floatingActionButton;

    @Inject
    DriverService service;

    @Inject
    IScheduler scheduler;

    private DriverPresenter presenter;
    public DriverAdapter adapter;

    // @TODO
    // Loading, floating button

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.driver_main);
        ButterKnife.bind(this);
        // setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ArrayList<SKSensor> sensors = new ArrayList<>();

        //adapter = new DriverAdapter(this, sensors);
        //listView.setAdapter(adapter);
        scheduler = new AppScheduler();
        service = new DriverService((SensorManager) this.getSystemService(SENSOR_SERVICE));

        presenter = new DriverPresenter(service, scheduler);
        //floatingActionButton.setOnClickListener(presenter);

        //presenter.setView(this);
        presenter.start();


        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (SecurityException ex){ // Permission not given

        }
        this.updateSpeed(null);

//        CheckBox chkUseMetricUntis = (CheckBox) this.findViewById(R.id.chkMetricUnits);
//        chkUseMetricUntis.setOnCheckedChangeListener(new OnCheckedChangeListener() {

//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // TODO Auto-generated method stub
//                DriverActivity.this.updateSpeed(null);
//            }
        //});
    }

    public void finish()
    {
        super.finish();
        System.exit(0);
    }

    private void updateSpeed(CLocation location) {
        // TODO Auto-generated method stub
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

        TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
        txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnits);
    }

    private boolean useMetricUnits() {
        // TODO Auto-generated method stub
//        CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
//        return chkUseMetricUnits.isChecked();
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        if(location != null)
        {
            CLocation myLocation = new CLocation(location, this.useMetricUnits());
            this.updateSpeed(myLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onGpsStatusChanged(int event) {
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

    public Activity getActivity(){
        return this;
    }
}
