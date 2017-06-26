package net.sereko.incense.decisions;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;

import net.sereko.incense.R;
import net.sereko.incense.model.Decision;
import net.sereko.incense.util.AppScheduler;
import net.sereko.incense.util.IScheduler;
import net.sereko.incense.view.IAdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by steve on 2/15/17.
 */

public class DecisionActivity extends AppCompatActivity implements IAdapterView<Decision> {

    private final String TAG = DecisionActivity.class.getSimpleName();

    @Bind(R.id.loading)
    ProgressBar loadingView;

//    @Bind(R.id.listview)
//    ListIView listView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Inject
    DecisionService service;

    @Inject
    IScheduler IScheduler;

    private DecisionPresenter presenter;

    // @TODO
    // Loading, floating button

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.driver_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ArrayList<Decision> decisions = new ArrayList<>();

//        listView.setAdapter(adapter);
        IScheduler = new AppScheduler();
        service = new DecisionService(this);

        presenter = new DecisionPresenter(service, IScheduler, this);
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
    public void addItem(Decision object) {

    }

    @Override
    public Decision getItem(int i) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void insert(Decision decision, int i) {

    }

    @Override
    public void setModel(List<Decision> objects) {

    }

    @Override
    public void setLoading(boolean isLoading) {
//        loadingView.setVisibility(isLoading ? android.view.IView.VISIBLE : android.view.IView.GONE);
    }



    @Override
    public void error(Throwable t) {

    }



    public Activity getActivity(){
        return this;
    }

    @Override
    public void setModel(HashMap<String, String> model) {

    }
}
