package gens;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс обертки над массивом.
 * @author Николай Разилов
 * @since 10.02.2019
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int carriage;

    /**
     * Создает объект класса SimpleArray.
     * @param n Размер коллекции.
     */
    public SimpleArray(int n) {
        array = new Object[n];
    }

    /**
     * Возвращает размер коллекции.
     * @return Размер коллекции.
     */
    public int size() {
        return carriage;
    }

    /**
     * Добавляет элемент в коллекцию.
     * @param val Добавляемый элемент.
     */
    public void add(T val) {
        if (carriage >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[carriage++] = val;
    }

    /**
     * Получает элемент из коллекции по индексу.
     * @param index Индекс.
     * @return Элемент из коллекции.
     */
    public T get(int index) {
        throwIfBadIndex(index);
        return (T) array[index];
    }

    /**
     * Бросает исключения, если индес выходит на границы коллекции.
     * @param index Индекс.
     */
    private void throwIfBadIndex(int index) {
        if (index >= carriage || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Задает значение по индексу.
     * @param index Индекс.
     * @param model Значение.
     */
    public void set(int index, T model) {
        throwIfBadIndex(index);
        array[index] = model;
    }

    /**
     * Возвращает элемент по индексу и удаляет его.
     * @param index Индекс.
     * @return Возвращаемый элемент.
     */
    public T remove(int index) {
        throwIfBadIndex(index);
        T result = (T) array[index];
        if (index != array.length - 1) {
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            array[array.length - 1] = null;
        } else if (size() == 1 || index == array.length - 1) {
            array[index] = null;
        }
        carriage--;
        return result;
    }

    /**
     * Итератор для обертки над массивом.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int innerCarriage;

            @Override
            public boolean hasNext() {
                return innerCarriage < carriage;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[innerCarriage++];
            }
        };
    }
}
