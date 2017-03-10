package net.sereko.incense.presenter;

import net.sereko.incense.view.View;

/**
 * Created by steve on 2/21/17.
 */

public interface IPresenter<T, V> {
    void start();

    void finish();

    void setView(View<T, V> view);
}
