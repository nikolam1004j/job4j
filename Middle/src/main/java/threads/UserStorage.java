package threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

@ThreadSafe
public class UserStorage implements Iterable<User> {
    @GuardedBy("this")
    private final Set<User> users = new HashSet<>();

    synchronized void transfer(int fromId, int toId, int amount) {
        Function<Integer, User> userById = id -> {
            User result = null;
            for (User user : users) {
                if (user.getId() == id) {
                    result = user;
                    break;
                }
            }
            return result;
        };

        User u1 = userById.apply(fromId);
        User u2 = userById.apply(toId);

        if (u1 == null || u2 == null) {
            throw new UnsupportedOperationException("Один из аккаунтов не найден");
        } else if (u1.getAmount() < amount) {
            throw new UnsupportedOperationException("Не хватает денег для передачи");
        }
        u1.setAmount(u1.getAmount() - amount);
        u2.setAmount(u2.getAmount() + amount);
    }

    synchronized boolean add(User user) {
        return users.add(user);
    }

    synchronized boolean update(User user) {
        boolean result = false;
        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setAmount(user.getAmount());
                result = true;
            }
        }
        return result;
    }

    synchronized boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public synchronized Iterator<User> iterator() {
        return users.iterator();
    }
}