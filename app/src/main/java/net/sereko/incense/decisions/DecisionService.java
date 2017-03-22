package net.sereko.incense.decisions;

import net.sereko.incense.model.Decision;
import net.sereko.incense.view.IView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by steve on 2/27/17.
 */

public class DecisionService {

    private static final String TAG = DecisionService.class.getSimpleName();

    private IView<Decision> view;


    public DecisionService(IView<Decision> view) {
        super();
        this.view = view;
    }

//    public Observable<Decision> getDecisions() {
//        return Observable.create(new Observable.OnSubscribe<Decision>() {
//            @Override
//            public void call(Subscriber<? super Decision> subscriber) {
//
//            }
//
//            @Override
//            public void call(Subscriber<Decision> subscriber){
//                //Simulate some latency
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    subscriber.onError(e);
////                }
//                Log.wtf(TAG, "making sensors");
//
//                subscriber.onCompleted();
//            }
//        });
//    }


    public Observable<List<Decision>> updateDecision(){
        return Observable.create(new Observable.OnSubscribe<List<Decision>>(){
            @Override
            public void call(Subscriber<? super List<Decision>> subscriber){


            }
        });
    }


    public void start(){

    }

    public void finish() {

    }

}
