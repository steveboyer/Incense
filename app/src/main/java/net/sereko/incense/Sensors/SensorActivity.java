package net.sereko.incense.sensors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.sereko.incense.R;
import net.sereko.incense.model.SKSensor;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class SensorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IView<List<SKSensor>, SKSensor> {
    @Bind(R.id.listview)
    ListView listView;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    SensorService service;

    @Inject
    IScheduler scheduler;
    // @TODO
    // Loading, floating button

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tasks_main);
        ButterKnife.bind(this);


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


    private final String TAG = SensorActivity.class.getSimpleName();



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void setLoading(boolean isLoading) {

    }

    @Override
    public void setModel(List<SKSensor> object) {

    }

    @Override
    public void addItem(SKSensor object) {

    }

    @Override
    public void error(Throwable t) {

    }
}
