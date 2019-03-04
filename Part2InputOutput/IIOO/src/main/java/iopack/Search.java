package iopack;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс поиска файлов.
 * @author Николай Разилов.
 * @since 04.06.2019
 */
public class Search {

    /**
     * Внутренний класс, представляющий собой фильтр для фалов и папок.
     */
    private class MyFilter implements FileFilter {
        private List<String> exts = new ArrayList<>();

        /**
         * Создает объект MyFilter с заданными расширениями.
         *
         * @param exts Спиок расширений.
         */
        public MyFilter(List<String> exts) {
            exts.forEach(s -> this.exts.add(s.toLowerCase()));
        }

        /**
         * Фильтр по расширеням.
         *
         * @param pathname Проверяемый путь.
         * @return Подходит ли папка или файл под фильтр.
         */
        @Override
        public boolean accept(File pathname) {
            boolean result = false;
            if (pathname.isDirectory()) {
                result = true;
            } else if (pathname.isFile()) {
                int lastIndexOf = pathname.getAbsolutePath().lastIndexOf(".");
                String ext;
                if (lastIndexOf > 0) {
                    ext = pathname.getAbsolutePath().substring(lastIndexOf + 1).toLowerCase();
                    if (exts.contains(ext)) {
                        result = true;
                    }
                }
            }
            return result;
        }
    }

    /**
     * Возвращает список найденных файлов.
     *
     * @param parent Директория, с которой начинать поиск.
     * @param exts   Список расширений.
     * @return Список найденных файлов.
     */
    public List<File> files(String parent, List<String> exts) {
        LinkedList<File> linkedFolders = new LinkedList<>();
        LinkedList<File> resultFileList = new LinkedList<>();
        File startDirectory = new File(parent);
        if (startDirectory.exists() && startDirectory.isDirectory()) {
            linkedFolders.push(startDirectory);
            do {
                File[] files = linkedFolders.poll().listFiles(new MyFilter(exts));
                for (File file : files) {
                    if (file.isDirectory()) {
                        linkedFolders.push(file);
                    } else {
                        resultFileList.push(file);
                    }
                }
            } while (!linkedFolders.isEmpty());
        }
        return resultFileList;
    }
}