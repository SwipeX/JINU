package pw.dekk.model;

/**
 * Created by TimD on 5/3/2016.
 */
public abstract class Response<T> implements Filter<T> {
    public abstract void react(T t);
}
