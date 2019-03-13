package threads;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void test() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        assertThat(storage.add(user1), is(true));
        assertThat(storage.add(user2), is(true));
        assertThat(storage.add(new User(3, 300)), is(true));
        assertThat(storage.add(new User(1, 150)), is(false));
        assertThat(storage.update(new User(1, 111)), is(true));

        storage.transfer(user1.getId(), user2.getId(), 50);
        assertThat(user1.getAmount(), is(61));
        assertThat(user2.getAmount(), is(250));
    }

}