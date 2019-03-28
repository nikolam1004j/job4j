package storages;

import models.User;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStorage implements Storage, Iterable<User> {
    private List<User> users = new CopyOnWriteArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
