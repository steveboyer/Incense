package net.sereko.incense.drivergrades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

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
    DriverModel model;

    public DriverPresenter(DriverService service, IScheduler scheduler, IView view){
        super();
        this.service = service;
        this.scheduler = scheduler;
        this.view = view;
        this.context = view.getActivity().getApplicationContext();
        this.sensorManager = (SensorManager)view.getActivity().getSystemService(SENSOR_SERVICE);
        this.model = new DriverModel();

        view.setModel(model);
    }

    @Override
    public void start() {
        locationListener = new LocationListener(this);

        Sensor grav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SensorListener listener = new SensorListener(this);

        sensorManager.registerListener(listener, grav, SensorManager.SENSOR_DELAY_NORMAL);
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
        model.setLatitude(location.latitude);
        model.setLongitude(location.longitude);
        model.setSpeed(location.speed);
        model.setAltitude(location.altitude);

        view.setModel(model);
    }

    public void onGravityChanged(Vector3D gravity){
        model.setGrav(gravity);

        view.setModel(model);
    }

    public void onAccelChanged(Vector3D accel){
        model.setAccel(accel);

        view.setModel(model);
    }

}
