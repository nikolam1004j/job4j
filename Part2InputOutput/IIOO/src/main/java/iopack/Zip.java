package iopack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /**
     * Архивирует папку.
     *
     * @throws IOException
     */
    public static void zip(String output, String directory, String excule, List<File> files) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(output)))) {
            File f = new File(directory);
            if (f.exists() && f.isDirectory()) {
                for (File file : files) {
                    //Если файл совпатает с ключом -e, пропускаем его.
                    if (file.getName().equalsIgnoreCase(excule)) {
                        continue;
                    }
                    if (file.isDirectory()) {
                        zos.putNextEntry(new ZipEntry(file.getAbsolutePath().replace(directory, "") + File.separator));
                        zos.closeEntry();
                    } else {
                        zos.putNextEntry(new ZipEntry(file.getAbsolutePath().replace(directory, "")));
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
            zos.closeEntry();
            zos.flush();
        }
    }
}