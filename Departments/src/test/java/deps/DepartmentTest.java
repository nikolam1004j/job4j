package deps;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Класс тестирования департаментов
 * @author Николай Разилов
 * @since 19.02.2019
 */
public class DepartmentTest {
    private Departament departament;

    private void initDefaultComarator(boolean isReverse) {
        departament = new Departament(isReverse);
    }

    private void fillData() {
        departament.add("K1\\SK1");
        departament.add("K1\\SK2");
        departament.add("K1\\SK1\\SSK1");
        departament.add("K1\\SK1\\SSK2");
        departament.add("K2");
        departament.add("K2\\SK1");
        departament.add("K2\\SK1\\SSK1");
        departament.add("K2\\SK1\\SSK2");
    }

    @Test
    public void defaultComaratorTest() {
        initDefaultComarator(false);
        fillData();
        List<String> correctResultList = Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2");
        Assert.assertEquals(correctResultList.toString(), departament.toString());
    }

    @Test
    public void reverseComaratorTest() {
        initDefaultComarator(true);
        fillData();
        List<String> reverseResultList = Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1");
        Assert.assertEquals(reverseResultList.toString(), departament.toString());
    }
}
