package trees;

import java.util.*;

/**
 * Класс дерева.
 * @param <E> Тип данных.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    /**
     * Создает дерево.
     *
     * @param val Значение корня дерева.
     */
    public Tree(E val) {
        this.root = new Node<E>(val);
    }

    /**
     * Добавляет элемент в дерево,
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> nodeParent = findBy(parent).orElse(null);
        if (nodeParent != null) {
            Node<E> nodeChild = findBy(child).orElse(null);
            if (nodeChild == null) {
                nodeParent.add(new Node<E>(child));
                result = true;
            }
        }
        return result;
    }

    /**
     * Ищет элемент в дереве по значению.
     *
     * @param value Значение.
     * @return Элемент дерева.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Возвращает итератор для дерева.
     *
     * @return Итератор для дерева.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = root;
            private LinkedList<Node<E>> stackUnchecked = new LinkedList<>();

            /**
             * Динамический блок.
             * Добавляем корневой элемент в стек, если этот элемент не равен null.
             */ {
                if (node != null) {
                    stackUnchecked.push(node);
                }
            }

            /**
             * Забираем элемент из вершины стека.
             * Так же добавляем с помощью цикла дочерние элементы текущего
             * узла, если они есть.
             */
            private void proc() {
                if (!stackUnchecked.isEmpty()) {
                    node = stackUnchecked.poll();
                }
                for (Node<E> leaf : node.leaves()) {
                    stackUnchecked.push(leaf);
                }
            }

            @Override
            public boolean hasNext() {
                return !stackUnchecked.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = stackUnchecked.peek().getValue();
                proc();
                return result;
            }
        };
    }
}