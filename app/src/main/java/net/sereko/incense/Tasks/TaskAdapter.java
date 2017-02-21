package net.sereko.incense.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;

import java.util.ArrayList;

/**
 * Created by steve on 2/15/17.
 */

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Task> tasks;

    private LayoutInflater inflater;
    private TaskPresenter presenter;

    public TaskAdapter(Context context, ArrayList<Task> tasks, TaskPresenter presenter) {
        this.context = context;
        this.tasks = tasks;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.presenter = presenter;
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

        ClickListener listener = new ClickListener(i);

        doAction.setOnClickListener(listener);

        Task t = getItem(i);
        text.setText(t.getName());
        return vi;
    }

}
