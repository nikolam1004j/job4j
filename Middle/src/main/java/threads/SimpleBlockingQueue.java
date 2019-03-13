package threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("lock")
    private final Queue<T> queue = new LinkedList<>();
    private final Object lock = new Object();

    public void offer(T value) {
        synchronized (lock) {
            queue.offer(value);
            lock.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == 0) {
                lock.wait();
            }
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (lock) {
            return queue.size() == 0;
        }
    }
}