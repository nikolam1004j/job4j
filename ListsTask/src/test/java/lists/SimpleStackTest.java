package lists;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleStackTest {

    @Test
    public void stackTest() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(10);
        stack.push(100);
        stack.push(1000);

        assertThat(stack.poll(), is(1000));
        assertThat(stack.poll(), is(100));
        assertThat(stack.poll(), is(10));
        assertThat(stack.poll(), is(1));
        assertThat(stack.getSize(), is(0));
    }
}