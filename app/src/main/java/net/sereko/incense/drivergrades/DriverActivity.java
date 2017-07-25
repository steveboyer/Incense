package net.sereko.incense.drivergrades;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.sereko.incense.R;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;


/**
 * Created by steve on 2/15/17.
 */

public class DriverActivity extends AbstractPermissionsActivity implements OnRequestPermissionsResultCallback, IView, SurfaceHolder.Callback {

    private final String TAG = DriverActivity.class.getSimpleName();
    private final int PERMISSION_GPS = 0;
    private final Handler mHandler = new Handler();
    DriverModel model;
    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfloc = new DecimalFormat("#.####");

//    @Bind(R.id.loading)
//    ProgressBar loadingView;
//
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

    @Bind(R.id.txtCurrentGrav)
    TextView txtCurrentGravity;

    @Bind(R.id.driver_layout)
    LinearLayout driverLayout;

    @Bind(R.id.txtGravMag)
    TextView txtGravMag;

    @Bind(R.id.txtGravAvg)
    TextView txtGravAvg;

    @Bind(R.id.txtGravMagAvg)
    TextView txtGravMagAvg;

    @Bind(R.id.txtAccelMag)
    TextView txtAccelMag;

    @Bind(R.id.txtAccelAvg)
    TextView txtAccelAvg;

    @Bind(R.id.txtAccelMagAvg)
    TextView txtAccelMagAvg;

//    @Bind(R.id.graph1)
//    GraphView graph1;

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

    private Random rand = new Random();
    private DriverPresenter presenter;

    private LocationManager lm;

    private static final String[] PERMS = {Manifest.permission.ACCESS_FINE_LOCATION};

    private long lastUpdated;

    private int UPDATE_RATE = 5; // Hz

    @Override
    protected void onPermissionDenied() {

    }

    @Override
    protected void onReady(Bundle state) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.driver_main);
        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        scheduler = new AppScheduler();
        service = new DriverService((SensorManager) this.getSystemService(SENSOR_SERVICE));

        presenter = new DriverPresenter(service, scheduler, this);

        presenter.setView(this);
        presenter.start();
        df.setRoundingMode(RoundingMode.CEILING);

        GMeter gmeter = new GMeter(getApplicationContext());
        driverLayout.addView(gmeter);

        final GMeter gMeter = (GMeter) findViewById(R.id.meter);


        df.setMinimumIntegerDigits(1);
        df.setMinimumFractionDigits(2);

        dfloc.setMinimumFractionDigits(4);
        dfloc.setMinimumIntegerDigits(3);

        df.setNegativePrefix("");
        lastUpdated = System.currentTimeMillis();
//        series1 = new LineGraphSeries<>();

        //MeterView meterView = (MeterView) findViewById(R.id.surfaceView);

//        series2 = new LineGraphSeries<>();
//        series3 = new LineGraphSeries<>();
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMaxX(100);
//        graph.getViewport().setMinX(0);

//        graph1.addSeries(series1);
//        graph2.addSeries(series2);
//        graph3.addSeries(series3);

        dataPoint = 0;
    }

    public void updateLocationText(Double latitude, Double longitude, Double speed, Double altitude){
        txtCurrentLatitude.setText(dfloc.format(latitude));
        txtCurrentLongitude.setText(dfloc.format(longitude));
        txtCurrentSpeed.setText(df.format(speed * 2.23694));
        txtCurrentAltitude.setText(df.format(altitude * 3.28084));
    }

    public void updateAccelText(Vector3D accel){
        String newText = df.format(accel.getX()) + " " + df.format(accel.getY()) + " " + df.format(accel.getZ());
        txtCurrentAccel.setText(newText);
        txtAccelMag.setText(df.format(accel.getMagnitude()) + " at " + df.format(accel.getAngleH() * 180 / Math.PI) + "º H and " + df.format(accel.getAngleV() * 180 / Math.PI) + "º V");

    }

    public void updateGravText(Vector3D grav){
        String newText = " " + df.format(grav.getX()) + " " + df.format(grav.getY()) + " " + df.format(grav.getZ());
        txtCurrentGravity.setText(newText);
        txtGravMag.setText(df.format(grav.getMagnitude()) + " at " + df.format(grav.getAngleH() * 180 / Math.PI) + "º H and " + df.format(grav.getAngleV() * 180 / Math.PI) + "º V");
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public static void saveLogcatToFile(Context context) {
        String fileName = "logcat_"+System.currentTimeMillis()+".txt";
        File outputFile = new File(context.getExternalCacheDir(),fileName);
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec("logcat -f "+outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause(){
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    @Override
    public void onStop(){
        saveLogcatToFile(getApplicationContext());
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
    public void setModel(Object model) {
        this.model = (DriverModel)model;


        if((System.currentTimeMillis() - lastUpdated) > 1.0/UPDATE_RATE){
            updateText(this.model);
            lastUpdated = System.currentTimeMillis();
        }
    }

    public void updateText(DriverModel model) {
        updateLocationText(model.getLatitude(),model.getLongitude(), model.getSpeed(), model.getAltitude());
        updateAccelText(model.getAccel());
        updateGravText(model.getGrav());
    }

    @Override
    public void onResume(){
        super.onResume();

        mTimer = new Runnable() {

            @Override
            public void run() {
                graphLastXValue += 1d;

//                Double dx = model.get("accelx").isEmpty() ? 0d : Double.parseDouble(model.get("accelx"));
//                Double dy = model.get("accely").isEmpty() ? 0d : Double.parseDouble(model.get("accely"));
//                Double dz = model.get("accelz").isEmpty() ? 0d : Double.parseDouble(model.get("accelz"));
//                series1.appendData(new DataPoint(graphLastXValue, dx), false, 40);
//                series2.appendData(new DataPoint(graphLastXValue, dy), false, 40);
//                series3.appendData(new DataPoint(graphLastXValue, dz), false, 40);
                mHandler.postDelayed(this, 3000);
            }
        };
        mHandler.postDelayed(mTimer, 100);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
