package lists;

/**
 * Класс стека.
 * @param <T> Тип данных.
 */
public class SimpleStack<T> {
    private MyLinkedList<T> linkedList = new MyLinkedList<>();

    /**
     * Добавляет элемент в стек.
     *
     * @param val Добавляемый элемент.
     */
    public void push(T val) {
        linkedList.push(val);
    }

    /**
     * Извлекает элемент из стека и удаляет его.
     *
     * @return Извлекаемый элемент.
     */
    public T poll() {
        return linkedList.poll();
    }

    /**
     * Возвращает размер стека.
     *
     * @return Размер стека.
     */
    public int getSize() {
        return linkedList.getSize();
    }
}
