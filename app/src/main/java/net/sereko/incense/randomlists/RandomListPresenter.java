package net.sereko.incense.randomlists;

import android.text.TextUtils;

import net.sereko.incense.decisions.DecisionPresenter;
import net.sereko.incense.presenter.IPresenter;
import net.sereko.incense.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by steve on 10/19/17.
 */

public class RandomListPresenter implements IPresenter {
    private IView view;

    public RandomListPresenter(IView view){
        this.view = view;
    }

    @Override
    public void start() {
        EventBus.getDefault().register(this);
        //if(TextUtils.isEmpty()) // If text field is empty
        DatabaseHelper db = DatabaseHelper.getInstance(view.getActivity());
        db.loadNote(0);
    }

    @Override
    public void finish() {
        DatabaseHelper.getInstance(view.getActivity()).updateNote(0, ""); // Save note on stop

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setView(IView iView) {
        this.view = iView;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListLoaded(ListLoadedEvent event){
        if(event.getPosition() == 0){
            // Set something
        }
    }
}
