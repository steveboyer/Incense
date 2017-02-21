package net.sereko.incense;

import android.os.AsyncTask;

import net.sereko.incense.model.Task;
import net.sereko.incense.tasks.TaskActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by steve on 2/21/17.
 */

public class CreateTaskInstanceTask extends AsyncTask<Task, Calendar, Integer> {

    TaskActivity activity;



    @Override
    protected Integer doInBackground(Task... tasks) {
        int size = tasks.length;

        ArrayList<Task> tasksList = new ArrayList<>();

        for(Task task : tasks){

        }


        return null;
    }


    protected void onProgressUpdate(Integer... progress) {
        activity.setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
        activity.showDialog("Downloaded " + result + " bytes");
    }

    public CreateTaskInstanceTask(TaskActivity activity){
        this.activity = activity;
    }
}
