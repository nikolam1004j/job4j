package servlets;

import models.MemoryStore;
import models.User;

import java.util.List;

public class ValidateService {
    private static final ValidateService VALIDATE_SERVICE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService newInstance() {
        return VALIDATE_SERVICE;
    }

    private final MemoryStore memoryStore = MemoryStore.getInstance();

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
}