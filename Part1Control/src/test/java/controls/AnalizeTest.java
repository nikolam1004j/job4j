package controls;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalizeTest {

    @Test
    public void testBeforeAndAfter() {
        //Два списка
        List<Analize.User> previous = new ArrayList<>();
        List<Analize.User> current = new ArrayList<>();

        //Юзеры
        Analize.User nick = new Analize.User(1, "Nick");
        Analize.User den = new Analize.User(2, "Den");
        Analize.User denMod = new Analize.User(2, "Denis");
        Analize.User vova = new Analize.User(3, "Vova");
        Analize.User sergey = new Analize.User(4, "Serega");
        Analize.User nikolay = new Analize.User(1, "Nikolay");

        //Заполняем списки
        Collections.addAll(previous, nick, den, vova);
        Collections.addAll(current, denMod, sergey, nikolay);

        //Отдаем на проверку
        Analize analize = new Analize();
        Analize.Info diff = analize.diff(previous, current);

        //Добавлен только sergey
        Assert.assertEquals(diff.added, 1);
        //Удален vova
        Assert.assertEquals(diff.deleted, 1);
        //Изменены Den на Denis и Nick на Nikolay
        Assert.assertEquals(diff.changed, 2);
    }

}