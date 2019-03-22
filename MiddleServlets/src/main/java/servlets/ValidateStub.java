package servlets;

import models.User;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class ValidateStub extends ValidateService {
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();
    @GuardedBy("this")
    private int ids = 0;

    @Override
    public synchronized boolean add(User user) {
        user.setId(ids++);
        store.put(user.getId(), user);
        return true;
    }

    @Override
    public synchronized List<User> findAll() {
        return new ArrayList<User>(store.values());
    }
}
