package net.sereko.incense.drivergrades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.HashMap;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by steve on 1/25/17.
 */

public class DriverPresenter implements IPresenter {

    private final String TAG = DriverPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private IScheduler scheduler;
    private IView view;
    private DriverService service;
    private Context context;
    private LocationListener locationListener;
    public SensorManager sensorManager;
    HashMap<String, String> model;



    public DriverPresenter(DriverService service, IScheduler scheduler, IView view){
        super();
        this.service = service;
        this.scheduler = scheduler;
        this.view = view;
        this.context = view.getActivity().getApplicationContext();
        this.sensorManager = (SensorManager)view.getActivity().getSystemService(SENSOR_SERVICE);
        this.model = new HashMap<>();

        model.put("lat", "");
        model.put("long", "");
        model.put("alt", "");
        model.put("speed", "");
        model.put("gravx", "");
        model.put("gravy", "");
        model.put("gravz", "");
        model.put("accelx", "");
        model.put("accely", "");
        model.put("accelz", "");
        view.setModel(model);
    }

    @Override
    public void start() {
        locationListener = new LocationListener(this);

        Sensor gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SensorListener listener = new SensorListener(this);

        sensorManager.registerListener(listener, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, accel, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void finish() {
        locationListener.finish();
    }

    @Override
    public void setView(IView iView) {
        this.view = iView;
    }

    public Context getContext(){
        return this.context;
    }

    public void onLocationChanged(SLocation location){
        model.put("lat", String.valueOf(location.latitude));
        model.put("long", String.valueOf(location.longitude));
        model.put("alt", String.valueOf(location.altitude));
        model.put("speed", String.valueOf(location.speed));
    }

    public void onGravityChanged(SGravity sGravity){
        model.put("gravx", String.valueOf(sGravity.gravx));
        model.put("gravy", String.valueOf(sGravity.gravy));
        model.put("gravz", String.valueOf(sGravity.gravz));
    }

    public void onAccelChanged(SAccel sAccel){
        model.put("accelx", String.valueOf(sAccel.accelx));
        model.put("accely", String.valueOf(sAccel.accely));
        model.put("accelz", String.valueOf(sAccel.accelz));
    }

}
