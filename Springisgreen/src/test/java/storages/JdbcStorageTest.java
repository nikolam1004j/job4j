package storages;

import models.User;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class JdbcStorageTest {
    @Test
    public void whenAddToJdbcStorage() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        JdbcStorage jdbcStorage = context.getBean(JdbcStorage.class);
        assertNotNull(jdbcStorage);
        User user3 = (User) context.getBean("user3");
        assertNotNull(user3);
        jdbcStorage.add(user3);
        context.close();
    }
}