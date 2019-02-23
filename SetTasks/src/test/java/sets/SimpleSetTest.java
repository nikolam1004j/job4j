package sets;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleSetTest {

    @Test
    public void duplicateTest() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("I");
        simpleSet.add(" am");
        simpleSet.add(" am");
        simpleSet.add(" Nikolay");
        simpleSet.add(" Nikolay");
        simpleSet.add("!");

        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = simpleSet.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        assertThat(sb.toString(), is("I am Nikolay!"));
        assertThat(simpleSet.size(), is(4));
        assertThat(simpleSet.capacity(), is(16));
    }
}