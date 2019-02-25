package maps;

import java.util.Calendar;

/**
 * Класс User.
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj != this) {
            if (obj instanceof User) {
                User user = (User) obj;
                result = name.equals(user.name)
                        && children == user.children
                        && birthday.equals(user.birthday);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (name != null ? name.hashCode() : 0);
        result = result * 31 + children;
        result = result * 31 + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}