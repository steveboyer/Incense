package net.sereko.incense.presenter;

import net.sereko.incense.view.IView;

/**
 * Created by steve on 5/29/17.
 */

public interface IPresenter <V> {
        void start();

        void finish();

        void setView(IView<V> iView);



}
