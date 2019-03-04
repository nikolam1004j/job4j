package iopack;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArgsTest {

    /**
     * Проверяет сохраненный архив.
     * В нем не должно быть файла, указанного в ключе -e.
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        try (ZipInputStream zis = new ZipInputStream(
                new FileInputStream(new File("C:\\Projects\\job4j\\project.zip")))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                Assert.assertNotEquals(zipEntry.getName(), "pom.xml");
            }
        }
    }
}