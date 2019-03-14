package threads;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CatchException {

    /**
     * Тест из задания
     * @throws InterruptedException join бросает
     */
    @Test
    public void whenThrowException() throws InterruptedException {
        final String sep = System.getProperty("line.separator");
        Thread thread = new Thread(
                () -> {
                    throw new RuntimeException("Throw Exception in Thread");
                }
        );
        thread.start();
        thread.join();
        System.out.printf("Thread %s works normally%s", Thread.currentThread().getName(), sep);
    }

    /**
     * Одним потоком.
     */
    @Test
    public void testOneThread() {
        NonBlockCache cache = new NonBlockCache();
        cache.add(new Base(1, 1));
        cache.add(new Base(2, 1));
        assertThat(cache.size(), is(2));
        Base update = cache.update(new Base(1, 1));
        System.out.println("update = " + update);
        System.out.println("----------");
        Iterator<Map.Entry<Integer, Base>> iterator = cache.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Base delete = cache.delete(update);
        System.out.println("deleting " + delete);
        assertThat(delete, is(new Base(1, 2)));
        System.out.println("After delete:");
        Iterator<Map.Entry<Integer, Base>> iterator2 = cache.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        assertThat(cache.size(), is(1));
    }

    /**
     * Многопоточный доступ.
     * @throws InterruptedException join бросает
     * @throws OptimisticException при доступе к элементу, версия которого отлична.
     */
    @Test
    public void testSeveralThreads() throws InterruptedException {
        AtomicReference<RuntimeException> reference = new AtomicReference<>();
        final String sep = System.getProperty("line.separator");
        final NonBlockCache cache = new NonBlockCache();
        for (int i = 1; i <= 10; i++) {
            cache.add(new Base(i, 1));
        }

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    cache.update(new Base(i, 1));
                } catch (OptimisticException ex) {
                    System.err.println(Thread.currentThread().getName());
                    System.err.println(Integer.toHexString(ex.hashCode()));
                    ex.printStackTrace();
                    reference.set(ex);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                try {
                    cache.update(new Base(i, 1));
                } catch (OptimisticException ex) {
                    System.err.println(Thread.currentThread().getName());
                    System.err.println(Integer.toHexString(ex.hashCode()));
                    ex.printStackTrace();
                    reference.set(ex);
                }
            }
        });

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("-----");
        System.out.println("Transfering exception from second thread to main thread");
        System.err.println(Thread.currentThread().getName());
        System.err.println(Integer.toHexString(reference.get().hashCode()));
        reference.get().printStackTrace();
        assertThat(reference.get().getMessage(), is("Разные версии"));
    }
}
