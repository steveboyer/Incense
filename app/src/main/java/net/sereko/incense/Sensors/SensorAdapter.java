package net.sereko.incense.sensors;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;

import java.util.ArrayList;

/**
 * Created by steve on 1/25/17.
 */

public class SensorAdapter extends ArrayAdapter<SKSensor> {


    public SensorAdapter(Context context, ArrayList<SKSensor> sensors) {
        super(context, 0, sensors);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        SKSensor sensor = getItem(position);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sensor_layout, null);
        TextView name = (TextView)view.findViewById(R.id.sensor_name);
        TextView value = (TextView) view.findViewById(R.id.sensor_value);

        Log.d("Adapter", "name " + sensor.getName());
        Log.d("Adapter", "value " + sensor.getValue());
        name.setText(sensor.getName());
        value.setText(sensor.getValue());

        return view;
    }


}
