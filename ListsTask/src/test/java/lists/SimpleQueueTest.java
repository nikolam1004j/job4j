package lists;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleQueueTest {

    @Test
    public void queueTwoStacksTest() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

}