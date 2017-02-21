package net.sereko.incense.tasks;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.sereko.incense.R;
import net.sereko.incense.SKDialog;
import net.sereko.incense.model.Task;
import net.sereko.incense.service.TaskService;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

public class TaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IView<List<Task>> {
    private static final String TAG = TaskActivity.class.getSimpleName();


    @Bind(R.id.listview)
    ListView listview;

    @Inject TaskService service;
    @Inject IScheduler scheduler;


    private TaskPresenter presenter;
    private ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scheduler = new AppScheduler();
        service = new TaskService();

        presenter = new TaskPresenter(service, scheduler);
        presenter.setView(this);
        //tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.task_row); //new TaskAdapter(this, tasks, presenter);
        ButterKnife.bind(this);

        listview.setAdapter(adapter);

        presenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public void onItemsNext(){

    }

    public void onItemsError(Throwable throwable){

    }

    public void showDialog(String message){

    }

    public void setProgressPercent(int progress){

    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    public void openDialog(){
        DialogFragment dialog = new SKDialog();
        dialog.show(getSupportFragmentManager(), "SKDialog");
    }

    @Override
    public void setLoading(boolean isLoading) {

    }

    @Override
    public void setModel(List<Task> object) {

    }

    @Override
    public void error(Throwable t) {

    }
}
