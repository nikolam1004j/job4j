package iopack;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Класс для упаковки папок в ZIP архивы.
 * @author Николай Разилов.
 * @since 04.03.2019
 */
public class Args {
    private static Map<String, String> insertedKeyValues;
    private static Set<String> availableKeys;

    /**
     * Возвращает заданную директорию, которую надо запаковать.
     * @return Заданную директория.
     */
    public static String directory() {
        String val = insertedKeyValues.get("-d");
        boolean b = val.endsWith(File.separator);
        return b ? val : val + File.separator;
    }

    /**
     * Получает значение ключа -e.
     * @return Значение ключа -e.
     */
    public static String excule() {
        return insertedKeyValues.get("-e");
    }

    /**
     * Получает значение ключа -o.
     * @return Значение ключа -o.
     */
    public static String output() {
        return insertedKeyValues.get("-o");
    }

    /**
     * Точка входа в программу.
     * @param args Аргументы.
     * @throws IOException Выбрасывается при ошибке архивации.
     * @throws UnsupportedOperationException Выбрасывается, если задаются неправильные параметры.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 6) {
            throw new UnsupportedOperationException();
        }

        availableKeys = new HashSet<String>() {{
            add("-d");
            add("-e");
            add("-o");
        }};

        insertedKeyValues = new HashMap<>();
        if (availableKeys.contains(args[0])) {
            insertedKeyValues.put(args[0].toLowerCase(), args[1]);
        }
        if (availableKeys.contains(args[2])) {
            insertedKeyValues.put(args[2].toLowerCase(), args[3]);
        }
        if (availableKeys.contains(args[4])) {
            insertedKeyValues.put(args[4].toLowerCase(), args[5]);
        }

        if (insertedKeyValues.size() != 3) {
            throw new UnsupportedOperationException();
        }

        Search search = new Search();
        List<File> files = search.files(directory(), Arrays.asList("java", "xml"));
        Zip.zip(output(), directory(), excule(), files);
    }
}
