package net.sereko.incense.Tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import net.sereko.incense.R;

import java.util.ArrayList;

/**
 * Created by steve on 2/15/17.
 */

public class TaskAdapter extends BaseAdapter {

    Context context;
    ArrayList<Task> tasks;

    LayoutInflater inflater;
    View.OnClickListener listener;

    public TaskAdapter(Context context, ArrayList<Task> tasks, View.OnClickListener listener) {
        this.context = context;
        this.tasks = tasks;
        this.listener = listener;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (vi == null) {
            vi = inflater.inflate(R.layout.task_row, null);
        }

        TextView text = (TextView) vi.findViewById(R.id.text);

        Button doAction = (Button) vi.findViewById(R.id.didit_button);

        doAction.setOnClickListener(listener);

        Task t = getItem(i);
        text.setText(t.getName());
        return vi;
    }

}
