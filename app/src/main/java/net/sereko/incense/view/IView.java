package net.sereko.incense.view;

/**
 * Created by steve on 2/21/17.
 */

public interface IView<T,V> {
    void setLoading(boolean isLoading);
    void setModel(T object);
    void addItem(V object);
    void error(Throwable t);
}
