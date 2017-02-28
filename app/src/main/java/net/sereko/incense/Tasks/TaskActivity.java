package net.sereko.incense.tasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import net.sereko.incense.R;
import net.sereko.incense.model.Task;
import net.sereko.incense.service.TaskService;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

public class TaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IView<List<Task>, Task> {
    private static final String TAG = TaskActivity.class.getSimpleName();

    @Bind(R.id.listview)
    ListView listview;

    @Bind(R.id.loading)
    ProgressBar loadingView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Inject TaskService service;
    @Inject IScheduler scheduler;

    private TaskPresenter presenter;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        scheduler = new AppScheduler();
        service = new TaskService();

        presenter = new TaskPresenter(service, scheduler, this);
        presenter.setView(this);

        floatingActionButton.setOnClickListener(presenter);

        adapter = new TaskAdapter(this, new ArrayList<Task>());
        listview.setAdapter(adapter);

        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //getWindow().setEnterTransition(new Explode());

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
        presenter.finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void setLoading(boolean isLoading) {
        loadingView.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
    }

    @Override
    public void setModel(List<Task> tasks) {
        //adapter.clear();
        adapter.addAll(tasks);
    }

    public void addItem(Task task){
        adapter.addAll(task);
    }


    @Override
    public void error(Throwable t) {

    }
}
