package net.sereko.incense.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;

import java.util.ArrayList;

/**
 * Created by steve on 2/15/17.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_row, parent, false);
        }

        Button doAction = (Button) convertView.findViewById(R.id.didit_button);
        doAction.setText("Btn " + position);
        doAction.setTag(position);
        doAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Task t = getItem(position);
            }
        });

        return convertView;
    }

}
