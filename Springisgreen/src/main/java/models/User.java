package models;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String name;
    private Timestamp created;

    public User(String name, Timestamp created) {
        this.name = name;
        this.created = created;
    }
}