package net.sereko.incense.view;

import java.util.List;

/**
 * Created by steve on 6/5/17.
 */

public interface IAdapterView<V> extends IView {
    void addItem(V object);
    V getItem(int i );
    int getItemCount();
    void insert(V v, int i);
    void setModel(List<V> objects);
}
