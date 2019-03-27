package Main;

import models.Car;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        User user1 = (User)context.getBean("user1");
        User user2 = (User)context.getBean("user2");
        User user3 = (User)context.getBean("user3");
        User user4 = (User) context.getBean("user4");
        Car car = context.getBean(Car.class);
        Car car1 = context.getBean(Car.class);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(car);
        System.out.println(car1);
    }
}