package lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер на базе связанного списка.
 * @param <E> Тип данных.
 */
public class MyLinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private int size;
    private int modCount;

    /**
     * Возвращает размер списка.
     *
     * @return Размер списка.
     */
    public int getSize() {
        return size;
    }

    /**
     * Добавляет элемент в список.
     *
     * @param val Добавляемый элемент.
     */
    public void add(E val) {
        if (first == null) {
            first = new Node<>(val);
        } else {
            Node<E> node = first;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(val);
            node.next.prev = node;
        }
        modCount++;
        size++;
    }

    /**
     * Извлекает элемент из начала списка с удалением.
     *
     * @return Извлекаемый элемент
     */
    public E dropFirst() {
        return remove(0);
    }

    /**
     * Извлекает элемент из конца списка с удалением.
     *
     * @return Извлекаемый элемент
     */
    public E dropLast() {
        return remove(size - 1);
    }

    /**
     * Возвращает узел по индексу.
     *
     * @param index Индекс.
     * @return Узел по индексу.
     */
    private Node<E> getNode(int index) {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Возвращает значение узла по индексу.
     *
     * @param index Индекс.
     * @return Значение узла.
     */
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.date;
    }

    /**
     * Удаляет узел по индексу, возвращая его значение.
     *
     * @param index Индекс.
     * @return Возвращаемое значение удаляемого узла.
     */
    public E remove(int index) {
        Node<E> node = getNode(index);
        E result = node.date;
        if (node == first) {
            if (node.next == null) {
                first = null;
            } else {
                node.next.prev = null;
                first = first.next;
            }
        } else if (index == size - 1) {
            node.prev.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        modCount++;
        size--;
        return node == null ? result : node.date;
    }

    /**
     * Возвращает итератор для списка.
     *
     * @return Итератор для списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> curNode = first;
            private int innerModCount = modCount;

            @Override
            public boolean hasNext() {
                if (innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return curNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = curNode.date;
                curNode = curNode.next;
                return result;
            }
        };
    }

    /**
     * Класс узла.
     *
     * @param <E> Тип данных.
     */
    private static class Node<E> {
        private E date;
        private Node<E> next;
        private Node<E> prev;

        public Node(E date) {
            this.date = date;
        }
    }
}