package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс связанного списка с цикличностью.
 * @param <T> Тип данных.
 */
public class BadList<T> implements Iterable<T> {
    private Node<T> first;
    private int size;

    /**
     * Зацикливает список.
     *
     * @param nodeIndex Индекс узла, с которого начнется зацикливание.
     * @throws IndexOutOfBoundsException Возникает при передаче индекса, которого
     *                                   нет в списке.
     */
    public void setBrokenLink(int nodeIndex) {
        if (nodeIndex >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (nodeIndex == size - 1) {
            Node<T> nodeByIndex = getNodeByIndex(nodeIndex);
            nodeByIndex.next = first;
        } else if (nodeIndex == 0) {
            if (size == 1 || size == 2) {
                first.next = first;
            } else {
                Node<T> lastNode = getNodeByIndex(size - 1);
                first.next = first;
            }
        } else {
            Node<T> nodeByIndex = getNodeByIndex(nodeIndex - 1);
            nodeByIndex.next.next = nodeByIndex;
        }
    }

    /**
     * Получает узел по индексу.
     *
     * @param index Индекс.
     * @return Узел по индексу.
     * @throws IndexOutOfBoundsException Возникает при передаче индекса, которого
     *                                   нет в списке.
     */
    Node<T> getNodeByIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Обнаружает круговое замыкание.
     *
     * @return Было ли замыкание по кругу.
     */
    private boolean roundCycle() {
        boolean result = false;
        if (size > 0) {
            if (getNodeByIndex(0) == getNodeByIndex(size - 1).next) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Поиск цикличности (поиск ссылок на предыдущие элементы).
     * Основан на поиске с помощью двух курсоров.
     * Сначала запускается один, потом второй.
     * Если они по пути встречаются, значит цикличность имела место быть.
     *
     * @param first Узел, с которого начинается проверка.
     * @return Была ли обнаружена цикличность.
     */
    public boolean hasCycle(Node<T> first) {
        boolean result = roundCycle();
        if (!result) {
            if (first.next == first) {
                result = true;
            } else {
                Node<T> cursor1 = first;
                Node<T> cursor2 = first;
                if (cursor1.next != null) {
                    if (cursor1.next.next == cursor1) {
                        result = true;
                    } else if (cursor1.next.next != null) {
                        cursor1 = cursor1.next.next;
                    }
                }
                if (cursor1 != cursor2) {
                    while (cursor1 != null && cursor2 != null) {
                        cursor1 = cursor1.next;
                        cursor2 = cursor2.next;
                        if (cursor1 == cursor2) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Добавляет элемент в список.
     *
     * @param val Добавляемый элемент.
     */
    public void add(T val) {
        if (first == null) {
            first = new Node<T>(val);
        } else {
            Node<T> node = first;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(val);
        }
        size++;
    }

    /**
     * Возвращает итератор для списка.
     *
     * @return Итератор для списка.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> curNode = first;

            @Override
            public boolean hasNext() {
                return curNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = curNode.value;
                curNode = curNode.next;
                return result;
            }
        };
    }

    /**
     * Класс узла.
     *
     * @param <T> Тип данных.
     */
    class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}