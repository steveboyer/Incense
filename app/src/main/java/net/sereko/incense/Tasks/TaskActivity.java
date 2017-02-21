package net.sereko.incense.Tasks;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.sereko.incense.Activity;
import net.sereko.incense.R;
import net.sereko.incense.SKDialog;

import java.util.ArrayList;

import icepick.Icepick;

public class TaskActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = TaskActivity.class.getSimpleName();
    private ListView listview;
    private TaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Task> tasks = new ArrayList<>();

        Task t = new Task();
        t.setName("Caaaat");
        tasks.add(t);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new TaskAdapter(this, tasks, this));
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
        //if(!isChangingConfigurations()) mainView = null;
    }

    public void onItemsNext(){

    }

    public void onItemsError(Throwable throwable){

    }


    @Override
    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.didit_button) {
            // Create instance with default values

        }
    }

    public void openDialog(){
        DialogFragment dialog = new SKDialog();
        dialog.show(getSupportFragmentManager(), "SKDialog");
    }
}
