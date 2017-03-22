package net.sereko.incense.view;

import android.app.Activity;

/**
 * Created by steve on 2/21/17.
 */

public interface IView<V> {
    void setLoading(boolean isLoading);
    void setModel(V item);
    void error(Throwable t);
    Activity getActivity();
}
