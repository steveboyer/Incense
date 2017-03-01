package net.sereko.incense.sensors;

import net.sereko.incense.model.SKSensor;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/27/17.
 */

public class SensorService {
    private static final String TAG = SensorService.class.getSimpleName();

    public SensorService() {
        super();
    }

    public Observable<List<SKSensor>> getTasks() {
        return Observable.create(new Observable.OnSubscribe<List<SKSensor>>() {
            @Override
            public void call(Subscriber<? super List<SKSensor>> subscriber){
                //Simulate some latency
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }

                List<SKSensor> sensors = new ArrayList<>();
                sensors.add(new SKSensor(""));
                // @TODO
                // Load sensors from prefs/db



                subscriber.onNext(sensors);
                subscriber.onCompleted();
            }
        });
    }

}
