package gens;

/**
 * Класс AbstractStore.
 * AbstractStore<T extends Base> : Хранение типов Base и его наследников.
 * Store<T> : Класс имплементирует интерфейс Store с методами, возвращающими
 * конкретный тип T.
 * @author Николай Разилов
 * @since 21.02.2019
 * @param <T> Тип данных (<T extends Base>)
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> array;

    /**
     * Создает объект класса наследника AbstractStore.
     * @param n Размер хранилища.
     */
    public AbstractStore(int n) {
        array = new SimpleArray<>(n);
    }

    /**
     * Добавляет элемент в хранилище.
     * @param model Добавляемый элемент.
     */
    @Override
    public void add(T model) {
        array.add(model);
    }

    /**
     * Замена элемента в хранилище.
     * @param id Ид элемента.
     * @param model Новый элемент.
     * @return Была ли проведена замена.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(id)) {
                array.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Удаляет элемент из хранилища.
     * @param id Ид удаляемого элемента.
     * @return Был ли удален элемент.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(id)) {
                array.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Осуществляет поиск элемента в хранилище по Ид.
     * @param id Ид элемента.
     * @return Возвращает элемент, или null, если элемента найдено не было.
     */
    @Override
    public T findById(String id) {
        T result = null;
        for (T t : array) {
            if (t.getId().equals(id)) {
                result = t;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает размер хранилища.
     * @return Размер хранилища.
     */
    public int size() {
        return array.size();
    }
}
