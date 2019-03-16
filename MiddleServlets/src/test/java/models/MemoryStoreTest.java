package models;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MemoryStoreTest {

    @Test
    public void insertTest() {
        User user = new User("nick", "nickraz", "nickraz@yandex.ru", new Date());
        final MemoryStore memoryStore = MemoryStore.getInstance();
        boolean add = memoryStore.add(user);
        assertThat(add, is(true));
    }

    @Test
    public void testSelectById() {
        User user = new User(1);
        final MemoryStore memoryStore = MemoryStore.getInstance();
        User byId = memoryStore.findById(user);
        Assert.assertNull(byId);

        User user20 = new User(20);
        User byId20 = memoryStore.findById(user20);
        System.out.println("byId20 = " + byId20);
    }

    @Test
    public void testFindAll() {
        final MemoryStore memoryStore = MemoryStore.getInstance();
        List<User> all = memoryStore.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testDeleteById() {
        final MemoryStore memoryStore = MemoryStore.getInstance();
        User user14 = new User(14);
        boolean delete = memoryStore.delete(user14);
        assertThat(delete, is(true));
    }

    @Test
    public void testUpdate() {
        final MemoryStore memoryStore = MemoryStore.getInstance();
        User user5 = new User(5);
        user5.setName("Nicky");
        user5.setCreateDate(new Date());
        user5.setEmail("gosha@mail.ru");
        user5.setLogin("Goga");
        boolean update = memoryStore.update(user5);
        assertThat(update, is(true));
    }
}