package threads;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void test1() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        new Thread(() -> {
            try {
                System.out.println(queue.poll());
                System.out.println(queue.poll());
                System.out.println(queue.poll());
                //Здесь блокировка. Четвертого числа еще нет
                System.out.println(queue.poll());
                //Здесь блокировка. Пятого числа еще нет
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000);
        queue.offer(4);
        Thread.sleep(3000);
        queue.offer(5);
    }

    @Test
    public void test2() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final List<Integer> list = new CopyOnWriteArrayList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        new Thread(() -> {
            try {
                list.add(queue.poll());
                list.add(queue.poll());
                list.add(queue.poll());
                list.add(queue.poll());
                list.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        queue.offer(4);
        Thread.sleep(2000);
        queue.offer(5);
        assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5)));
    }
}