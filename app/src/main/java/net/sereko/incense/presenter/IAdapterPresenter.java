package net.sereko.incense.presenter;

import net.sereko.incense.view.IAdapterView;

/**
 * Created by steve on 5/29/17.
 */

public interface IAdapterPresenter<V> {
        void start();

        void finish();

        void setView(IAdapterView<V> iView);

}
