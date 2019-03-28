package razilov.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope("prototype")
@Getter
@Setter
public class User {
    private String name;
    private int age;
    private Timestamp created;
}
