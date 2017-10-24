package net.sereko.incense.randomlists;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.sereko.incense.R;
import net.sereko.incense.model.Decision;
import net.sereko.incense.view.IAdapterView;
import net.sereko.incense.view.IView;

import butterknife.Bind;

/**
 * Created by steve on 10/19/17.
 */

public class RandomListsActivity extends AppCompatActivity implements IView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    private RandomListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.randomlists_main);
    }

    @Override
    public void setLoading(boolean isLoading) {

    }

    @Override
    public void error(Throwable t) {

    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void setModel(Object model) {

    }
}
