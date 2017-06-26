package net.sereko.incense.drivergrades;

import android.Manifest;
import android.app.Activity;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.sereko.incense.R;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;


/**
 * Created by steve on 2/15/17.
 */

public class DriverActivity extends AbstractPermissionsActivity implements OnRequestPermissionsResultCallback, IView {

    private final String TAG = DriverActivity.class.getSimpleName();
    private final int PERMISSION_GPS = 0;
    private final Handler mHandler = new Handler();
    HashMap<String, String> model;
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

    @Bind(R.id.txtCurrentAccel)
    TextView txtCurrentAccel;

    @Bind(R.id.txtCurrentGravity)
    TextView txtCurrentGravity;

    @Bind(R.id.graph1)
    GraphView graph1;

//    @Bind(R.id.graph2)
//    GraphView graph2;
//
//    @Bind(R.id.graph3)
//    GraphView graph3;

    LineGraphSeries<DataPoint> series1, series2, series3;

    @Inject
    DriverService service;

    @Inject
    IScheduler scheduler;

    private Runnable mTimer;
    private double graphLastXValue = 0d;

    int dataPoint;

    private DriverPresenter presenter;

    private LocationManager lm;

    private static final String[] PERMS = {Manifest.permission.ACCESS_FINE_LOCATION};

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

        presenter.setView(this);
        presenter.start();

        series1 = new LineGraphSeries<>();
//        series2 = new LineGraphSeries<>();
//        series3 = new LineGraphSeries<>();
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMaxX(100);
//        graph.getViewport().setMinX(0);

        graph1.addSeries(series1);
//        graph2.addSeries(series2);
//        graph3.addSeries(series3);


        dataPoint = 0;


    }

    public void updateLocationText(String latitude, String longitude, String speed, String altitude){
        txtCurrentLatitude.setText(latitude);
        txtCurrentLongitude.setText(longitude);
        txtCurrentSpeed.setText(speed);
        txtCurrentAltitude.setText(altitude);
    }

    public void updateAccelText(String x, String y, String z){
        String newText = "(" + x + "," + y + "," + z + ")";
        txtCurrentAccel.setText(newText);
    }

    public void updateGravText(String x, String y, String z){
        String newText = "(" + x + "," + y + "," + z + ")";
        txtCurrentGravity.setText(newText);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onPause(){
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }


    @Override
    public void onStop(){
        super.onStop();
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
    protected String[] getDesiredPermissions(){
        return(PERMS);
    }

    @Override
    public void error(Throwable t) {

    }

    public Activity getActivity(){
        return this;
    }

    @Override
    public void setModel(HashMap<String, String> model) {
        this.model = model;
        updateText(model);
    }

    public void updateText(HashMap<String, String> model) {
        updateLocationText(model.get("lat"), model.get("long"), model.get("speed"), model.get("alt"));
        updateAccelText(model.get("accelx"), model.get("accely"), model.get("accelz"));
        updateGravText(model.get("gravx"), model.get("gravy"), model.get("gravz"));
    }

    @Override
    public void onResume(){
        super.onResume();

        mTimer = new Runnable() {

            @Override
            public void run() {
                graphLastXValue += 1d;

                Double dx = model.get("accelx").isEmpty() ? 0d : Double.parseDouble(model.get("accelx"));
                Double dy = model.get("accely").isEmpty() ? 0d : Double.parseDouble(model.get("accely"));
                Double dz = model.get("accelz").isEmpty() ? 0d : Double.parseDouble(model.get("accelz"));
                series1.appendData(new DataPoint(graphLastXValue, dx), false, 40);
//                series2.appendData(new DataPoint(graphLastXValue, dy), false, 40);
//                series3.appendData(new DataPoint(graphLastXValue, dz), false, 40);
                mHandler.postDelayed(this, 3000);
            }
        };
        mHandler.postDelayed(mTimer, 100);
    }



}
