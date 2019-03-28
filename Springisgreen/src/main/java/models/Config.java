package models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class Config {

    @Bean
    public User user3() {
        return new User("Vova", new Timestamp(System.currentTimeMillis()));
    }

    @Bean
    public User user4() {
        return new User("Petya", new Timestamp(System.currentTimeMillis()));
    }

    @Bean
    public String stop() {
        return "-stop";
    }
}