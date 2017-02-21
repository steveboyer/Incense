package net.sereko.incense.presenter;

import net.sereko.incense.view.IView;

/**
 * Created by steve on 2/21/17.
 */

public interface IPresenter<T> {
    void start();

    void finish();

    void setView(IView<T> view);
}
