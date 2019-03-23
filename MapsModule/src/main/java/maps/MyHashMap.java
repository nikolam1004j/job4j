package maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * HashMap без разрешения коллизий.
 * @param <K> Тип данных ключа.
 * @param <V> Тип данных значения.
 */
public class MyHashMap<K, V> implements Iterable<MyHashMap.Entry> {

    private Entry[] entries;
    private int size = 16;
    private int carriage;
    private static final double LOAD_FACTOR = 0.75;
    private double curLoading;

    /**
     * Создает коллекцию MyHashMap с размером 16 элементов.
     */
    public MyHashMap() {
        entries = new Entry[size];
    }

    /**
     * Возвращает количество добавленных элементов в вколлекцию.
     *
     * @return Количество добавленных элементов.
     */
    public int size() {
        return carriage;
    }

    /**
     * Добавляет значения в коллекцию.
     *
     * @param key   Ключ.
     * @param value Значение.
     * @return Было ли добавлено значение в коллекцию.
     */
    public boolean insert(K key, V value) {
        if (carriage == size || curLoading >= LOAD_FACTOR) {
            rehash();
        }
        boolean result = false;
        int index = getIndex(key);
        if (entries[index] == null) {
            entries[index] = new Entry<K, V>(key, value);
            carriage++;
            result = true;
            refreshCurLoadFactor();
        } else {
            if (entries[index].key.hashCode() == key.hashCode()) {
                if (entries[index].key.equals(key)) {
                    entries[index] = new Entry<K, V>(key, value);
                    carriage++;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Возвращает индекс элемента по ключу.
     *
     * @param key Ключ.
     * @return Индекс элемента.
     */
    private int getIndex(K key) {
        return key == null ? 0 : indexFor(key);
    }

    /**
     * Получает элемент из коллекции по заданному объекту. Если объекта нет, вернет null.
     *
     * @param key Ключ, по которому ведется поиск.
     * @return Возвращает найденный объект. Если объекта нет, вернет null.
     */
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        if (entries[index].key.equals(key)) {
            result = (V) entries[index];
        }
        return result;
    }

    /**
     * Удаляет элемент из коллекции по ключу.
     *
     * @param key Ключ.
     * @return Было ли произведено удаление.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (entries[index].key.equals(key)) {
            entries[index] = null;
            result = true;
            carriage--;
            refreshCurLoadFactor();
        }
        return result;
    }

    /**
     * Обновить текущий loadFactor.
     */
    private void refreshCurLoadFactor() {
        this.curLoading = (double) carriage / size;
    }

    /**
     * Перехеширование.
     */
    private void rehash() {
        size <<= 1;
        Entry[] array = entries;
        entries = new Entry[size];
        refreshCurLoadFactor();
        carriage = 0;
        for (Entry entry : array) {
            if (entry != null) {
                insert((K) entry.key, (V) entry.value);
            }
        }
    }

    /**
     * Нормализация хэша.
     *
     * @param key Объект ключ.
     * @return Нормализованный хэш.
     */
    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Высчитывает позицию элемента в массиве на основе нормализованного хэша и размера массива.
     *
     * @param key Ключ.
     * @return Позиция элемента в массиве.
     */
    private int indexFor(K key) {
        return (size - 1) & hash(key);
    }

    /**
     * Возвращает итератор для коллекции.
     *
     * @return Итератор для коллекции.
     */
    @Override
    public Iterator<MyHashMap.Entry> iterator() {
        return new Iterator<MyHashMap.Entry>() {
            private int innerCarriage;

            {
                skipNulls();
            }

            private void skipNulls() {
                while (hasNext() && entries[innerCarriage] == null) {
                    innerCarriage++;
                }
            }

            @Override
            public boolean hasNext() {
                return innerCarriage < size;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> result = entries[innerCarriage++];
                skipNulls();
                return result;
            }
        };
    }

    /**
     * Класс элемента корзины.
     *
     * @param <K> Тип ключа.
     * @param <V> Тип значения.
     */
    class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}