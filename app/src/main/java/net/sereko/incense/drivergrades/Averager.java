package net.sereko.incense.drivergrades;

import net.sereko.incense.decisions.ExponentialMovingAverage;

import java.util.Deque;


/**
 * Created by steve on 6/6/17.
 */

public class Averager implements Runnable {
    private final Integer BUFFER_MAX = 10;
    private volatile Deque<Double> numbers;
    private String TAG = Averager.class.getName();
    private volatile Double currentSensor = 0d;
    private volatile Double currentAverage;

    private boolean running = true;
    private Integer maxBuffer;
    private final double DECAY_RATE = 0.5;

    private ExponentialMovingAverage exponentialMovingAverage;
//    private Boolean

    public Averager(Integer maxBuffer){
        this.maxBuffer = maxBuffer;

        //this.numbers = new ArrayDeque<>();
       // this.exponentialMovingAverage = new ExponentialMovingAverage();
    }


    public Averager(){
        this.maxBuffer = BUFFER_MAX;
        this.currentAverage = 0.0;
        this.currentSensor = 0.0;

        //this.numbers = new ArrayDeque<>();
    }

    public void addValue(Double d){
//        if(!numbers.isEmpty() && numbers.size() >= maxBuffer){
//            numbers.removeFirst();
//        }
//        numbers.addLast(d);
        currentSensor = d;
    }

    public Double getAverage(){
        return this.currentAverage;
    }

    public boolean isRunning(){
        return this.running;
    }

    public void setRunning(boolean running){
        this.running = running;

        if(!running) {
            run();
        }
    }

    @Override
    public void run() {
        if(running){

            // Shouldn't be able to get here @TODO
            //if(numbers.isEmpty()) throw new RuntimeException();


//            Double sum = 0.0;
//            Integer size = numbers.size();
//            double increment = 1d/numbers.size(); // Should not get here with a 0

//            Log.d(TAG, "Increment: " + increment);

//            for (int i = 0; i < numbers.size(); i++){
//                sum += (i+1)*increment*numbers.getFirst(); // Largest multiplier to newest value
//            }

//            for(Double d : numbers){
//                sum += d;
//            }
//
//            synchronized (this) { // Should not be necessary @TODO
//                average = sum / size;
//            }

            synchronized (this){
                Double decayRate = 0.1;
                currentAverage = ExponentialMovingAverage.getNextAverage(decayRate, currentSensor, currentAverage);
            }

        }
    }


}
