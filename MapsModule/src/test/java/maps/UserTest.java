package maps;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class UserTest {

    @Test
    public void testHashCode() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990,12,20);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1994,06,2);
        User u1 = new User("Nick", 10, calendar);
        User u2 = new User("Nick", 10, calendar);
        User u3 = new User("Vova", 11, calendar2);

        assertThat(u1 == u2, is(false));
        assertThat(u1.equals(u2), is(true));
        assertThat(u1, is(u2));
        assertThat(u1.hashCode(), is(u2.hashCode()));
        assertThat(u1.hashCode(), not(u3.hashCode()));
        assertThat(u1, not(u3));
    }

    @Test
    public void test2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990,12,20);
        User u1 = new User("Nick", 10, calendar);
        User u2 = new User("Nick", 10, calendar);
        Map<User, Object> map = new HashMap<User, Object>(){{
            put(u1, "u1");
            put(u2, "u2");
        }};

    }

    @Test
    public void test3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990,12,20);
        User u1 = new User("Nick", 10, calendar);
        User u2 = new User("Nick", 10, calendar);
        Map<User, Object> map = new HashMap<User, Object>(){{
            put(u1, "u1");
            put(u2, "u2");
        }};

        System.out.println("map = " + map);
        System.out.println(map.get(u1));
        System.out.println(map.get(u2));
    }
}