package lists;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BadListTest {

    @Test
    public void testCycleIfHasNoCycle() {
        BadList<Integer> list = new BadList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        assertThat(list.getNodeByIndex(1).next, is(list.getNodeByIndex(2)));
        assertThat(list.getNodeByIndex(2).next, is(list.getNodeByIndex(3)));
        assertThat(list.hasCycle(list.getNodeByIndex(0)), is(false));
    }

    @Test
    public void testCycleIfHasRoundCycle() {
        BadList<Integer> list = new BadList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.setBrokenLink(5);
        assertThat(list.hasCycle(list.getNodeByIndex(0)), is(true));
    }

    @Test
    public void testCycleIfHasCycle() {
        BadList<Integer> list = new BadList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.setBrokenLink(3);
        assertThat(list.getNodeByIndex(3).next, is(list.getNodeByIndex(2)));
        assertThat(list.hasCycle(list.getNodeByIndex(0)), is(true));
    }
}