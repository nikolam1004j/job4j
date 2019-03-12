package threads;

import org.junit.Assert;
import org.junit.Test;

public class ThreadProblemTest {

    @Test
    public void testThreadProblem() throws InterruptedException {
        ThreadProblem threadProblem = new ThreadProblem();
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                threadProblem.incrementX();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                threadProblem.incrementX();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Assert.assertEquals(threadProblem.getX(), 2000);
    }
}