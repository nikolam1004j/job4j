package gens;

/**
 * @author Николай Разилов
 * @version 1
 * @since 20.02.2019
 */
public interface Store<T extends Base> {
    void add(T model);
    boolean replace(String id, T model);
    boolean delete(String id);
    T findById(String id);
}
