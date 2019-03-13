package threads;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MultithreadCollectionTest {

    @Test
    public void test() throws InterruptedException {
        MultithreadCollection<Integer> collection = new MultithreadCollection<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                collection.add(i);
            }
        });
        t1.setName("Vasya");

        Thread t2 = new Thread(() -> {
            for (int i = 10; i <= 20; i++) {
                collection.add(i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertThat(collection.size(), is(21));
        Iterator<Integer> superIterator = collection.iterator();
        collection.add(333);
        collection.add(444);
        collection.add(555);
        collection.add(666);
        assertThat(collection.size(), is(25));

        while (superIterator.hasNext()) {
            System.out.print(superIterator.next() + " ");
        }
    }
}