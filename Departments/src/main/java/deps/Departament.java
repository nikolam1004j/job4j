package deps;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Класс департаментов
 * @author Николай Разилов
 * @since 19.02.2019
 */
public class Departament implements Iterable<String> {

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
        if (parts.length > 0) {
            StringBuilder rs = new StringBuilder();
            for (String part : parts) {
                if (rs.length() == 0) {
                    rs.append(part);
                } else {
                    rs.append("\\").append(part);
                }
                tree.add(rs.toString());
            }
        }
    }

    /**
     * Возвращает строковое представление коллекции департаментов
     * @return Строковое представление коллекции департаментов
     */
    @Override
    public String toString() {
        return tree.toString();
    }

    /**
     * Возвращает итератор для коллекции департаментов
     * @return Итератор для коллекции департаментов (на основе TreeSet)
     */
    @Override
    public Iterator<String> iterator() {
        return tree.iterator();
    }

    /**
     * Возвращает размер коллекции департаментов
     * @return Размер коллекции департаментов
     */
    public int size() {
        return tree.size();
    }
}
