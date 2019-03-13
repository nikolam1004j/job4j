package lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический список на базе массива.
 * @author Николай Разилов
 * @since 21.02.2019
 * @param <E> Тип данных.
 */
public class DynamicArray<E> implements Iterable<E> {
    private Object[] array;
    private int startCapacity;
    private int capacity;
    private int carriage;
    private int modCount;

    /**
     * Создает динамический список на базе массива емкостью startCapacity.
     *
     * @param startCapacity Емкость списка.
     */
    public DynamicArray(int startCapacity) {
        capacity = startCapacity;
        this.startCapacity = startCapacity;
        array = new Object[capacity];
    }

    /**
     * Создает динамический список на базе массива с параметрами по умолчанию
     * (Емкость равна 10).
     */
    public DynamicArray() {
        capacity = 10;
        startCapacity = 10;
        array = new Object[capacity];
    }

    /**
     * Возвращает размер списка.
     *
     * @return Размер списка.
     */
    public int size() {
        return carriage;
    }

    /**
     * Возвращает емкость списка.
     *
     * @return Емкость списка.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Добавляет элемент в список.
     *
     * @param val Добавляемый элемент.
     */
    public void add(E val) {
        if (array.length == carriage) {
            increaseCapacity();
        }
        array[carriage++] = val;
    }

    /**
     * Увеличивает емкость списка.
     */
    private void increaseCapacity() {
        Object[] array2 = new Object[array.length + startCapacity];
        System.arraycopy(array, 0, array2, 0, array.length);
        array = array2;
        modCount++;
        capacity += startCapacity;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index Индекс.
     * @return Возвращаемый элемент.
     * @throws ArrayIndexOutOfBoundsException если индекс за пределами списка.
     */
    public E get(int index) {
        return (E) array[index];
    }

    /**
     * Возвращает итератор для списка.
     *
     * @return Итератор для списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int changes = modCount;
            private int iteratorCarriage;

            @Override
            public boolean hasNext() {
                if (changes != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCarriage < carriage;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) array[iteratorCarriage++];
            }
        };
    }
}