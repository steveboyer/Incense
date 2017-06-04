package net.sereko.incense.drivergrades;

import net.sereko.incense.model.SKSensor;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by steve on 1/25/17.
 */

public class DriverPresenter implements IPresenter<SKSensor> {

    private final String TAG = DriverPresenter.class.getSimpleName();
    private Subscription subscription = Subscriptions.empty();
    private IScheduler scheduler;
    private IView<SKSensor> view;


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
//    private Observable<List<SKSensor>> getObservable(){
//        return driverService.getSKSensors()
//                .subscribeOn(scheduler.backgroundThread())
//                .observeOn(scheduler.mainThread());
//    }


}
