package net.sereko.incense.presenter;

import net.sereko.incense.view.IView;

/**
 * Created by steve on 6/5/17.
 */

public interface IPresenter  {
    void start();

    void finish();

    void setView(IView iView);
}
