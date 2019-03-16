package models;

import java.util.List;

public interface Store {
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAll();
    User findById(User user);
}