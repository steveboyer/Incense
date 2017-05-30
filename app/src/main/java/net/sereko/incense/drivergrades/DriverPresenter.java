package net.sereko.incense.drivergrades;

import android.util.Log;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class DriverPresenter implements IPresenter<SKSensor> {

    private final String TAG = DriverPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private DriverService driverService;
    //private Sensor sensor;
//    private SKSensor skSensor;
//    private SensorManager sensorManager;
    private IScheduler scheduler;
    private IView<SKSensor> view;
//    private DecisionAdapter adapter;
//    public ArrayList<SKSensor> skSensors;


    //private StopwatchActivity activity;

    public DriverPresenter(DriverService service, IScheduler SScheduler){
        super();
        this.driverService = service;
        this.scheduler = SScheduler;
        //this.activity = activity;

//        this.sensorManager = (SensorManager) view.getActivity().getSystemService(Context.SENSOR_SERVICE);

        Log.w(TAG, TAG);
        //this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Log.w(TAG, sensor.getName());

        //skSensor = new SKSensor("Accelerometer", sensor);
        //IView.addItem(skSensor);
//
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                //skSensor.start();
//            }
//        });
//        t.start();

    }

    // Load SKSensors
//    private Subscriber<List<SKSensor>> getSubscriber(){
//        return new Subscriber<List<SKSensor>>() {
//            @Override
//            public void onStart(){
//                super.onStart();
//                view.setLoading(true);
//            }
//
//            @Override
//            public void onCompleted() {
//                view.setLoading(false);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                view.setLoading(false);
//                view.error(e);
//            }
//
//            @Override
//            public void onNext(List<SKSensor> skSensors) {
//                view.setModel(skSensors);
//            }
//        };
//    }
//
//
//
//    @Override
//    public void onClick(android.view.View view) {
//
//    }
//
//    @Override
//    public void start() {
//        subscription = getObservable().subscribe(getSubscriber());
////        skSensor.start();
//    }

//    @Override
//    public void finish() {
//        subscription.unsubscribe();
//        this.view = null;
//        //skSensor.finish();
//    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }

    @Override
    public void setView(IView<SKSensor> iView) {

    }

    /*
     * Connect to service
     * @return An observer that waits on StopwatchService sensors
     */
    private Observable<List<SKSensor>> getObservable(){
        return driverService.getSKSensors()
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread());
    }


}
