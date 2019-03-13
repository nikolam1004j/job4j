package threads;

import lists.DynamicArray;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class MultithreadCollection<E> implements Iterable<E>{
    @GuardedBy("this")
    private DynamicArray<E> array = new DynamicArray<>();

    public synchronized int size() {
        return array.size();
    }

    public synchronized int capacity() {
        return array.capacity();
    }

    public synchronized void add(E val) {
        array.add(val);
    }

    public synchronized E get(int index) {
        return array.get(index);
    }

    private synchronized DynamicArray<E> copy() {
        DynamicArray<E> innerArray = new DynamicArray<>();
        for (E e : array) {
            innerArray.add(e);
        }
        return innerArray;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy().iterator();
    }
}
