package net.sereko.incense.view;

/**
 * Created by steve on 2/21/17.
 */

public interface IView<T> {
    void setLoading(boolean isLoading);
    void setModel(T object);
    void error(Throwable t);
}
