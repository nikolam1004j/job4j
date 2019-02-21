package lists;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MyLinkedListTest {

    @Test
    public void simpleTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        assertThat(myLinkedList.get(0), is(1));
        assertThat(myLinkedList.get(1), is(2));
        assertThat(myLinkedList.get(2), is(3));
        assertThat(myLinkedList.getSize(), is(5));
        assertThat(myLinkedList.remove(0), is(1));
        assertThat(myLinkedList.getSize(), is(4));
        assertThat(myLinkedList.get(0), is(2));
        assertThat(myLinkedList.get(myLinkedList.getSize() - 1), is(5));
    }

    @Test
    public void iteratorTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        assertThat(myLinkedList.remove(1), is(2));
        assertThat(myLinkedList.remove(2), is(4));
        assertThat(myLinkedList.getSize(), is(3));

        Iterator<Integer> iterator = myLinkedList.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        assertThat(sb.toString(), is("135"));
    }

    @Test
    public void iteratorTestNextAndHasNext() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        Iterator<Integer> iterator = myLinkedList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionAfterAddItemTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        Iterator<Integer> iterator = myLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        myLinkedList.add(6);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionAfterDeleteItemTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        Iterator<Integer> iterator = myLinkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        myLinkedList.remove(myLinkedList.getSize() - 1);
        iterator.next();
    }

    @Test
    public void pushAndPollTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.push(3);
        myLinkedList.push(2);
        myLinkedList.push(1);

        assertThat(myLinkedList.get(0), is(1));
        assertThat(myLinkedList.get(1), is(2));
        assertThat(myLinkedList.get(2), is(3));

        assertThat(myLinkedList.poll(), is(1));
        assertThat(myLinkedList.poll(), is(2));
        assertThat(myLinkedList.poll(), is(3));
    }
}