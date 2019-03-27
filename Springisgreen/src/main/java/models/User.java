package models;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private Timestamp created;
    private Car car;

    public User(int id, String name, Timestamp created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Car getCar() {
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', created=%s}", id, name, created);
    }

}
