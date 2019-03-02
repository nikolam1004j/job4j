package controls;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analize {

    /**
     * Анализирует состояния двух списков
     *
     * @param previous Начальный список.
     * @param current  Конечный список.
     * @return Результаты анализа.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> prevUsers = previous.stream()
                .collect(Collectors.toMap(
                        o -> o.id, o -> o
                ));

        Map<Integer, User> curUsers = current.stream()
                .collect(Collectors.toMap(
                        o -> o.id, o -> o
                ));

        long added = curUsers.keySet().stream()
                .filter(integer -> !prevUsers.keySet().contains(integer))
                .count();

        long deleted = prevUsers.keySet().stream()
                .filter(integer -> !curUsers.keySet().contains(integer))
                .count();

        int changed = 0;
        for (Map.Entry<Integer, User> entry : prevUsers.entrySet()) {
            if (curUsers.containsKey(entry.getKey())) {
                if (!curUsers.get(entry.getKey()).name.equals(entry.getValue().name)) {
                    changed++;
                }
            }
        }

        info.added = (int) added;
        info.deleted = (int) deleted;
        info.changed = changed;
        return info;
    }

    /**
     * Класс User
     */
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;
        private int hashCode;

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj != this) {
                if (obj instanceof User) {
                    User user = (User) obj;
                    result = this.id == user.id
                            && this.name.equals(user.name);
                }
            }
            return result;
        }

        @Override
        public int hashCode() {
            if (hashCode == 0) {
                int result = 17;
                result = result * 31 + id;
                result = result * 31 + (name == null ? 0 : name.hashCode());
                hashCode = result;
            }
            return hashCode;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", hashCode=" + hashCode +
                    '}';
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        /**
         * Возвращает строковое представление результатов анализа.
         *
         * @return Строковое представление результатов анализа.
         */
        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }
}