package Main;

import models.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import storages.JdbcStorage;

import java.sql.Timestamp;
import java.util.Scanner;

/**
 * Класс для добавления позователей в БД.
 * Использован ConfigurableApplicationContext, т.к. ApplicationContext не имеет метода close(),
 * что не позволяет закрыть контекст и вызвать методы destroy у бинов.
 */
public class ImportUser {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        JdbcStorage jdbcStorage = context.getBean(JdbcStorage.class);
        String stop = (String)context.getBean("stop");
        try(Scanner sc = new Scanner(System.in)) {
            String line;
            while (!(line = sc.nextLine()).equals(stop)) {
                jdbcStorage.add(new User(line, new Timestamp(System.currentTimeMillis())));
            }
        }
        context.close();
    }
}