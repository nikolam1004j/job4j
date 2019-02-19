package deps;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Класс департаментов
 * @author Николай Разилов
 * @since 19.02.2019
 */
public class Departament {

    private TreeSet<String> tree;

    /**
     * Создает объект класса Departament
     * @param isReverse (true - реверсивный Comparator, false - Comparator по умолчанию)
     */
    public Departament(boolean isReverse) {
        if (isReverse) {
            tree = new TreeSet<>(reverseComparator());
        } else {
            tree = new TreeSet<>();
        }
    }

    private Comparator<String> reverseComparator() {
        return (o1, o2) -> {
            int result = o2.compareTo(o1);
            if (o1.length() > o2.length()) {
                if (o1.startsWith(o2)) {
                    result = 1;
                }
            } else if (o2.length() > o1.length()) {
                if (o2.startsWith(o1)) {
                    result = -1;
                }
            }
            return result;
        };
    }

    /**
     * Добавляет департаменты в коллекцию
     * @param val Строковое представление департамента
     */
    public void add(String val) {
        String[] parts = val.split("\\\\");
        tree.add(parts[0]);
        tree.add(val);
    }

    /**
     * Возвращает строковое представление коллекции департаментов
     * @return Строковое представление коллекции департаментов
     */
    @Override
    public String toString() {
        return tree.toString();
    }
}
