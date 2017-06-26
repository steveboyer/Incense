package net.sereko.incense.drivergrades;


import net.sereko.incense.decisions.ExponentialMovingAverage;

import org.junit.Test;

/**
 * Created by steve on 6/8/17.
 */

public class AveragerTest {




    @Test
    public void testLimited(){
        ExponentialMovingAverage averager = new ExponentialMovingAverage(0.5);
        double i = 1d;


        for(int j = 0; j < 100; j++) {
            Double current = averager.getNextAverage(i);
            System.out.println(current);
            i++;
        }




    }

}
