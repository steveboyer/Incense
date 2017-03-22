package net.sereko.incense.decisions;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.sereko.incense.R;
import net.sereko.incense.model.Decision;

import java.util.ArrayList;

/**
 * Created by steve on 1/25/17.
 */

public class DecisionAdapter extends ArrayAdapter<Decision> {

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

    public DecisionAdapter(Context context, ArrayList<Decision> decisions) {
        super(context, 0, decisions);
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
        Decision d = getItem(position);
        if(d == null ) return null;

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.sensor_view, null);
        }

        //Log.d("Adapter", "name " + sensor.getName());

        TextView xValue = (TextView) convertView.findViewById(R.id.x_value);
        TextView yValue = (TextView) convertView.findViewById(R.id.y_value);
        TextView zValue = (TextView) convertView.findViewById(R.id.z_value);
        TextView sensorName = (TextView) convertView.findViewById(R.id.sensor_name);

//        sensorName.setText(sensor.getName());
//
//        xValue.setText(Float.toString(sensor.getX_val()));
//        yValue.setText(Float.toString(sensor.getY_val()));
//        zValue.setText(Float.toString(sensor.getZ_val()));

        return convertView;
    }


}
