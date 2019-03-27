package models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class Config {

    @Bean
    public User user3() {
        return new User(3, "Vova", new Timestamp(System.currentTimeMillis()));
    }

    @Bean
    public User user4() {
        return new User(4, "Petya", new Timestamp(System.currentTimeMillis()));
    }
}
