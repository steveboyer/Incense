package net.sereko.incense.view;

import android.app.Activity;

/**
 * Created by steve on 2/21/17.
 */

public interface IView {
    void setLoading(boolean isLoading);
    void error(Throwable t);
    Activity getActivity();
//    void setModel(HashMap<String, String> model);
    void setModel(Object model);
}
