package lists;

/**
 * Класс очереди на двух стеках.
 * @param <T> Тип данных.
 */
public class SimpleQueue<T> {
    private SimpleStack<T> stack1 = new SimpleStack<>();
    private SimpleStack<T> stack2 = new SimpleStack<>();

    /**
     * Переливает данные из стека from в стек to.
     *
     * @param from Стек, откуда переливаются данные.
     * @param to   Стек, куда переливаются данные.
     */
    private void exchange(SimpleStack<T> from, SimpleStack<T> to) {
        int size = from.getSize();
        for (int i = 0; i < size; i++) {
            to.push(from.poll());
        }
    }

    /**
     * Добавление элемента в очередь.
     *
     * @param val Добавляемый элемент.
     */
    public void add(T val) {
        exchange(stack1, stack2);
        stack1.push(val);
    }

    /**
     * Извлекает элемент с удалением.
     *
     * @return Извлекаемый элемент.
     */
    public T poll() {
        exchange(stack2, stack1);
        return stack1.poll();
    }
}