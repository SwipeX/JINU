package pw.dekk.model;

/**
 * Created by TimD on 5/3/2016.
 */
public interface Filter<T> {
    boolean accept(T t);
}
