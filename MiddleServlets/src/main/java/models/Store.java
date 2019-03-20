package models;

import java.util.List;

public interface Store {
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    boolean updateRole(User user, int roleId);
    List<User> findAll();
    List<Role> getRoles();
    User findById(User user);
}