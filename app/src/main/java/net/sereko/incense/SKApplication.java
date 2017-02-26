package net.sereko.incense;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by steve on 2/26/17.
 */

public class SKApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

}
