package servlets;

import models.DbStore;
import models.Role;
import models.Store;
import models.User;

import java.util.List;

public class ValidateService {
    private static final ValidateService VALIDATE = new ValidateService();

    public static ValidateService newInstance() {
        return VALIDATE;
    }

    private final Store memoryStore = DbStore.getInstance();

    public boolean add(User user) {
        return memoryStore.add(user);
    }

    public boolean update(User user) {
        boolean result = false;
        User byId = memoryStore.findById(user);
        if (byId != null) {
            result = memoryStore.update(user);
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        User byId = memoryStore.findById(user);
        if (byId != null) {
            result = memoryStore.delete(user);
        }
        return result;
    }

    public List<User> findAll() {
        return memoryStore.findAll();
    }

    public User findById(User user) {
        return memoryStore.findById(user);
    }

    public List<Role> getRoles() {
        return memoryStore.getRoles();
    }

    public boolean updateRole(User user, int roleId) {
        return memoryStore.updateRole(user, roleId);
    }
}