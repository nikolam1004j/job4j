package gens;

import org.junit.Assert;
import org.junit.Test;

public class SimpleArrayTest {

    @Test
    public void simpleTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);

        Assert.assertEquals(simpleArray.size(), 5);
        Assert.assertEquals(simpleArray.get(0), Integer.valueOf(1));
        Assert.assertEquals(simpleArray.get(1), Integer.valueOf(2));
        Assert.assertEquals(simpleArray.get(2), Integer.valueOf(3));
        Assert.assertEquals(simpleArray.get(3), Integer.valueOf(4));
        Assert.assertEquals(simpleArray.get(4), Integer.valueOf(5));

        Assert.assertEquals(simpleArray.remove(2), Integer.valueOf(3));
        Assert.assertEquals(simpleArray.remove(0), Integer.valueOf(1));
        Assert.assertEquals(simpleArray.remove(2), Integer.valueOf(5));

        Assert.assertEquals(simpleArray.size(), 2);

        simpleArray.set(0, 100);
        simpleArray.set(1, 555);
        Assert.assertEquals(simpleArray.get(0), Integer.valueOf(100));
        Assert.assertEquals(simpleArray.get(1), Integer.valueOf(555));
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);

        StringBuilder sb = new StringBuilder();
        for (Integer integer : simpleArray) {
            sb.append(integer);
        }
        Assert.assertEquals(sb.toString(), "12345");
    }
}