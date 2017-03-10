package net.sereko.incense.sensors;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class SensorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View<List<SKSensor>, SKSensor> {

    private final String TAG = SensorActivity.class.getSimpleName();

    @Bind(R.id.loading)
    ProgressBar loadingView;

    @Bind(R.id.listview)
    ListView listView;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;


    @Inject
    SensorService service;

    @Inject
    IScheduler scheduler;

    private SensorPresenter presenter;
    public SensorAdapter adapter;

    // @TODO
    // Loading, floating button

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tasks_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ArrayList<SKSensor> sensors = new ArrayList<>();

        adapter = new SensorAdapter(this, sensors);
        listView.setAdapter(adapter);
        scheduler = new AppScheduler();
        service = new SensorService(this, (SensorManager)this.getSystemService(SENSOR_SERVICE));

        presenter = new SensorPresenter(service, scheduler, this);
        floatingActionButton.setOnClickListener(presenter);

        //presenter.setView(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void setLoading(boolean isLoading) {
        loadingView.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
    }

    @Override
    public void setModel(List<SKSensor> object) {
        adapter.clear();
        adapter.addAll(object);
    }

    @Override
    public SKSensor getItem(int position ){
        return adapter.getItem(position);
    }

    @Override
    public int getItemCount() {
        return adapter.getCount();
    }

    @Override
    public void addItem(SKSensor object) {
        adapter.addAll(object);
    }

    @Override
    public void insert(SKSensor sensor, int i){
        adapter.insert(sensor, i);
    }

    @Override
    public void error(Throwable t) {

    }

    public SensorAdapter getAdapter(){
        return adapter;
    }


    public Activity getActivity(){
        return this;
    }
}
