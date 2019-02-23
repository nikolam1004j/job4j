package sets;

import lists.DynamicArray;

import java.util.Iterator;

/**
 * Класс SimpleSet. Коллекция для хранения уникальных элементов.
 * @param <E> Тип данных.
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicArray<E> dynamicArray = new DynamicArray<>(16);

    /**
     * Возвращает размер коллекции.
     *
     * @return Размер коллекции.
     */
    public int size() {
        return dynamicArray.size();
    }

    /**
     * Возвращает емкость коллекции.
     *
     * @return Емкость коллекции.
     */
    public int capacity() {
        return dynamicArray.capacity();
    }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param val Добавляемый элемент.
     */
    public void add(E val) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(val)) {
                return;
            }
        }
        dynamicArray.add(val);
    }

    /**
     * Возвращает итератор для коллекции.
     *
     * @return Итератор для коллекции.
     */
    @Override
    public Iterator<E> iterator() {
        return dynamicArray.iterator();
    }
}