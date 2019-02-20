package deps;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Класс тестирования департаментов
 * @author Николай Разилов
 * @since 19.02.2019
 */
public class DepartmentTest {

    private Departament initData(boolean isReverse) {
        Departament departament = new Departament(isReverse);
        departament.add("K1\\SK1");
        departament.add("K1\\SK2");
        departament.add("K1\\SK1\\SSK1");
        departament.add("K1\\SK1\\SSK2");
        departament.add("K2");
        departament.add("K2\\SK1");
        departament.add("K2\\SK1\\SSK1");
        departament.add("K2\\SK1\\SSK2");
        return departament;
    }

    @Test
    public void simpleTest() {
        Departament departament = new Departament(false);
        departament.add("K1\\SK1\\SSK1");
        List<String> correctDeps = Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1");
        Assert.assertEquals(departament.size(), correctDeps.size());
        Iterator<String> depsIterator = departament.iterator();
        Iterator<String> listCollectionIterator = correctDeps.iterator();
        while (depsIterator.hasNext()) {
            Assert.assertEquals(depsIterator.next(), listCollectionIterator.next());
        }
    }

    @Test
    public void defaultComparatorTest() {
        Departament departament = initData(false);
        List<String> correctResultList = Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2");
        Assert.assertEquals(departament.size(), correctResultList.size());
        Iterator<String> depsIterator = departament.iterator();
        Iterator<String> listCollectionIterator = correctResultList.iterator();
        while (depsIterator.hasNext()) {
            Assert.assertEquals(depsIterator.next(), listCollectionIterator.next());
        }
    }

    @Test
    public void reverseComparatorTest() {
        Departament departament = initData(true);
        List<String> reverseResultList = Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1");
        Assert.assertEquals(departament.size(), reverseResultList.size());
        Iterator<String> depsIterator = departament.iterator();
        Iterator<String> listCollectionIterator = reverseResultList.iterator();
        while (depsIterator.hasNext()) {
            Assert.assertEquals(depsIterator.next(), listCollectionIterator.next());
        }
    }
}
