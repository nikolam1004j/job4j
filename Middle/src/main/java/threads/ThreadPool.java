package threads;

import java.util.LinkedList;
import java.util.List;

/**
 * Пул потоков.
 * @author Николай Разилов.
 * @since 15.03.2019.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final int poolSize;
    private boolean isPoolEnabled = true;

    /**
     * Запускает задачу. Если есть свободное песто в пуле, занимает его.
     * Если нет, ждет, пока выполнится одна из задач в пуле и занимает освободившееся место.
     *
     * @param index Индекс освобожденного элемента.
     * @throws InterruptedException poll() бросает, т.к. в нем сидит метод wait.
     */
    private void run(int index) throws InterruptedException {
        Runnable poll = tasks.poll();
        if (threads.size() < poolSize) {
            threads.add(new Worker(new Thread(poll), threads.size()));
            threads.get(threads.size() - 1).start();
        } else {
            threads.set(index, new Worker(new Thread(poll), index));
            threads.get(index).start();
        }
    }

    /**
     * Создает пул потоков. Размер пула зависит от количества процессоров.
     */
    public ThreadPool() {
        poolSize = Runtime.getRuntime().availableProcessors();
    }

    /**
     * Добавляет поток в пул.
     *
     * @param job Поток.
     */
    public void work(Runnable job) {
        if (!isPoolEnabled) {
            throw new UnsupportedOperationException("Пул закрыт");
        }
        tasks.offer(job);
        if (threads.size() < poolSize) {
            try {
                run(threads.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Закрывает пул.
     */
    public void shutdown() {
        isPoolEnabled = false;
    }

    /**
     * Класс обертка для потоков.
     * Создан для того, чтобы потоки могли оповещать об освобождении места в пуле.
     */
    private class Worker extends Thread {
        private ThreadPool myPool = ThreadPool.this;
        private final Thread thread;
        private int index;

        public Worker(Thread thread, int index) {
            this.thread = thread;
            this.index = index;
        }

        @Override
        public void run() {
            thread.run();
            try {
                myPool.run(index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}