package razilov.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import razilov.logic.DatabaseLogic;
import razilov.repo.Repo;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name = " + name);
        }

        DatabaseLogic logic = context.getBean(DatabaseLogic.class);
        System.out.println(logic.findById(8));

        context.close();
    }
}
