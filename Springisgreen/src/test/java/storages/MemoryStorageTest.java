package storages;

import models.User;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class MemoryStorageTest {
    @Test
    public void whenWeAddUserToMemoryStorage() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MemoryStorage memoryStorage = context.getBean(MemoryStorage.class);
        assertNotNull(memoryStorage);
        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user2");
        assertNotNull(user1);
        assertNotNull(user2);
        memoryStorage.add(user1);
        memoryStorage.add(user2);
        memoryStorage.forEach(System.out::println);
        context.close();
    }
}