package net.sereko.incense.view;

import java.util.List;

/**
 * Created by steve on 3/21/17.
 */

public interface IListView<V> extends IView<V> {
    void addItem(V object);
    V getItem(int i );
    int getItemCount();
    void insert(V v, int i);
    void setModel(List<V> objects);
}
