package net.sereko.incense.presenter;

import net.sereko.incense.view.IListView;
import net.sereko.incense.view.IView;

/**
 * Created by steve on 2/21/17.
 */

public interface IPresenter<V> {
    void start();

    void finish();

    void setView(IView<V> iView);

    void setView(IListView<V> iListView);
}
