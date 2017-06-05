package net.sereko.incense.stopwatch;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TimePicker;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;
import net.sereko.incense.sensors.SensorAdapter;
import net.sereko.incense.sensors.SensorPresenter;
import net.sereko.incense.sensors.SensorService;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IAdapterView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class StopwatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IAdapterView<SKSensor> {

    private final String TAG = StopwatchActivity.class.getSimpleName();

    @Bind(R.id.watch1)
    TimePicker watch;
//    Toolbar toolbar;

//    @Bind(R.id.fab)
//    FloatingActionButton floatingActionButton;

    @Inject
    SensorService service;

    @Inject
    IScheduler IScheduler;

    private SensorPresenter presenter;
    public SensorAdapter adapter;

    // @TODO
    // Loading, floating button

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stopwatch_view);
        ButterKnife.bind(this);
        //setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ArrayList<SKSensor> sensors = new ArrayList<>();

        adapter = new SensorAdapter(this, sensors);
        //listView.setAdapter(adapter);
        IScheduler = new AppScheduler();
        service = new SensorService(this, (SensorManager)this.getSystemService(SENSOR_SERVICE));

        presenter = new SensorPresenter(service, IScheduler, this);
        //floatingActionButton.setOnClickListener(presenter);

        watch.setIs24HourView(true);

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
        //loadingView.setVisibility(isLoading ? android.view.IView.VISIBLE : android.view.IView.GONE);
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
