package net.sereko.incense.view;

import android.app.Activity;

/**
 * Created by steve on 2/21/17.
 */

public interface View<T,V> {

    void setLoading(boolean isLoading);
    void setModel(T object);
    void addItem(V object);
    void error(Throwable t);
    Activity getActivity();
    V getItem(int i );
    int getItemCount();
    void insert(V v, int i);

}
