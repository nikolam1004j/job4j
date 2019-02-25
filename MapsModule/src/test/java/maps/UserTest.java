package maps;

import org.junit.Test;

import java.util.Calendar;

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
}