package iopack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
     * Архивирует папку.
     * @throws IOException
     */
    private static void zipIt() throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(output())))) {
            LinkedList<File> fileList = new LinkedList<>();
            File f = new File(directory());
            if (f.exists() && f.isDirectory()) {
                fileList.push(f);
                do {
                    File[] files = fileList.poll().listFiles();
                    if (files != null) {
                        for (File file : files) {
                            //Если файл совпатает с ключом -e, пропускаем его.
                            if (file.getName().equalsIgnoreCase(excule())) {
                                continue;
                            }
                            if (file.isDirectory()) {
                                fileList.push(file);
                                zos.putNextEntry(new ZipEntry(file.getAbsolutePath().replace(directory(), "") + File.separator));
                                zos.closeEntry();
                            } else {
                                zos.putNextEntry(new ZipEntry(file.getAbsolutePath().replace(directory(), "")));
                                //После создания ZipEntry для файла, копируем в файл данные.
                                FileInputStream in = new FileInputStream(file);
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = in.read(buffer)) >= 0)
                                    zos.write(buffer, 0, len);
                                in.close();
                                zos.closeEntry();
                            }
                        }
                    }
                } while (!fileList.isEmpty());
            }
            zos.closeEntry();
            zos.flush();
        }
    }

    /**
     * Точка входа в программу.
     * @param args Аргументы.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 6) {
            throw new UnsupportedOperationException();
        }

        //Возможные ключи.
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

        //Если не собралось 3 ключа, программа бросает UnsupportedOperationException.
        if (insertedKeyValues.size() != 3) {
            throw new UnsupportedOperationException();
        }

        //Зипуем.
        zipIt();
    }
}