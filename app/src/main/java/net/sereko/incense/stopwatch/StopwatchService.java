package net.sereko.incense.stopwatch;

import net.sereko.incense.model.SKSensor;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/27/17.
 */

public class StopwatchService {

    private static final String TAG = StopwatchService.class.getSimpleName();

    public StopwatchService() {
        super();
    }

    public Observable<List<SKSensor>> getSKSensors() {
        return Observable.create(new Observable.OnSubscribe<List<SKSensor>>() {
            @Override
            public void call(Subscriber<? super List<SKSensor>> subscriber){
                //Simulate some latency
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    subscriber.onError(e);
//                }


                //subscriber.onNext(skSensors);


                //subscriber.onCompleted();
            }
        });
    }




    public void start(){

    }

    public void finish() {
    }

}
