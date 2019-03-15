package threads;

import org.junit.Test;

import java.util.Random;

public class ThreadPoolTest {

    /**
     * Тест пула.
     *
     * @throws InterruptedException sleep бросает.
     */
    @Test
    public void testPool() throws InterruptedException {
        final String sep = System.getProperty("line.separator");
        ThreadPool pool = new ThreadPool();
        Random r = new Random();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.printf("%s works i = %d%s", Thread.currentThread().getName(), i, sep);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);

        Thread.sleep(23000);
    }

    /**
     * Тест закрытого пула.
     *
     * @throws InterruptedException          sleep бросает.
     * @throws UnsupportedOperationException должен выбросить при добавлении задач в закрытый пул,
     *                                       иначе тест не пройдет.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testClosedPool() throws InterruptedException {
        final String sep = System.getProperty("line.separator");
        ThreadPool pool = new ThreadPool();
        Random r = new Random();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.printf("%s works i = %d%s", Thread.currentThread().getName(), i, sep);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);
        pool.work(thread);

        pool.shutdown();
        Thread.sleep(23000);
        pool.work(thread);
    }
}