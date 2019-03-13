package threads;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
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
}