package net.sereko.incense.drivergrades;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;

import java.util.ArrayList;

/**
 * Created by steve on 1/25/17.
 */

public class DriverAdapter extends ArrayAdapter<SKSensor> {

    //@Bind(R.layout.sensor_view)
//    LinearLayout sensorView;
//
//    //@Bind(R.id.sensor_name)
//    TextView sensorName;
//
//    //@Bind(R.id.x_value)
//    TextView xValue;
//
//    //@Bind(R.id.y_value)
//    TextView yValue;
//
////    @Bind(R.id.z_value)
//    TextView zValue;

    public DriverAdapter(Context context, ArrayList<SKSensor> sensors) {
        super(context, 0, sensors);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        SKSensor sensor = getItem(position);
        if(sensor == null ) return null;

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.sensor_view, null);
        }

        //Log.d("Adapter", "name " + sensor.getName());


        return convertView;
    }


}
