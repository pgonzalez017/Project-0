package db;

import util.ArrayList;

/**
 * A dao contract to determine functionality in the GymDao objects.
 * @param <T> The class used for this dao object.
 * @param <I> The primary key used by the class.
 */

public interface GenericDao<T, I> {
    abstract void save(T t);

    abstract T getById(I id);

    abstract boolean remove(I id);

    abstract ArrayList<T> getAll();

    abstract boolean update(T t);

    abstract int updateAll(ArrayList<T> collection);
}
