package lists;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DynamicArrayTest {

    @Test
    public void simpleOperationsTest() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(5);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);

        assertThat(dynamicArray.get(0), is(1));
        assertThat(dynamicArray.get(1), is(2));
        assertThat(dynamicArray.get(2), is(3));
        assertThat(dynamicArray.size(), is(4));
        assertThat(dynamicArray.capacity(), is(5));

        dynamicArray.add(5);
        dynamicArray.add(6);

        assertThat(dynamicArray.size(), is(6));
        assertThat(dynamicArray.capacity(), is(10));
    }

    @Test
    public void iteratorTest() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(5);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : dynamicArray) {
            sb.append(integer);
        }
        assertThat(sb.toString(), is("1234"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionTest() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(5);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);

        Iterator<Integer> iterator = dynamicArray.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        dynamicArray.add(5);
        assertThat(dynamicArray.capacity(), is(5));
        dynamicArray.add(6);
        assertThat(dynamicArray.capacity(), is(10));
        assertThat(iterator.next(), is(3));
    }
}